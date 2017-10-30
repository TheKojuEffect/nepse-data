package com.kapilkoju.nepse.data.floorsheet

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
@RestClientTest(FloorSheetServiceImpl::class)
class FloorSheetServiceImplTest {

    @Autowired
    private lateinit var floorSheetService: FloorSheetService

    @Autowired
    private lateinit var server: MockRestServiceServer

    @Value("\${nepse.floorsheet.url}")
    private lateinit var floorSheetUrl: String

    @Value("classpath:data/floorsheet-2017-09-17.html")
    private lateinit var floorSheetHtml: Resource

    @Test
    fun getFloorSheetShouldReturnFloorSheet() {
        server.expect(requestTo(floorSheetUrl))
                .andRespond(withSuccess(floorSheetHtml, MediaType.TEXT_HTML))

        val prices = floorSheetService.getFloorSheet()
        assertThat(prices)
                .hasSize(50)
                .contains(
                        FloorSheetEntry(
                                contractNo = 201709175342179L,
                                stockSymbol = "PRVU",
                                buyerBroker = 45,
                                sellerBroker = 41,
                                quantity = 50,
                                rate = BigDecimal("376"),
                                amount = BigDecimal("18800.00")),

                        FloorSheetEntry(
                                contractNo = 201709175342182L,
                                stockSymbol = "NGPL",
                                buyerBroker = 29,
                                sellerBroker = 1,
                                quantity = 10,
                                rate = BigDecimal("237"),
                                amount = BigDecimal("2370.00")),

                        FloorSheetEntry(
                                contractNo = 201709175342221L,
                                stockSymbol = "NBBL",
                                buyerBroker = 58,
                                sellerBroker = 18,
                                quantity = 10,
                                rate = BigDecimal("3820"),
                                amount = BigDecimal("38200.00")),

                        FloorSheetEntry(
                                contractNo = 201709175342228L,
                                stockSymbol = "LBL",
                                buyerBroker = 47,
                                sellerBroker = 43,
                                quantity = 100,
                                rate = BigDecimal("339"),
                                amount = BigDecimal("33900.00"))
                )

    }

}