package com.kapilkoju.nepse.data.todaysprice.fetch

import com.kapilkoju.nepse.data.todaysprice.model.TodaysPrice

interface TodaysPriceFetcher {

    fun getTodaysPrices(): List<TodaysPrice>
}