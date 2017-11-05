package com.kapilkoju.nepse.data.stocklive.fetch

import com.kapilkoju.nepse.data.stocklive.model.StockLive
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
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.response.MockRestResponseCreators
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

@RunWith(SpringRunner::class)
@RestClientTest(StockLiveFetcherImpl::class)
class StockLiveFetcherImplTest {

    @Autowired
    private lateinit var stockLiveFetcher: StockLiveFetcher

    @Autowired
    private lateinit var server: MockRestServiceServer

    @Value("\${nepse.stocklive.url}")
    private lateinit var stockLiveUrl: String

    @Value("classpath:data/stocklive-2017-10-31-12:20.html")
    private lateinit var stockLiveOpenHtml: Resource

    @Value("classpath:data/stocklive-2017-10-31-15:48.html")
    private lateinit var stockLiveClosedHtml: Resource

    @Test
    fun `getStockLives() should return list of stock lives for open market`() {
        server.expect(MockRestRequestMatchers.requestTo(stockLiveUrl))
                .andRespond(MockRestResponseCreators.withSuccess(stockLiveOpenHtml, MediaType.TEXT_HTML))

        val stockLives = stockLiveFetcher.getStockLives()
        assertThat(stockLives)
                .hasSize(103)
                .contains(
                        StockLive(
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("12:19:01"),
                                symbol = "NIL",
                                ltp = BigDecimal("941.00"),
                                ltv = BigDecimal("20"),
                                pointChange = BigDecimal("-9.00"),
                                percentageChange = BigDecimal("1.05"),
                                open = BigDecimal("941"),
                                high = BigDecimal("941"),
                                low = BigDecimal("940"),
                                volume = 113,
                                previousClosing = BigDecimal("950")),

                        StockLive(
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("12:19:01"),
                                symbol = "SCB",
                                ltp = BigDecimal("2105.00"),
                                ltv = BigDecimal("50"),
                                pointChange = BigDecimal("-10.00"),
                                percentageChange = BigDecimal("0.47"),
                                open = BigDecimal("2115"),
                                high = BigDecimal("2120"),
                                low = BigDecimal("2101"),
                                volume = 1149,
                                previousClosing = BigDecimal("2115")),

                        StockLive(
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("12:19:01"),
                                symbol = "BHBL",
                                ltp = BigDecimal("270.00"),
                                ltv = BigDecimal("436"),
                                pointChange = BigDecimal("-5.00"),
                                percentageChange = BigDecimal("1.82"),
                                open = BigDecimal("277"),
                                high = BigDecimal("277"),
                                low = BigDecimal("270"),
                                volume = 2150,
                                previousClosing = BigDecimal("275")),

                        StockLive(
                                valueDate = LocalDate.parse("2017-10-31"),
                                valueTime = LocalTime.parse("12:19:01"),
                                symbol = "NMBHF1",
                                ltp = BigDecimal("10.00"),
                                ltv = BigDecimal("832"),
                                pointChange = BigDecimal("-0.12"),
                                percentageChange = BigDecimal("1.19"),
                                open = BigDecimal("10"),
                                high = BigDecimal("10"),
                                low = BigDecimal("10"),
                                volume = 832,
                                previousClosing = BigDecimal("10")))
    }


    @Test
    fun `getStockLives() should return list of stock lives for closed market`() {
        server.expect(MockRestRequestMatchers.requestTo(stockLiveUrl))
                .andRespond(MockRestResponseCreators.withSuccess(stockLiveClosedHtml, MediaType.TEXT_HTML))

        val stockLives = stockLiveFetcher.getStockLives()
        assertThat(stockLives)
                .isEmpty()

    }
}