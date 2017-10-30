package com.kapilkoju.nepse.data.todaysprice

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
import java.util.*

@RunWith(SpringRunner::class)
@WebMvcTest(TodaysPriceController::class)
class TodaysPriceControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var todaysPriceService: TodaysPriceService

    private val sampleTodaysPrices: List<TodaysPriceEntry> = Arrays.asList(
            TodaysPriceEntry(
                    companyName = "Khanikhola Hydropower Co. Ltd.",
                    noOfTransactions = 14,
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

    @Test
    fun getTodaysPriceShouldReturnTodaysPricesJson() {
        given(todaysPriceService.getTodaysPrice())
                .willReturn(sampleTodaysPrices)

        mvc.perform(get("/data/todaysprice"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[0].companyName", `is`("Khanikhola Hydropower Co. Ltd.")))
                .andExpect(jsonPath("$[0].noOfTransactions", `is`(14)))
                .andExpect(jsonPath("$[0].maxPrice", `is`(154.0)))
                .andExpect(jsonPath("$[0].minPrice", `is`(151.0)))
                .andExpect(jsonPath("$[0].closingPrice", `is`(151.0)))
                .andExpect(jsonPath("$[0].tradedShares", `is`(362)))
                .andExpect(jsonPath("$[0].amount", `is`(54958.0)))
                .andExpect(jsonPath("$[0].previousClosing", `is`(154.0)))

                .andExpect(jsonPath("$[1].companyName", `is`("World Merchant Banking & Finance Ltd.")))
                .andExpect(jsonPath("$[1].noOfTransactions", `is`(13)))
                .andExpect(jsonPath("$[1].maxPrice", `is`(144.00)))
                .andExpect(jsonPath("$[1].minPrice", `is`(140.00)))
                .andExpect(jsonPath("$[1].closingPrice", `is`(142.00)))
                .andExpect(jsonPath("$[1].tradedShares", `is`(2466)))
                .andExpect(jsonPath("$[1].amount", `is`(350748.00)))
                .andExpect(jsonPath("$[1].previousClosing", `is`(144.00)))
    }

}