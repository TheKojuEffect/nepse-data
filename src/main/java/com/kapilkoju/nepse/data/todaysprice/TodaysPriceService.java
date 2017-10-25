package com.kapilkoju.nepse.data.todaysprice;

import reactor.core.publisher.Flux;

public interface TodaysPriceService {

    Flux<TodaysPriceEntry> getTodaysPrice();
}