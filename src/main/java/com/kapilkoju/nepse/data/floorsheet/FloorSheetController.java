package com.kapilkoju.nepse.data.floorsheet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FloorSheetController {

    private final FloorSheetService floorSheetService;

    public FloorSheetController(FloorSheetService floorSheetService) {
        this.floorSheetService = floorSheetService;
    }

    @GetMapping("/data/floorsheet")
    public Flux<FloorSheetEntry> getFloorSheet() {
        // TODO: Make non-blocking
        return Flux.fromIterable(floorSheetService.getFloorSheet());
    }
}
