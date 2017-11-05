package com.kapilkoju.nepse.data.floorsheet.fetch

import com.kapilkoju.nepse.data.floorsheet.model.FloorSheet
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.util.stream.Collectors.toList

@Service
class FloorSheetFetcherImpl(
        @param:Value("\${nepse.floorsheet.url}") private val floorSheetUrl: String,
        restTemplateBuilder: RestTemplateBuilder)
    : FloorSheetFetcher {

    private val restTemplate: RestTemplate = restTemplateBuilder.build()

    override fun getFloorSheets(): List<FloorSheet> {
        val floorSheetHtml = restTemplate.getForObject(floorSheetUrl, String::class.java)

        val floorSheetTable = Jsoup.parse(floorSheetHtml)

        val sheetTrs = floorSheetTable.select("table.my-table tbody tr:nth-child(n+3):nth-last-child(n+4)")

        val floorSheetExtractor = { tr: Element ->
            val tds = tr.select("td")
            FloorSheet(
                    contractNo = tds[1].text().toLong(),
                    stockSymbol = tds[2].text(),
                    buyerBroker = tds[3].text(),
                    sellerBroker = tds[4].text(),
                    quantity = Integer.valueOf(tds[5].text()),
                    rate = BigDecimal(tds[6].text()),
                    amount = BigDecimal(tds[7].text()))
        }
        return sheetTrs.parallelStream()
                .map(floorSheetExtractor)
                .collect(toList())
    }
}