package com.kapilkoju.nepse.data.floorsheet;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(FloorSheetServiceImpl.class)
public class FloorSheetServiceImplTest {

    @Autowired
    private FloorSheetService floorSheetService;

    @Autowired
    private MockRestServiceServer server;

    @Value("${nepse.floorsheet.url}")
    private String floorSheetUrl;

    @Value("classpath:data/floorsheet-2017-09-17.html")
    private Resource floorSheetHtml;

    @Test
    @Ignore
    public void getFloorSheetShouldReturnFloorSheet() throws IOException {
        server.expect(requestTo(floorSheetUrl))
                .andRespond(withSuccess(floorSheetHtml, MediaType.TEXT_HTML));

        final List<FloorSheetEntry> prices = floorSheetService.getFloorSheet().collectList().block();
        assertThat(prices)
                .hasSize(50)
                .contains(
                        FloorSheetEntry.builder()
                                .contractNo(201709175342179L)
                                .stockSymbol("PRVU")
                                .buyerBroker(45)
                                .sellerBroker(41)
                                .quantity(50)
                                .rate(new BigDecimal("376"))
                                .amount(new BigDecimal("18800.00"))
                                .build(),

                        FloorSheetEntry.builder()
                                .contractNo(201709175342182L)
                                .stockSymbol("NGPL")
                                .buyerBroker(29)
                                .sellerBroker(1)
                                .quantity(10)
                                .rate(new BigDecimal("237"))
                                .amount(new BigDecimal("2370.00"))
                                .build(),

                        FloorSheetEntry.builder()
                                .contractNo(201709175342221L)
                                .stockSymbol("NBBL")
                                .buyerBroker(58)
                                .sellerBroker(18)
                                .quantity(10)
                                .rate(new BigDecimal("3820"))
                                .amount(new BigDecimal("38200.00"))
                                .build(),

                        FloorSheetEntry.builder()
                                .contractNo(201709175342228L)
                                .stockSymbol("LBL")
                                .buyerBroker(47)
                                .sellerBroker(43)
                                .quantity(100)
                                .rate(new BigDecimal("339"))
                                .amount(new BigDecimal("33900.00"))
                                .build()
                );

    }

}