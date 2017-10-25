package com.kapilkoju.nepse.data.floorsheet;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest(FloorSheetController.class)
public class FloorSheetControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private FloorSheetService floorSheetService;

    @Test
    @Ignore
    public void getFloorSheetShouldReturnFloorSheetJson() throws Exception {
        given(floorSheetService.getFloorSheet())
                .willReturn(Flux.fromIterable(getSampleFloorSheets()));

        webClient.get().uri("/data/floorsheet").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(FloorSheetEntry.class)
                .hasSize(2)
                .contains(getSampleFloorSheets().get(0))
                .contains(getSampleFloorSheets().get(1));

//        body.jsonPath("$", hasSize(20));
//        body.jsonPath("$[0].contractNo", is(201709175342182L));
//        body.jsonPath("$[0].stockSymbol", is("NGPL"));
//        body.jsonPath("$[0].buyerBroker", is(29));
//        body.jsonPath("$[0].sellerBroker", is(1));
//        body.jsonPath("$[0].quantity", is(10));
//        body.jsonPath("$[0].rate", is(237.0));
//        body.jsonPath("$[0].amount", is(2370.0));
//
//        body.jsonPath("$[1].contractNo", is(201709175342221L));
//        body.jsonPath("$[1].stockSymbol", is("NBBL"));
//        body.jsonPath("$[1].buyerBroker", is(58));
//        body.jsonPath("$[1].sellerBroker", is(18));
//        body.jsonPath("$[1].quantity", is(10));
//        body.jsonPath("$[1].rate", is(3820.0));
//        body.jsonPath("$[1].amount", is(38200.0));
    }

    private static List<FloorSheetEntry> getSampleFloorSheets() {
        return Arrays.asList(
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
                        .build()
        );
    }
}