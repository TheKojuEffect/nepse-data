package com.kapilkoju.nepse.data.todaysprice

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

@RunWith(SpringRunner::class)
@RestClientTest(TodaysPriceServiceImpl::class)
class TodaysPriceServiceImplTest {

    @Autowired
    private lateinit var todaysPriceService: TodaysPriceService

    @Autowired
    private lateinit var server: MockRestServiceServer

    @Value("\${nepse.todaysprice.url}")
    private lateinit var todaysPriceUrl: String

    @Value("classpath:data/todaysprice-2017-09-17.xls")
    private lateinit var todaysPriceXls: Resource

    @Test
    fun getTodaysPricesShouldReturnListOfTodaysPrices() {
        server.expect(requestTo(todaysPriceUrl))
                .andRespond(withSuccess(todaysPriceXls, MediaType.valueOf("application/vnd.ms-excel")))

        val prices = todaysPriceService.getTodaysPrice()
        assertThat(prices)
                .hasSize(159)
                .contains(
                        TodaysPriceEntry(
                                companyName = "Agriculture Development Bank Limited",
                                noOfTransactions = 159,
                                maxPrice = BigDecimal("459.00"),
                                minPrice = BigDecimal("438.00"),
                                closingPrice = BigDecimal("447.00"),
                                tradedShares = BigDecimal("49764.00").toInt(),
                                amount = BigDecimal("22001355.00"),
                                previousClosing = BigDecimal("447.00"),
                                difference = BigDecimal("0.00")),

                        TodaysPriceEntry(
                                companyName = "Everest Insurance Co. Ltd.",
                                noOfTransactions = 34,
                                maxPrice = BigDecimal("2698.00"),
                                minPrice = BigDecimal("2652.00"),
                                closingPrice = BigDecimal("2685.00"),
                                tradedShares = BigDecimal("1500.00").toInt(),
                                amount = BigDecimal("4025360.00"),
                                previousClosing = BigDecimal("2650.00"),
                                difference = BigDecimal("35.00")),

                        TodaysPriceEntry(
                                companyName = "Khanikhola Hydropower Co. Ltd.",
                                noOfTransactions = 13,
                                maxPrice = BigDecimal("154.00"),
                                minPrice = BigDecimal("151.00"),
                                closingPrice = BigDecimal("151.00"),
                                tradedShares = BigDecimal("362.00").toInt(),
                                amount = BigDecimal("54958.00"),
                                previousClosing = BigDecimal("154.00"),
                                difference = BigDecimal("-3.00")),

                        TodaysPriceEntry(
                                companyName = "World Merchant Banking & Finance Ltd.",
                                noOfTransactions = 13,
                                maxPrice = BigDecimal("144.00"),
                                minPrice = BigDecimal("140.00"),
                                closingPrice = BigDecimal("142.00"),
                                tradedShares = BigDecimal("2466.00").toInt(),
                                amount = BigDecimal("350748.00"),
                                previousClosing = BigDecimal("144.00"),
                                difference = BigDecimal("-2.00"))
                )

    }

}