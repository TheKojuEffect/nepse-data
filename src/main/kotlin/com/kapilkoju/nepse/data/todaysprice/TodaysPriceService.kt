package com.kapilkoju.nepse.data.todaysprice

interface TodaysPriceService {

    fun getTodaysPrice(): List<TodaysPriceEntry>
}