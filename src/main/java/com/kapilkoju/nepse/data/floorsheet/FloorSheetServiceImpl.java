package com.kapilkoju.nepse.data.floorsheet;

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
public class FloorSheetServiceImpl implements FloorSheetService {

    private final String floorSheetUrl;
    private final RestTemplate restTemplate;

    public FloorSheetServiceImpl(@Value("${nepse.floorsheet.url}") String floorSheetUrl, RestTemplateBuilder restTemplateBuilder) {
        this.floorSheetUrl = floorSheetUrl;
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<FloorSheetEntry> getFloorSheet() {
        final String floorSheetHtml = restTemplate.getForObject(floorSheetUrl, String.class);

        final Document floorSheetTable = Jsoup.parse(floorSheetHtml);

        final Elements sheetTrs = floorSheetTable.select("table.my-table tbody tr:nth-child(n+3):nth-last-child(n+4)");

        final Function<Element, FloorSheetEntry> floorSheetExtractor = tr -> {
            final Elements tds = tr.select("td");
            return FloorSheetEntry.builder()
                    .contractNo(Long.valueOf(tds.get(1).text()))
                    .stockSymbol(tds.get(2).text())
                    .buyerBroker(Integer.valueOf(tds.get(3).text()))
                    .sellerBroker(Integer.valueOf(tds.get(4).text()))
                    .quantity(Integer.valueOf(tds.get(5).text()))
                    .rate(new BigDecimal(tds.get(6).text()))
                    .amount(new BigDecimal(tds.get(7).text()))
                    .build();
        };

        return sheetTrs.stream()
                .map(floorSheetExtractor)
                .collect(toList());
    }
}