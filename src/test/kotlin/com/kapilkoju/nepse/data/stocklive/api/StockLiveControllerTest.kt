package com.kapilkoju.nepse.data.stocklive.api

import com.kapilkoju.nepse.data.stocklive.fetch.StockLiveFetcher
import com.kapilkoju.nepse.data.stocklive.model.StockLive
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(StockLiveController::class)
class StockLiveControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var stockLiveFetcher: StockLiveFetcher

    private val sampleStockLives: List<StockLive> = Arrays.asList(
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
                    previousClosing = BigDecimal("275"))
    )

    @Test
    fun getStockLiveShouldReturnStockLiveJson() {
        given(stockLiveFetcher.getStockLives())
                .willReturn(sampleStockLives)

        mvc.perform(get("/data/stocklive"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[0].symbol", `is`("SCB")))
                .andExpect(jsonPath("$[0].ltp", `is`(2105.0)))
                .andExpect(jsonPath("$[0].ltv", `is`(50)))
                .andExpect(jsonPath("$[0].pointChange", `is`(-10.0)))
                .andExpect(jsonPath("$[0].percentageChange", `is`(0.47)))
                .andExpect(jsonPath("$[0].open", `is`(2115)))
                .andExpect(jsonPath("$[0].high", `is`(2120)))
                .andExpect(jsonPath("$[0].low", `is`(2101)))
                .andExpect(jsonPath("$[0].volume", `is`(1149)))
                .andExpect(jsonPath("$[0].previousClosing", `is`(2115)))

                .andExpect(jsonPath("$[1].symbol", `is`("BHBL")))
                .andExpect(jsonPath("$[1].ltp", `is`(270.0)))
                .andExpect(jsonPath("$[1].ltv", `is`(436)))
                .andExpect(jsonPath("$[1].pointChange", `is`(-5.0)))
                .andExpect(jsonPath("$[1].percentageChange", `is`(1.82)))
                .andExpect(jsonPath("$[1].open", `is`(277)))
                .andExpect(jsonPath("$[1].high", `is`(277)))
                .andExpect(jsonPath("$[1].low", `is`(270)))
                .andExpect(jsonPath("$[1].volume", `is`(2150)))
                .andExpect(jsonPath("$[1].previousClosing", `is`(275)))
    }

}