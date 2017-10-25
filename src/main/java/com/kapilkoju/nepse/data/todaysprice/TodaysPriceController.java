package com.kapilkoju.nepse.data.todaysprice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TodaysPriceController {

    private final TodaysPriceService todaysPriceService;

    public TodaysPriceController(TodaysPriceService todaysPriceService) {
        this.todaysPriceService = todaysPriceService;
    }

    @GetMapping("/data/todaysprice")
    public Flux<TodaysPriceEntry> getTodaysPrice() {
        return todaysPriceService.getTodaysPrice();
    }
}
