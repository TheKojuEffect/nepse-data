package com.kapilkoju.nepse.data.todaysprice.fetch

import com.kapilkoju.nepse.data.todaysprice.model.TodaysPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

@RunWith(SpringRunner::class)
@RestClientTest(TodaysPriceFetcherImpl::class)
class TodaysPriceFetcherImplTest {

    @Autowired
    private lateinit var todaysPriceFetcher: TodaysPriceFetcher

    @Autowired
    private lateinit var server: MockRestServiceServer

    @Value("\${nepse.todaysprice.url}")
    private lateinit var todaysPriceUrl: String

    @Value("classpath:data/todaysprice-2017-10-31-15:48.html")
    private lateinit var todaysPriceHtml: Resource

    @Test
    fun getTodaysPricesShouldReturnListOfTodaysPrices() {
        server.expect(requestTo(todaysPriceUrl))
                .andRespond(withSuccess(todaysPriceHtml, MediaType.TEXT_HTML))

        val prices = todaysPriceFetcher.getTodaysPrices()
        assertThat(prices)
                .hasSize(160)
                .contains(

                        TodaysPrice(
                                date = LocalDate.now(),
                                companyName = "Agriculture Development Bank Limited",
                                noOfTransactions = 38,
                                maxPrice = BigDecimal("440.00"),
                                minPrice = BigDecimal("433.00"),
                                closingPrice = BigDecimal("437.00"),
                                tradedShares = BigDecimal("8889.00").toInt(),
                                amount = BigDecimal("3866223.00"),
                                previousClosing = BigDecimal("437.00"),
                                difference = BigDecimal("0.00"),
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("15:00:00")),

                        TodaysPrice(
                                date = LocalDate.now(),
                                companyName = "Everest Insurance Co. Ltd.",
                                noOfTransactions = 1,
                                maxPrice = BigDecimal("2635.00"),
                                minPrice = BigDecimal("2635.00"),
                                closingPrice = BigDecimal("2635.00"),
                                tradedShares = BigDecimal("10.00").toInt(),
                                amount = BigDecimal("26350.00"),
                                previousClosing = BigDecimal("2635.00"),
                                difference = BigDecimal("0.00"),
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("15:00:00")),

                        TodaysPrice(
                                date = LocalDate.now(),
                                companyName = "Khanikhola Hydropower Co. Ltd.",
                                noOfTransactions = 12,
                                maxPrice = BigDecimal("168.00"),
                                minPrice = BigDecimal("161.00"),
                                closingPrice = BigDecimal("161.00"),
                                tradedShares = BigDecimal("154.00").toInt(),
                                amount = BigDecimal("25352.00"),
                                previousClosing = BigDecimal("167.00"),
                                difference = BigDecimal("-6.00"),
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("15:00:00")),

                        TodaysPrice(
                                date = LocalDate.now(),
                                companyName = "World Merchant Banking & Finance Ltd.",
                                noOfTransactions = 3,
                                maxPrice = BigDecimal("138.00"),
                                minPrice = BigDecimal("136.00"),
                                closingPrice = BigDecimal("138.00"),
                                tradedShares = BigDecimal("1500.00").toInt(),
                                amount = BigDecimal("206000.00"),
                                previousClosing = BigDecimal("138.00"),
                                difference = BigDecimal("0.00"),
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("15:00:00"))
                )

    }

}