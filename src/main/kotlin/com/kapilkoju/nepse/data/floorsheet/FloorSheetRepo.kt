package com.kapilkoju.nepse.data.floorsheet

import com.kapilkoju.nepse.data.floorsheet.model.FloorSheet
import org.springframework.data.jpa.repository.JpaRepository

interface FloorSheetRepo: JpaRepository<FloorSheet, Long>