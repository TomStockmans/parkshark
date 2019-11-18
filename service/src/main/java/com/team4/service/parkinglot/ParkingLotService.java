package com.team4.service.parkinglot;

import com.team4.domain.parkinglot.ParkingLot;
import com.team4.domain.parkinglot.ParkingLotException;
import com.team4.domain.parkinglot.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {


    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {

        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLot> getAll() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot getById(long id) {
        return parkingLotRepository.findById(id).orElseThrow(() -> new ParkingLotException("no parkinglot found for this id"));
    }

}
