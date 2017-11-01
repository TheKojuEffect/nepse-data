package com.kapilkoju.nepse.data.todaysprice.fetch

import com.kapilkoju.nepse.data.todaysprice.model.TodaysPrice
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
class TodaysPriceFetcherImpl(
        @param:Value("\${nepse.todaysprice.url}") private val todaysPriceUrl: String,
        restTemplateBuilder: RestTemplateBuilder)
    : TodaysPriceFetcher {

    private val restTemplate: RestTemplate = restTemplateBuilder.build()
    private val valueDateTimeSelector = "#home-contents table tbody > tr:first-child > td:first-child > label:first-child"
    private val priceRowsSelector = "#home-contents table tbody tr:nth-child(n+3):nth-last-child(n+5)"

    override fun getTodaysPrices(): List<TodaysPrice> {
        val todaysPriceHtml = restTemplate.getForObject(todaysPriceUrl, String::class.java)
        val todaysPricePage = Jsoup.parse(todaysPriceHtml)

        val (valueDate, valueTime) = parseValueDateTime(todaysPricePage)

        val priceTrs = todaysPricePage.select(priceRowsSelector)

        val todaysPriceParser = { priceTr: Element ->
            val tds = priceTr.select("td")
            TodaysPrice(
                    date = LocalDate.now(),
                    companyName = tds[1].text(),
                    noOfTransactions = Integer.valueOf(tds[2].text()),
                    maxPrice = BigDecimal(tds[3].text()),
                    minPrice = BigDecimal(tds[4].text()),
                    closingPrice = BigDecimal(tds[5].text()),
                    tradedShares = BigDecimal(tds[6].text()).toInt(),
                    amount = BigDecimal(tds[7].text()),
                    previousClosing = BigDecimal(tds[8].text()),
                    difference = BigDecimal(tds[9].text().removeSuffix("\u00a0")),
                    valueDate = valueDate,
                    valueTime = valueTime)
        }

        return priceTrs.map(todaysPriceParser)
    }

    private fun parseValueDateTime(todaysPricePage: Document): Pair<LocalDate, LocalTime> {
        val valueDateTimeLabel = todaysPricePage.select(valueDateTimeSelector).html()
        val valueDateTime = valueDateTimeLabel.removePrefix("As of ")
        val dateTime = valueDateTime.split("&nbsp;")
        val valueDate = LocalDate.parse(dateTime[0])
        val valueTime = LocalTime.parse(dateTime[1])
        return Pair(valueDate, valueTime)
    }
}