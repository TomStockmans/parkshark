package com.team4.domain.member;

import java.time.LocalDate;

public class Member extends Person {
    private final LicensePlate licensePlate;
    private final LocalDate registrationDate;

    public Member(Name name, Address address, String phoneNumber, String email, LicensePlate licensePlate, LocalDate registrationDate) {
        super(name, address, phoneNumber, email);
        this.licensePlate = licensePlate;
        this.registrationDate = registrationDate;
    }
}
