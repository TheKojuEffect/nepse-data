package com.kapilkoju.nepse.data.floorsheet;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
class FloorSheet {
    private Long contractNo;
    private String stockSymbol;
    private Integer buyerBroker;
    private Integer sellerBroker;
    private Integer quantity;
    private BigDecimal rate;
    private BigDecimal amount;
}
