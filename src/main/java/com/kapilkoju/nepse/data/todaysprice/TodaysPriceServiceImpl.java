package com.kapilkoju.nepse.data.todaysprice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class TodaysPriceServiceImpl implements TodaysPriceService {

    private final WebClient webClient;

    @SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
    public TodaysPriceServiceImpl(@Value("${nepse.todaysprice.url}") String todaysPriceUrl,
                                  WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl(todaysPriceUrl).build();
    }

    @Override
    public Flux<TodaysPriceEntry> getTodaysPrice() {
        return webClient.get().retrieve().bodyToMono(String.class)
                .flatMapIterable(priceTableMapper);
    }

    private static final Function<Element, TodaysPriceEntry> priceTrMapper = priceTr -> {
        final Elements tds = priceTr.select("td");
        return TodaysPriceEntry.builder()
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

    private static final Function<String, Iterable<TodaysPriceEntry>> priceTableMapper = pricesTableHtml -> {

        final Document pricesTable = Jsoup.parse(pricesTableHtml);

        final Elements priceTrs = pricesTable.select("table tr:gt(0)");
        return priceTrs.parallelStream()
                .map(priceTrMapper)
                .collect(toList());
    };

}