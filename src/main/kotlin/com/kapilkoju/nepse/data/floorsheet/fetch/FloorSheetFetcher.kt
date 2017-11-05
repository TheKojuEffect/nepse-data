package com.kapilkoju.nepse.data.floorsheet.fetch

import com.kapilkoju.nepse.data.floorsheet.model.FloorSheet

interface FloorSheetFetcher {

    fun getFloorSheets(): List<FloorSheet>
}