package com.kapilkoju.nepse.data.stocklive.service

import com.kapilkoju.nepse.data.stocklive.model.StockLive
import com.kapilkoju.nepse.data.stocklive.model.StockLiveId
import org.springframework.data.jpa.repository.JpaRepository

interface StockLiveRepo : JpaRepository<StockLive, StockLiveId>