package com.team4.domain.member;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String country;

    public Address(String streetName, String houseNumber, String zipCode, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address() {
    }
}
