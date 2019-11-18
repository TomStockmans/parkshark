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

    public static SingleParkingLotDtoResponse objectToSingleParkingLotDtoResponse(ParkingLot parkingLot){
        return new SingleParkingLotDtoResponse(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getParkingCategory(),
                parkingLot.getCapacity(),
                new SingleParkingLotDtoResponse.ContactPerson(
                        parkingLot.getContactPerson().getId(),
                        parkingLot.getContactPerson().getEmail(),
                        parkingLot.getContactPerson().getMobilePhoneNumber(),
                        parkingLot.getContactPerson().getTelephoneNumber(),
                        new SingleParkingLotDtoResponse.ContactPerson.Adress(
                                parkingLot.getContactPerson().getAdress().getStreet(),
                                parkingLot.getContactPerson().getAdress().getStreetNr(),
                                parkingLot.getContactPerson().getAdress().getPostalCode()
                                )
                )
        );
    }
}
