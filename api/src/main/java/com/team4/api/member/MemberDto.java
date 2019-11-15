package com.team4.api.member;

import com.team4.domain.member.Address;
import com.team4.domain.member.LicensePlate;
import com.team4.domain.member.Name;

import java.time.LocalDate;

public class MemberDto {
    private long id;
    private Name name;
    private Address address;
    private LicensePlate licensePlate;
    private String phoneNumber;
    private String email;
    private LocalDate registrationDate;

    public long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public LicensePlate getPlateNumber() {
        return licensePlate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
