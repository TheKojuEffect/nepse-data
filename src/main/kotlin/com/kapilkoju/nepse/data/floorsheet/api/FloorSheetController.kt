package com.kapilkoju.nepse.data.floorsheet.api

import com.kapilkoju.nepse.data.floorsheet.model.FloorSheet
import com.kapilkoju.nepse.data.floorsheet.fetch.FloorSheetFetcher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FloorSheetController(private val floorSheetFetcher: FloorSheetFetcher) {

    @GetMapping("/data/floorsheet")
    fun getFloorSheets(): List<FloorSheet>
            = floorSheetFetcher.getFloorSheets()
}
