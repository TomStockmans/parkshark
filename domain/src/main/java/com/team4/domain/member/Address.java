package com.team4.domain.member;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private final String streetName;
    private final String houseNumber;
    private final String zipCode;
    private final String country;

    public Address(String streetName, String houseNumber, String zipCode, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.country = country;
    }

}
