package com.kapilkoju.nepse.data.todaysprice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodaysPriceController(private val todaysPriceService: TodaysPriceService) {

    @GetMapping("/data/todaysprice")

    fun getTodaysPrice(): List<TodaysPriceEntry>
            = todaysPriceService.getTodaysPrice()
}
