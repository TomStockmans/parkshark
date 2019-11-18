package com.team4.api.parkinglot;

import com.team4.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ParkingLotController.PARKING_LOT_CONTROLLER_RESOURCE_URL)
public class ParkingLotController {

    public static final String PARKING_LOT_CONTROLLER_RESOURCE_URL = "/parkinglots";

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public List<ParkingLotResponseDto> getAll(){

        return parkingLotService.getAll()
                .stream()
                .map(ParkingLotMapper::objectToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SingleParkingLotDtoResponse getById(@PathVariable long id){
        return ParkingLotMapper.objectToSingleParkingLotDtoResponse(parkingLotService.getById(id));
    }

}
