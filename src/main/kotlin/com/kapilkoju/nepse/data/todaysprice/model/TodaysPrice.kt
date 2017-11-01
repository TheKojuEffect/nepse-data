package com.kapilkoju.nepse.data.todaysprice.model

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

data class TodaysPriceId(val date: LocalDate? = null, val companyName: String? = null) : Serializable

@Entity
@Table(name = "todays_price")
@IdClass(TodaysPriceId::class)
data class TodaysPrice(
        @Id
        val date: LocalDate,
        @Id
        val companyName: String,
        val noOfTransactions: Int,
        val maxPrice: BigDecimal,
        val minPrice: BigDecimal,
        val closingPrice: BigDecimal,
        val tradedShares: Int,
        val amount: BigDecimal,
        val previousClosing: BigDecimal,
        val difference: BigDecimal,
        val valueDate: LocalDate,
        val valueTime: LocalTime
)