package com.kapilkoju.nepse.data.stocklive.fetch

import com.kapilkoju.nepse.data.stocklive.model.StockLive

interface StockLiveFetcher {

    fun getStockLives(): List<StockLive>
}