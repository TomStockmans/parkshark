package com.team4.api.parkinglot;

import com.team4.domain.parkinglot.ParkingCategory;

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

        public static class Adress{
            public String street;
            public String streetNr;
            public String postalCode;

            public Adress(String street, String streetNr, String postalCode) {
                this.street = street;
                this.streetNr = streetNr;
                this.postalCode = postalCode;
            }
        }
    }


}
