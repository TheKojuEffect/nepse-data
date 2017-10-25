package com.kapilkoju.nepse.data.todaysprice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest(TodaysPriceController.class)
public class TodaysPriceControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TodaysPriceService todaysPriceService;

    @Test
    public void getTodaysPriceShouldReturnTodaysPricesJson() throws Exception {
        given(todaysPriceService.getTodaysPrice())
            .willReturn(getSampleTodaysPrices());

        final BodyContentSpec body = webClient.get().uri("/data/todaysprice").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody();

        body.jsonPath("$", hasSize(2));
        body.jsonPath("$[0].companyName", is("Khanikhola Hydropower Co. Ltd."));
        body.jsonPath("$[0].noOfTransactions", is(14));
        body.jsonPath("$[0].maxPrice", is(154.0));
        body.jsonPath("$[0].minPrice", is(151.0));
        body.jsonPath("$[0].closingPrice", is(151.0));
        body.jsonPath("$[0].tradedShares", is(362));
        body.jsonPath("$[0].amount", is(54958.0));
        body.jsonPath("$[0].previousClosing", is(154.0));

        body.jsonPath("$[1].companyName", is("World Merchant Banking & Finance Ltd."));
        body.jsonPath("$[1].noOfTransactions", is(13));
        body.jsonPath("$[1].maxPrice", is(144.00));
        body.jsonPath("$[1].minPrice", is(140.00));
        body.jsonPath("$[1].closingPrice", is(142.00));
        body.jsonPath("$[1].tradedShares", is(2466));
        body.jsonPath("$[1].amount", is(350748.00));
        body.jsonPath("$[1].previousClosing", is(144.00));
    }

    private static List<TodaysPriceEntry> getSampleTodaysPrices() {
        return Arrays.asList(
            TodaysPriceEntry.builder()
                .companyName("Khanikhola Hydropower Co. Ltd.")
                .noOfTransactions(14)
                .maxPrice(new BigDecimal("154.00"))
                .minPrice(new BigDecimal("151.00"))
                .closingPrice(new BigDecimal("151.00"))
                .tradedShares(new BigDecimal("362.00").intValue())
                .amount(new BigDecimal("54958.00"))
                .previousClosing(new BigDecimal("154.00"))
                .difference(new BigDecimal("-3.00"))
                .build(),

            TodaysPriceEntry.builder()
                .companyName("World Merchant Banking & Finance Ltd.")
                .noOfTransactions(13)
                .maxPrice(new BigDecimal("144.00"))
                .minPrice(new BigDecimal("140.00"))
                .closingPrice(new BigDecimal("142.00"))
                .tradedShares(new BigDecimal("2466.00").intValue())
                .amount(new BigDecimal("350748.00"))
                .previousClosing(new BigDecimal("144.00"))
                .difference(new BigDecimal("-2.00"))
                .build()
        );
    }
}