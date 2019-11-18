package com.team4.api.parkinglot;

import com.team4.domain.parkinglot.ParkingCategory;

import java.util.Objects;

public class SingleParkingLotDtoResponse {

    public long id;
    public String name;
    public ParkingCategory parkingCategory;
    public int capacity;
    public ContactPerson contactPerson;

    public SingleParkingLotDtoResponse(long id, String name, ParkingCategory parkingCategory, int capacity, ContactPerson contactPerson) {
        this.id = id;
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
    }

    public static class ContactPerson{
        public long id;
        public String email;
        public String mobilePhoneNumber;
        public String telephoneNumber;
        public Adress adress;

        public ContactPerson(long id, String email, String mobilePhoneNumber, String telephoneNumber, Adress adress) {
            this.id = id;
            this.email = email;
            this.mobilePhoneNumber = mobilePhoneNumber;
            this.telephoneNumber = telephoneNumber;
            this.adress = adress;


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
                    Objects.equals(adress, that.adress);
        }

        public static class Adress{
            public String street;
            public String streetNr;
            public String postalCode;

            public Adress(String street, String streetNr, String postalCode) {
                this.street = street;
                this.streetNr = streetNr;
                this.postalCode = postalCode;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Adress adress = (Adress) o;
                return Objects.equals(street, adress.street) &&
                        Objects.equals(streetNr, adress.streetNr) &&
                        Objects.equals(postalCode, adress.postalCode);
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
                Objects.equals(contactPerson, that.contactPerson);
    }
}
