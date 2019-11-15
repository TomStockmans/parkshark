package com.team4.api.parkinglot;

import com.team4.domain.parkinglot.ParkingLot;

public class ParkingLotMapper {

    public static ParkingLotResponseDto objectToResponseDto(ParkingLot parkingLot){
        return new ParkingLotResponseDto(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCapacity(),
                parkingLot.getContactPerson().getEmail(),
                parkingLot.getContactPerson().getTelephoneNumber(),
                parkingLot.getContactPerson().getMobilePhoneNumber()
        );
    }
}
