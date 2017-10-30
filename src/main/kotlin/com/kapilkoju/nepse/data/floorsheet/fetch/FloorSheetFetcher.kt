package com.kapilkoju.nepse.data.floorsheet.fetch

import com.kapilkoju.nepse.data.floorsheet.model.FloorSheetEntry

interface FloorSheetFetcher {

    fun getFloorSheet(): List<FloorSheetEntry>
}