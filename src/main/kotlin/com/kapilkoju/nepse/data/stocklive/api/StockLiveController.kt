package com.kapilkoju.nepse.data.stocklive.api

import com.kapilkoju.nepse.data.stocklive.fetch.StockLiveFetcher
import com.kapilkoju.nepse.data.stocklive.model.StockLive
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StockLiveController(private val stockLiveFetcher: StockLiveFetcher) {

    @GetMapping("/data/stocklive")
    fun getStockLives(): List<StockLive>
            = stockLiveFetcher.getStockLives()
}