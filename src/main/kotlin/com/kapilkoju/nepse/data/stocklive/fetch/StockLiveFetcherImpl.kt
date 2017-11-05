package com.kapilkoju.nepse.data.stocklive.fetch

import com.kapilkoju.nepse.data.stocklive.model.StockLive
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

@Service
class StockLiveFetcherImpl(
        @param:Value("\${nepse.stocklive.url}") private val stockLiveUrl: String,
        restTemplateBuilder: RestTemplateBuilder)
    : StockLiveFetcher {

    private val restTemplate: RestTemplate = restTemplateBuilder.build()
    private val valueDateTimeSelector = "#home-contents > div.col-xs-12.col-md-9.col-sm-9"
    private val stockLiveRowsSelector = "#home-contents > div.col-xs-12.col-md-9.col-sm-9 > table > tbody > tr"

    override fun getStockLives(): List<StockLive> {
        val stockLiveHtml = restTemplate.getForObject(stockLiveUrl, String::class.java)
        val stockLivePage = Jsoup.parse(stockLiveHtml)

        val (valueDate, valueTime) = parseValueDateTime(stockLivePage) ?: return emptyList()

        val priceTrs = stockLivePage.select(stockLiveRowsSelector)

        val stockLiveParser = { priceTr: Element ->
            val tds = priceTr.select("td")
            StockLive(
                    valueDate = valueDate,
                    valueTime = valueTime,
                    symbol = tds[1].text(),
                    ltp = BigDecimal(tds[2].text().replace(",", "")),
                    ltv = BigDecimal(tds[3].text()),
                    pointChange = BigDecimal(tds[4].text()),
                    percentageChange = BigDecimal(tds[5].text()),
                    open = BigDecimal(tds[6].text().replace(",", "")),
                    high = BigDecimal(tds[7].text().replace(",", "")),
                    low = BigDecimal(tds[8].text().replace(",", "")),
                    volume = tds[9].text().replace(",", "").toInt(),
                    previousClosing = BigDecimal(tds[10].text().replace(",", "")))
        }

        return priceTrs.map(stockLiveParser)
    }

    private fun parseValueDateTime(stockLivePage: Document): Pair<LocalDate, LocalTime>? {
        val stockLiveDivHtml = stockLivePage.select(valueDateTimeSelector).html()
        if (stockLiveDivHtml.startsWith("<table")) {
            return null
        }
        val valueDateTime = stockLiveDivHtml.substring(6, 38)
        val dateTime = valueDateTime.split(" &nbsp;&nbsp; ")
        val valueDate = LocalDate.parse(dateTime[0])
        val valueTime = LocalTime.parse(dateTime[1])
        return Pair(valueDate, valueTime)
    }
}