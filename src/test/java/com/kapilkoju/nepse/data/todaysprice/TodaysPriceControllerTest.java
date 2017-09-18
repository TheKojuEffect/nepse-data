package com.kapilkoju.nepse.data.todaysprice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodaysPriceController.class)
public class TodaysPriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodaysPriceService todaysPriceService;

    @Test
    public void getTodaysPriceShouldReturnTodaysPricesJson() throws Exception {
        given(todaysPriceService.getTodaysPrice())
                .willReturn(getSampleTodaysPrices());

        mvc.perform(get("/data/todaysprice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].companyName", is("Khanikhola Hydropower Co. Ltd.")))
                .andExpect(jsonPath("$[0].noOfTransactions", is(14)))
                .andExpect(jsonPath("$[0].maxPrice", is(154.0)))
                .andExpect(jsonPath("$[0].minPrice", is(151.0)))
                .andExpect(jsonPath("$[0].closingPrice", is(151.0)))
                .andExpect(jsonPath("$[0].tradedShares", is(362)))
                .andExpect(jsonPath("$[0].amount", is(54958.0)))
                .andExpect(jsonPath("$[0].previousClosing", is(154.0)))

                .andExpect(jsonPath("$[1].companyName", is("World Merchant Banking & Finance Ltd.")))
                .andExpect(jsonPath("$[1].noOfTransactions", is(13)))
                .andExpect(jsonPath("$[1].maxPrice", is(144.00)))
                .andExpect(jsonPath("$[1].minPrice", is(140.00)))
                .andExpect(jsonPath("$[1].closingPrice", is(142.00)))
                .andExpect(jsonPath("$[1].tradedShares", is(2466)))
                .andExpect(jsonPath("$[1].amount", is(350748.00)))
                .andExpect(jsonPath("$[1].previousClosing", is(144.00)));
    }

    private static List<TodaysPrice> getSampleTodaysPrices() {
        return Arrays.asList(
                TodaysPrice.builder()
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

                TodaysPrice.builder()
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