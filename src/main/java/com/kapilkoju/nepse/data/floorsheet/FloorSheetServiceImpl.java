package com.kapilkoju.nepse.data.floorsheet;

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
public class FloorSheetServiceImpl implements FloorSheetService {

    private final WebClient webClient;

    @SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
    public FloorSheetServiceImpl(@Value("${nepse.floorsheet.url}") String floorSheetUrl, WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.baseUrl(floorSheetUrl).build();
    }

    @Override
    public Flux<FloorSheetEntry> getFloorSheet() {
        return webClient.get().retrieve().bodyToMono(String.class)
                .flatMapIterable(floorSheetEntriesMapper);
    }

    private static final Function<Element, FloorSheetEntry> floorSheetEntryMapper = tr -> {
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

    private static final Function<String, Iterable<? extends FloorSheetEntry>> floorSheetEntriesMapper = floorSheetHtml -> {
        final Document floorSheetTable = Jsoup.parse(floorSheetHtml);

        final Elements sheetTrs = floorSheetTable.select("table.my-table tbody tr:nth-child(n+3):nth-last-child(n+4)");

        return sheetTrs.parallelStream()
                .map(floorSheetEntryMapper)
                .collect(toList());
    };
}