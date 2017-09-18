package com.kapilkoju.nepse.data.floorsheet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloorSheetController {

    private final FloorSheetService floorSheetService;

    public FloorSheetController(FloorSheetService floorSheetService) {
        this.floorSheetService = floorSheetService;
    }

    @GetMapping("/data/floorsheet")
    public List<FloorSheetEntry> getFloorSheet() {
        return floorSheetService.getFloorSheet();
    }
}
