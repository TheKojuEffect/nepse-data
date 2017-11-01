package com.kapilkoju.nepse.data.todaysprice.service

import com.kapilkoju.nepse.data.todaysprice.model.TodaysPrice
import com.kapilkoju.nepse.data.todaysprice.model.TodaysPriceId
import org.springframework.data.jpa.repository.JpaRepository

interface TodaysPriceRepo : JpaRepository<TodaysPrice, TodaysPriceId>