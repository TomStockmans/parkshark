package com.team4.api.parkinglot;

import com.team4.domain.parkinglot.ParkingCategory;

public class CreateParkingLotDto {

    public String name;
    public ParkingCategory parkingCategory;
    public int capacity;
    public ContactPerson contactPerson;
    public long divisionId;


    public CreateParkingLotDto(String name, ParkingCategory parkingCategory, int capacity, ContactPerson contactPerson, long divisionId) {

        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
        this.divisionId = divisionId;
    }

    public static class ContactPerson{

        public String email;
        public String mobilePhoneNumber;
        public String telephoneNumber;
        public Address address;

        public ContactPerson( String email, String mobilePhoneNumber, String telephoneNumber, Address address) {
            this.email = email;
            this.mobilePhoneNumber = mobilePhoneNumber;
            this.telephoneNumber = telephoneNumber;
            this.address = address;
        }


        public static class Address {
            public String street;
            public String streetNr;
            public String postalCode;

            public Address(String street, String streetNr, String postalCode) {
                this.street = street;
                this.streetNr = streetNr;
                this.postalCode = postalCode;
            }
        }
    }
}
