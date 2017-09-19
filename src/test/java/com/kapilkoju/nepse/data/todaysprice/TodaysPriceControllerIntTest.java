package com.kapilkoju.nepse.data.todaysprice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TodaysPriceControllerIntTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testGetTodaysPrice() throws Exception {
        final List<TodaysPriceEntry> prices = template.getForObject("/data/todaysprice", List.class);
        assertThat(prices).isNotEmpty();
    }
}
