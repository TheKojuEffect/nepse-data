package com.kapilkoju.nepse.data.floorsheet;

import org.junit.Ignore;
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
@WebMvcTest(FloorSheetController.class)
public class FloorSheetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FloorSheetService floorSheetService;

    @Ignore
    @Test
    public void getFloorSheetShouldReturnFloorSheetJson() throws Exception {
        given(floorSheetService.getFloorSheet())
                .willReturn(getSampleFloorSheets());

        mvc.perform(get("/data/floorSheet"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].contractNo", is(201709175342182L)))
                .andExpect(jsonPath("$[0].stockSymbol", is("NGPL")))
                .andExpect(jsonPath("$[0].buyerBroker", is(29)))
                .andExpect(jsonPath("$[0].sellerBroker", is(1)))
                .andExpect(jsonPath("$[0].quantity", is(10)))
                .andExpect(jsonPath("$[0].rate", is(237.0)))
                .andExpect(jsonPath("$[0].amount", is(2370.0)))

                .andExpect(jsonPath("$[1].contractNo", is(201709175342221L)))
                .andExpect(jsonPath("$[1].stockSymbol", is("NBBL")))
                .andExpect(jsonPath("$[1].buyerBroker", is(58)))
                .andExpect(jsonPath("$[1].sellerBroker", is(18)))
                .andExpect(jsonPath("$[1].quantity", is(10)))
                .andExpect(jsonPath("$[1].rate", is(3820.0)))
                .andExpect(jsonPath("$[1].amount", is(38200.0)));
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