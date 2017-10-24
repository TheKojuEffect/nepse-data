package com.kapilkoju.nepse.data.todaysprice;

import java.math.BigDecimal;

class TodaysPriceEntry {
    private final String companyName;
    private final Integer noOfTransactions;
    private final BigDecimal maxPrice;
    private final BigDecimal minPrice;
    private final BigDecimal closingPrice;
    private final Integer tradedShares;
    private final BigDecimal amount;
    private final BigDecimal previousClosing;
    private final BigDecimal difference;

    @java.beans.ConstructorProperties({
        "companyName",
        "noOfTransactions",
        "maxPrice",
        "minPrice",
        "closingPrice",
        "tradedShares",
        "amount",
        "previousClosing",
        "difference"
    })
    TodaysPriceEntry(
        String companyName,
        Integer noOfTransactions,
        BigDecimal maxPrice,
        BigDecimal minPrice,
        BigDecimal closingPrice,
        Integer tradedShares,
        BigDecimal amount,
        BigDecimal previousClosing,
        BigDecimal difference
    ) {
        this.companyName = companyName;
        this.noOfTransactions = noOfTransactions;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.closingPrice = closingPrice;
        this.tradedShares = tradedShares;
        this.amount = amount;
        this.previousClosing = previousClosing;
        this.difference = difference;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Integer getNoOfTransactions() {
        return this.noOfTransactions;
    }

    public BigDecimal getMaxPrice() {
        return this.maxPrice;
    }

    public BigDecimal getMinPrice() {
        return this.minPrice;
    }

    public BigDecimal getClosingPrice() {
        return this.closingPrice;
    }

    public Integer getTradedShares() {
        return this.tradedShares;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getPreviousClosing() {
        return this.previousClosing;
    }

    public BigDecimal getDifference() {
        return this.difference;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TodaysPriceEntry)) {
            return false;
        }
        final TodaysPriceEntry other = (TodaysPriceEntry) o;
        final Object this$companyName = this.getCompanyName();
        final Object other$companyName = other.getCompanyName();
        if (this$companyName == null ? other$companyName != null : !this$companyName.equals(other$companyName)) {
            return false;
        }
        final Object this$noOfTransactions = this.getNoOfTransactions();
        final Object other$noOfTransactions = other.getNoOfTransactions();
        if (this$noOfTransactions == null ? other$noOfTransactions != null : !this$noOfTransactions.equals(other$noOfTransactions)) {
            return false;
        }
        final Object this$maxPrice = this.getMaxPrice();
        final Object other$maxPrice = other.getMaxPrice();
        if (this$maxPrice == null ? other$maxPrice != null : !this$maxPrice.equals(other$maxPrice)) {
            return false;
        }
        final Object this$minPrice = this.getMinPrice();
        final Object other$minPrice = other.getMinPrice();
        if (this$minPrice == null ? other$minPrice != null : !this$minPrice.equals(other$minPrice)) {
            return false;
        }
        final Object this$closingPrice = this.getClosingPrice();
        final Object other$closingPrice = other.getClosingPrice();
        if (this$closingPrice == null ? other$closingPrice != null : !this$closingPrice.equals(other$closingPrice)) {
            return false;
        }
        final Object this$tradedShares = this.getTradedShares();
        final Object other$tradedShares = other.getTradedShares();
        if (this$tradedShares == null ? other$tradedShares != null : !this$tradedShares.equals(other$tradedShares)) {
            return false;
        }
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) {
            return false;
        }
        final Object this$previousClosing = this.getPreviousClosing();
        final Object other$previousClosing = other.getPreviousClosing();
        if (this$previousClosing == null ? other$previousClosing != null : !this$previousClosing.equals(other$previousClosing)) {
            return false;
        }
        final Object this$difference = this.getDifference();
        final Object other$difference = other.getDifference();
        if (this$difference == null ? other$difference != null : !this$difference.equals(other$difference)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $companyName = this.getCompanyName();
        result = result * PRIME + ($companyName == null ? 43 : $companyName.hashCode());
        final Object $noOfTransactions = this.getNoOfTransactions();
        result = result * PRIME + ($noOfTransactions == null ? 43 : $noOfTransactions.hashCode());
        final Object $maxPrice = this.getMaxPrice();
        result = result * PRIME + ($maxPrice == null ? 43 : $maxPrice.hashCode());
        final Object $minPrice = this.getMinPrice();
        result = result * PRIME + ($minPrice == null ? 43 : $minPrice.hashCode());
        final Object $closingPrice = this.getClosingPrice();
        result = result * PRIME + ($closingPrice == null ? 43 : $closingPrice.hashCode());
        final Object $tradedShares = this.getTradedShares();
        result = result * PRIME + ($tradedShares == null ? 43 : $tradedShares.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $previousClosing = this.getPreviousClosing();
        result = result * PRIME + ($previousClosing == null ? 43 : $previousClosing.hashCode());
        final Object $difference = this.getDifference();
        result = result * PRIME + ($difference == null ? 43 : $difference.hashCode());
        return result;
    }

    public String toString() {
        return "TodaysPriceEntry(companyName=" +
            this.getCompanyName() +
            ", noOfTransactions=" +
            this.getNoOfTransactions() +
            ", maxPrice=" +
            this.getMaxPrice() +
            ", minPrice=" +
            this.getMinPrice() +
            ", closingPrice=" +
            this.getClosingPrice() +
            ", tradedShares=" +
            this.getTradedShares() +
            ", amount=" +
            this.getAmount() +
            ", previousClosing=" +
            this.getPreviousClosing() +
            ", difference=" +
            this.getDifference() +
            ")";
    }

    public static TodaysPriceEntryBuilder builder() {
        return new TodaysPriceEntryBuilder();
    }

    public static class TodaysPriceEntryBuilder {

        private String companyName;
        private Integer noOfTransactions;
        private BigDecimal maxPrice;
        private BigDecimal minPrice;
        private BigDecimal closingPrice;
        private Integer tradedShares;
        private BigDecimal amount;
        private BigDecimal previousClosing;
        private BigDecimal difference;

        TodaysPriceEntryBuilder() {
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder noOfTransactions(Integer noOfTransactions) {
            this.noOfTransactions = noOfTransactions;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder maxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder minPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder closingPrice(BigDecimal closingPrice) {
            this.closingPrice = closingPrice;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder tradedShares(Integer tradedShares) {
            this.tradedShares = tradedShares;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder previousClosing(BigDecimal previousClosing) {
            this.previousClosing = previousClosing;
            return this;
        }

        public TodaysPriceEntry.TodaysPriceEntryBuilder difference(BigDecimal difference) {
            this.difference = difference;
            return this;
        }

        public TodaysPriceEntry build() {
            return new TodaysPriceEntry(
                companyName,
                noOfTransactions,
                maxPrice,
                minPrice,
                closingPrice,
                tradedShares,
                amount,
                previousClosing,
                difference
            );
        }

        public String toString() {
            return "TodaysPriceEntry.TodaysPriceEntryBuilder(companyName=" +
                this.companyName +
                ", noOfTransactions=" +
                this.noOfTransactions +
                ", maxPrice=" +
                this.maxPrice +
                ", minPrice=" +
                this.minPrice +
                ", closingPrice=" +
                this.closingPrice +
                ", tradedShares=" +
                this.tradedShares +
                ", amount=" +
                this.amount +
                ", previousClosing=" +
                this.previousClosing +
                ", difference=" +
                this.difference +
                ")";
        }
    }
}