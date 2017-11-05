package com.kapilkoju.nepse.data.stocklive.api


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StockLiveControllerIntTest {

    @Autowired
    private lateinit var template: TestRestTemplate

    @Test
    fun testGetStockLive() {
        val entries = template.getForObject("/data/stocklive", List::class.java)
        assertThat(entries).isNotNull
    }

}
