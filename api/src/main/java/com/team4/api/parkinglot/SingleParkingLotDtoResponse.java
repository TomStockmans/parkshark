package com.team4.api.parkinglot;

import com.team4.api.division.DivisionDto;
import com.team4.domain.parkinglot.ParkingCategory;

import java.util.Objects;

public class SingleParkingLotDtoResponse {

    public long id;
    public String name;
    public ParkingCategory parkingCategory;
    public int capacity;
    public ContactPerson contactPerson;
    public DivisionDto divisionDto;


    public SingleParkingLotDtoResponse(long id, String name, ParkingCategory parkingCategory, int capacity, ContactPerson contactPerson, DivisionDto divisionDto) {
        this.id = id;
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
        this.divisionDto = divisionDto;
    }

    public static class ContactPerson{
        public long id;
        public String email;
        public String mobilePhoneNumber;
        public String telephoneNumber;
        public Address address;

        public ContactPerson(long id, String email, String mobilePhoneNumber, String telephoneNumber, Address address) {
            this.id = id;
            this.email = email;
            this.mobilePhoneNumber = mobilePhoneNumber;
            this.telephoneNumber = telephoneNumber;
            this.address = address;


        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ContactPerson that = (ContactPerson) o;
            return id == that.id &&
                    Objects.equals(email, that.email) &&
                    Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) &&
                    Objects.equals(telephoneNumber, that.telephoneNumber) &&
                    Objects.equals(address, that.address);
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

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Address address = (Address) o;
                return Objects.equals(street, address.street) &&
                        Objects.equals(streetNr, address.streetNr) &&
                        Objects.equals(postalCode, address.postalCode);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleParkingLotDtoResponse that = (SingleParkingLotDtoResponse) o;
        return id == that.id &&
                capacity == that.capacity &&
                Objects.equals(name, that.name) &&
                parkingCategory.equals(that.parkingCategory) &&
                Objects.equals(contactPerson, that.contactPerson) &&
                Objects.equals(divisionDto, that.divisionDto);
    }
}
