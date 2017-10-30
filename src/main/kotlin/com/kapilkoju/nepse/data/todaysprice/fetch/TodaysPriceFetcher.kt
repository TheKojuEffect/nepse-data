package com.kapilkoju.nepse.data.todaysprice.fetch

import com.kapilkoju.nepse.data.todaysprice.model.TodaysPriceEntry

interface TodaysPriceFetcher {

    fun getTodaysPrice(): List<TodaysPriceEntry>
}