package com.kapilkoju.nepse.data.floorsheet

interface FloorSheetService {

    fun getFloorSheet(): List<FloorSheetEntry>
}