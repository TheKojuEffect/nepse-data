package com.kapilkoju.nepse.data.todaysprice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class TodaysPriceServiceImpl implements TodaysPriceService {

    private final String todaysPriceUrl;
    private final RestTemplate restTemplate;

    public TodaysPriceServiceImpl(@Value("${nepse.todaysprice.url}") String todaysPriceUrl, RestTemplateBuilder restTemplateBuilder) {
        this.todaysPriceUrl = todaysPriceUrl;
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<TodaysPrice> getTodaysPrice() {
        final String pricesTableHtml = restTemplate.getForObject(todaysPriceUrl, String.class);

        final Document pricesTable = Jsoup.parse(pricesTableHtml);

        final Elements priceTrs = pricesTable.select("table tr:gt(0)");

        final Function<Element, TodaysPrice> todaysPriceExtractor = priceTr -> {
            final Elements tds = priceTr.select("td");
            return TodaysPrice.builder()
                    .companyName(tds.get(0).text())
                    .noOfTransactions(Integer.valueOf(tds.get(1).text()))
                    .maxPrice(new BigDecimal(tds.get(2).text()))
                    .minPrice(new BigDecimal(tds.get(3).text()))
                    .closingPrice(new BigDecimal(tds.get(4).text()))
                    .tradedShares(new BigDecimal(tds.get(5).text()).intValue())
                    .amount(new BigDecimal(tds.get(6).text()))
                    .previousClosing(new BigDecimal(tds.get(7).text()))
                    .difference(new BigDecimal(tds.get(8).text()))
                    .build();
        };

        return priceTrs.stream()
                .map(todaysPriceExtractor)
                .collect(toList());
    }
}