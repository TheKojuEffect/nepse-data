package com.kapilkoju.nepse.data.floorsheet

import java.math.BigDecimal

data class FloorSheetEntry(
        val contractNo: Long,
        val stockSymbol: String,
        val buyerBroker: Int,
        val sellerBroker: Int,
        val quantity: Int,
        val rate: BigDecimal,
        val amount: BigDecimal
)