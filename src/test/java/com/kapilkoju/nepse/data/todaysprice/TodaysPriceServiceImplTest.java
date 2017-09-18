package com.kapilkoju.nepse.data.todaysprice;

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
@RestClientTest(TodaysPriceServiceImpl.class)
public class TodaysPriceServiceImplTest {

    @Autowired
    private TodaysPriceService todaysPriceService;

    @Autowired
    private MockRestServiceServer server;

    @Value("${nepse.todaysprice.url}")
    private String todaysPriceUrl;

    @Value("classpath:data/todaysprice-2017-09-17.xls")
    private Resource todaysPriceXls;

    @Test
    public void getTodaysPricesShouldReturnListOfTodaysPrices() throws IOException {
        server.expect(requestTo(todaysPriceUrl))
                .andRespond(withSuccess(todaysPriceXls, MediaType.valueOf("application/vnd.ms-excel")));

        final List<TodaysPrice> prices = todaysPriceService.getTodaysPrice();
        assertThat(prices)
                .hasSize(159)
                .contains(
                        TodaysPrice.builder()
                                .companyName("Agriculture Development Bank Limited")
                                .noOfTransactions(159)
                                .maxPrice(new BigDecimal("459.00"))
                                .minPrice(new BigDecimal("438.00"))
                                .closingPrice(new BigDecimal("447.00"))
                                .tradedShares(new BigDecimal("49764.00").intValue())
                                .amount(new BigDecimal("22001355.00"))
                                .previousClosing(new BigDecimal("447.00"))
                                .difference(new BigDecimal("0.00"))
                                .build(),

                        TodaysPrice.builder()
                                .companyName("Everest Insurance Co. Ltd.")
                                .noOfTransactions(34)
                                .maxPrice(new BigDecimal("2698.00"))
                                .minPrice(new BigDecimal("2652.00"))
                                .closingPrice(new BigDecimal("2685.00"))
                                .tradedShares(new BigDecimal("1500.00").intValue())
                                .amount(new BigDecimal("4025360.00"))
                                .previousClosing(new BigDecimal("2650.00"))
                                .difference(new BigDecimal("35.00"))
                                .build(),

                        TodaysPrice.builder()
                                .companyName("Khanikhola Hydropower Co. Ltd.")
                                .noOfTransactions(13)
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