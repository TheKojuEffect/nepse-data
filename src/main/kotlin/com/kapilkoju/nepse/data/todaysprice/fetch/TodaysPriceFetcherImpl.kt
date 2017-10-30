package com.kapilkoju.nepse.data.todaysprice.fetch

import com.kapilkoju.nepse.data.todaysprice.model.TodaysPriceEntry
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.util.stream.Collectors.toList

@Service
class TodaysPriceFetcherImpl(
        @param:Value("\${nepse.todaysprice.url}") private val todaysPriceUrl: String,
        restTemplateBuilder: RestTemplateBuilder)
    : TodaysPriceFetcher {

    private val restTemplate: RestTemplate = restTemplateBuilder.build()

    override fun getTodaysPrice(): List<TodaysPriceEntry> {
        val pricesTableHtml = restTemplate.getForObject(todaysPriceUrl, String::class.java)

        val pricesTable = Jsoup.parse(pricesTableHtml)

        val priceTrs = pricesTable.select("table tr:gt(0)")

        val todaysPriceExtractor = { priceTr: Element ->
            val tds = priceTr.select("td")
            TodaysPriceEntry(
                    companyName = tds[0].text(),
                    noOfTransactions = Integer.valueOf(tds[1].text()),
                    maxPrice = BigDecimal(tds[2].text()),
                    minPrice = BigDecimal(tds[3].text()),
                    closingPrice = BigDecimal(tds[4].text()),
                    tradedShares = BigDecimal(tds[5].text()).toInt(),
                    amount = BigDecimal(tds[6].text()),
                    previousClosing = BigDecimal(tds[7].text()),
                    difference = BigDecimal(tds[8].text()))
        }

        return priceTrs.parallelStream()
                .map(todaysPriceExtractor)
                .collect(toList())
    }
}