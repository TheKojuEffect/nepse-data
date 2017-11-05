package com.kapilkoju.nepse.data.floorsheet.model

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "floor_sheet")
data class FloorSheet(
        @Id
        val contractNo: Long,
        val stockSymbol: String,
        val buyerBroker: String,
        val sellerBroker: String,
        val quantity: Int,
        val rate: BigDecimal,
        val amount: BigDecimal
)