package com.kapilkoju.nepse.data.floorsheet;

import java.math.BigDecimal;

class FloorSheetEntry {

    private final Long contractNo;
    private final String stockSymbol;
    private final Integer buyerBroker;
    private final Integer sellerBroker;
    private final Integer quantity;
    private final BigDecimal rate;
    private final BigDecimal amount;

    @java.beans.ConstructorProperties({"contractNo", "stockSymbol", "buyerBroker", "sellerBroker", "quantity", "rate", "amount"})
    FloorSheetEntry(
        Long contractNo,
        String stockSymbol,
        Integer buyerBroker,
        Integer sellerBroker,
        Integer quantity,
        BigDecimal rate,
        BigDecimal amount
    ) {
        this.contractNo = contractNo;
        this.stockSymbol = stockSymbol;
        this.buyerBroker = buyerBroker;
        this.sellerBroker = sellerBroker;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }

    public Long getContractNo() {
        return this.contractNo;
    }

    public String getStockSymbol() {
        return this.stockSymbol;
    }

    public Integer getBuyerBroker() {
        return this.buyerBroker;
    }

    public Integer getSellerBroker() {
        return this.sellerBroker;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FloorSheetEntry)) {
            return false;
        }
        final FloorSheetEntry other = (FloorSheetEntry) o;
        final Object this$contractNo = this.getContractNo();
        final Object other$contractNo = other.getContractNo();
        if (this$contractNo == null ? other$contractNo != null : !this$contractNo.equals(other$contractNo)) {
            return false;
        }
        final Object this$stockSymbol = this.getStockSymbol();
        final Object other$stockSymbol = other.getStockSymbol();
        if (this$stockSymbol == null ? other$stockSymbol != null : !this$stockSymbol.equals(other$stockSymbol)) {
            return false;
        }
        final Object this$buyerBroker = this.getBuyerBroker();
        final Object other$buyerBroker = other.getBuyerBroker();
        if (this$buyerBroker == null ? other$buyerBroker != null : !this$buyerBroker.equals(other$buyerBroker)) {
            return false;
        }
        final Object this$sellerBroker = this.getSellerBroker();
        final Object other$sellerBroker = other.getSellerBroker();
        if (this$sellerBroker == null ? other$sellerBroker != null : !this$sellerBroker.equals(other$sellerBroker)) {
            return false;
        }
        final Object this$quantity = this.getQuantity();
        final Object other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) {
            return false;
        }
        final Object this$rate = this.getRate();
        final Object other$rate = other.getRate();
        if (this$rate == null ? other$rate != null : !this$rate.equals(other$rate)) {
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $contractNo = this.getContractNo();
        result = result * PRIME + ($contractNo == null ? 43 : $contractNo.hashCode());
        final Object $stockSymbol = this.getStockSymbol();
        result = result * PRIME + ($stockSymbol == null ? 43 : $stockSymbol.hashCode());
        final Object $buyerBroker = this.getBuyerBroker();
        result = result * PRIME + ($buyerBroker == null ? 43 : $buyerBroker.hashCode());
        final Object $sellerBroker = this.getSellerBroker();
        result = result * PRIME + ($sellerBroker == null ? 43 : $sellerBroker.hashCode());
        final Object $quantity = this.getQuantity();
        result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
        final Object $rate = this.getRate();
        result = result * PRIME + ($rate == null ? 43 : $rate.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        return result;
    }

    public String toString() {
        return "FloorSheetEntry(contractNo=" +
            this.getContractNo() +
            ", stockSymbol=" +
            this.getStockSymbol() +
            ", buyerBroker=" +
            this.getBuyerBroker() +
            ", sellerBroker=" +
            this.getSellerBroker() +
            ", quantity=" +
            this.getQuantity() +
            ", rate=" +
            this.getRate() +
            ", amount=" +
            this.getAmount() +
            ")";
    }

    public static FloorSheetEntryBuilder builder() {
        return new FloorSheetEntryBuilder();
    }

    public static class FloorSheetEntryBuilder {

        private Long contractNo;
        private String stockSymbol;
        private Integer buyerBroker;
        private Integer sellerBroker;
        private Integer quantity;
        private BigDecimal rate;
        private BigDecimal amount;

        FloorSheetEntryBuilder() {
        }

        public FloorSheetEntry.FloorSheetEntryBuilder contractNo(Long contractNo) {
            this.contractNo = contractNo;
            return this;
        }

        public FloorSheetEntry.FloorSheetEntryBuilder stockSymbol(String stockSymbol) {
            this.stockSymbol = stockSymbol;
            return this;
        }

        public FloorSheetEntry.FloorSheetEntryBuilder buyerBroker(Integer buyerBroker) {
            this.buyerBroker = buyerBroker;
            return this;
        }

        public FloorSheetEntry.FloorSheetEntryBuilder sellerBroker(Integer sellerBroker) {
            this.sellerBroker = sellerBroker;
            return this;
        }

        public FloorSheetEntry.FloorSheetEntryBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public FloorSheetEntry.FloorSheetEntryBuilder rate(BigDecimal rate) {
            this.rate = rate;
            return this;
        }

        public FloorSheetEntry.FloorSheetEntryBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public FloorSheetEntry build() {
            return new FloorSheetEntry(contractNo, stockSymbol, buyerBroker, sellerBroker, quantity, rate, amount);
        }

        public String toString() {
            return "FloorSheetEntry.FloorSheetEntryBuilder(contractNo=" +
                this.contractNo +
                ", stockSymbol=" +
                this.stockSymbol +
                ", buyerBroker=" +
                this.buyerBroker +
                ", sellerBroker=" +
                this.sellerBroker +
                ", quantity=" +
                this.quantity +
                ", rate=" +
                this.rate +
                ", amount=" +
                this.amount +
                ")";
        }
    }
}
