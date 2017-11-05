package com.kapilkoju.nepse.data.stocklive.model

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

data class StockLiveId(val valueDate: LocalDate? = null,
                       val valueTime: LocalDate?,
                       val symbol: String? = null) : Serializable

@Entity
@Table(name = "stock_live")
@IdClass(StockLiveId::class)
data class StockLive(
        @Id val valueDate: LocalDate,
        @Id val valueTime: LocalTime,
        @Id val symbol: String,
        val ltp: BigDecimal,
        val ltv: BigDecimal,
        val pointChange: BigDecimal,
        val percentageChange: BigDecimal,
        val open: BigDecimal,
        val high: BigDecimal,
        val low: BigDecimal,
        val volume: Int,
        val previousClosing: BigDecimal)