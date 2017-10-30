package com.kapilkoju.nepse.data.todaysprice.model

import java.math.BigDecimal

data class TodaysPriceEntry(
        val companyName: String,
        val noOfTransactions: Int,
        val maxPrice: BigDecimal,
        val minPrice: BigDecimal,
        val closingPrice: BigDecimal,
        val tradedShares: Int,
        val amount: BigDecimal,
        val previousClosing: BigDecimal,
        val difference: BigDecimal
)