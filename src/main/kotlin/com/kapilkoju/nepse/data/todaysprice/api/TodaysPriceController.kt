package com.kapilkoju.nepse.data.todaysprice.api

import com.kapilkoju.nepse.data.todaysprice.model.TodaysPriceEntry
import com.kapilkoju.nepse.data.todaysprice.fetch.TodaysPriceFetcher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodaysPriceController(private val todaysPriceFetcher: TodaysPriceFetcher) {

    @GetMapping("/data/todaysprice")

    fun getTodaysPrice(): List<TodaysPriceEntry>
            = todaysPriceFetcher.getTodaysPrice()
}
