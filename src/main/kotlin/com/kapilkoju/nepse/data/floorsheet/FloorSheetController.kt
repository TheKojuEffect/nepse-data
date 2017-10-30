package com.kapilkoju.nepse.data.floorsheet

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FloorSheetController(private val floorSheetService: FloorSheetService) {

    @GetMapping("/data/floorsheet")
    fun getFloorSheet(): List<FloorSheetEntry>
            = floorSheetService.getFloorSheet()
}
