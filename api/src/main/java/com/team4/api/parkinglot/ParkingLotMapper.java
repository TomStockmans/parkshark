package com.team4.api.parkinglot;

import com.team4.api.division.DirectorDto;
import com.team4.api.division.DivisionDto;
import com.team4.domain.division.Division;
import com.team4.domain.parkinglot.ContactPerson;
import com.team4.domain.parkinglot.ParkingLot;

public class ParkingLotMapper {
    //String name, ParkingCategory parkingCategory, int capacity, ContactPerson contactPerson, Division division
    public static ParkingLot dtoToParkingLotObject(CreateParkingLotDto createParkingLotDto, Division division){
        return new ParkingLot(
                createParkingLotDto.name,
                createParkingLotDto.parkingCategory,
                createParkingLotDto.capacity,
                new ContactPerson(
                        createParkingLotDto.contactPerson.email,
                        createParkingLotDto.contactPerson.mobilePhoneNumber,
                        createParkingLotDto.contactPerson.telephoneNumber,
                        new com.team4.domain.parkinglot.Address(
                                createParkingLotDto.contactPerson.address.street,
                                createParkingLotDto.contactPerson.address.streetNr,
                                createParkingLotDto.contactPerson.address.postalCode
                        )
                ),
                division
        );
    }

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
                        new SingleParkingLotDtoResponse.ContactPerson.Address(
                                parkingLot.getContactPerson().getAddress().getStreet(),
                                parkingLot.getContactPerson().getAddress().getStreetNr(),
                                parkingLot.getContactPerson().getAddress().getPostalCode()
                                )
                ),
                new DivisionDto(
                        parkingLot.getDivision().getId(),
                        parkingLot.getDivision().getName(),
                        parkingLot.getDivision().getOriginalName(),
                        new DirectorDto(
                                parkingLot.getDivision().getDirector().getFirstName(),
                                parkingLot.getDivision().getDirector().getLastName()
                        ))
        );
    }
}
