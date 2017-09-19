package com.kapilkoju.nepse.data.floorsheet;


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
public class FloorSheetControllerIntTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testGetFloorSheet() throws Exception {
        final List entries = template.getForObject("/data/floorsheet", List.class);
        assertThat(entries).isNotEmpty()
                .size().isLessThanOrEqualTo(9);
    }

}
