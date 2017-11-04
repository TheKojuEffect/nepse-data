package com.kapilkoju.nepse.data.todaysprice.api

import com.kapilkoju.nepse.data.todaysprice.fetch.TodaysPriceFetcher
import com.kapilkoju.nepse.data.todaysprice.model.TodaysPrice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodaysPriceController(private val todaysPriceFetcher: TodaysPriceFetcher) {

    @GetMapping("/data/todaysprice")
    fun getTodaysPrices(): List<TodaysPrice>
            = todaysPriceFetcher.getTodaysPrices()
}
