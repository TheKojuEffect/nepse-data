package com.kapilkoju.nepse.data.floorsheet;

import reactor.core.publisher.Flux;

public interface FloorSheetService {

    Flux<FloorSheetEntry> getFloorSheet();
}