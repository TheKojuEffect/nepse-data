package com.kapilkoju.nepse.data;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class NepseDataApi {

    private final TodaysPriceService todaysPriceService;

    public NepseDataApi(TodaysPriceService todaysPriceService) {
        this.todaysPriceService = todaysPriceService;
    }

    @RequestMapping("/todaysprice")
    public List<TodaysPrice> getTodaysPrice() {
        return todaysPriceService.getTodaysPrices();
    }
}
