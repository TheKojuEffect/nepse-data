package com.kapilkoju.nepse.data;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
class TodaysPrice {
    private String companyName;
    private Integer noOfTransactions;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal closingPrice;
    private Integer tradedShares;
    private BigDecimal amount;
    private BigDecimal previousClosing;
    private BigDecimal difference;
}