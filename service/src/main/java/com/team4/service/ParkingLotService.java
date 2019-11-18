package com.team4.service;

import com.team4.domain.parkinglot.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {


    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {

        this.parkingLotRepository = parkingLotRepository;

        parkingLotRepository.save(new ParkingLot("pk1", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new Adress("elfnovemberlaan", "82", "3000, Leuven"))));
        parkingLotRepository.save(new ParkingLot("pk2", ParkingCategory.ABOVE_GROUND_MEMBER, 7,
                new ContactPerson("nick.st@g.be", "0496858585", null,
                        new Adress("groove street", "69", "3010, Kessel-lo"))));
    }

    public List<ParkingLot> getAll(){
        return parkingLotRepository.findAll();
    }
}
