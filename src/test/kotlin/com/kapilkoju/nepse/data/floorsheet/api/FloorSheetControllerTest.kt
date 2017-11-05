package com.kapilkoju.nepse.data.floorsheet.api

import com.kapilkoju.nepse.data.floorsheet.fetch.FloorSheetFetcher
import com.kapilkoju.nepse.data.floorsheet.model.FloorSheet
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
@WebMvcTest(FloorSheetController::class)
class FloorSheetControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var floorSheetFetcher: FloorSheetFetcher

    private val sampleFloorSheets: List<FloorSheet> = Arrays.asList(
            FloorSheet(
                    contractNo = 201709175342182L,
                    stockSymbol = "NGPL",
                    buyerBroker = "29",
                    sellerBroker = "1",
                    quantity = 10,
                    rate = BigDecimal("237"),
                    amount = BigDecimal("2370.00")),

            FloorSheet(
                    contractNo = 201709175342221L,
                    stockSymbol = "NBBL",
                    buyerBroker = "58",
                    sellerBroker = "18",
                    quantity = 10,
                    rate = BigDecimal("3820"),
                    amount = BigDecimal("38200.00"))
    )

    @Test
    fun getFloorSheetShouldReturnFloorSheetJson() {
        given(floorSheetFetcher.getFloorSheets())
                .willReturn(sampleFloorSheets)

        mvc.perform(get("/data/floorsheet"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$", hasSize<Any>(2)))
                .andExpect(jsonPath("$[0].contractNo", `is`(201709175342182L)))
                .andExpect(jsonPath("$[0].stockSymbol", `is`("NGPL")))
                .andExpect(jsonPath("$[0].buyerBroker", `is`("29")))
                .andExpect(jsonPath("$[0].sellerBroker", `is`("1")))
                .andExpect(jsonPath("$[0].quantity", `is`(10)))
                .andExpect(jsonPath("$[0].rate", `is`(237)))
                .andExpect(jsonPath("$[0].amount", `is`(2370.0)))

                .andExpect(jsonPath("$[1].contractNo", `is`(201709175342221L)))
                .andExpect(jsonPath("$[1].stockSymbol", `is`("NBBL")))
                .andExpect(jsonPath("$[1].buyerBroker", `is`("58")))
                .andExpect(jsonPath("$[1].sellerBroker", `is`("18")))
                .andExpect(jsonPath("$[1].quantity", `is`(10)))
                .andExpect(jsonPath("$[1].rate", `is`(3820)))
                .andExpect(jsonPath("$[1].amount", `is`(38200.0)))
    }

}