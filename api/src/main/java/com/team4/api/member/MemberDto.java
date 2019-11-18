package com.team4.api.member;

import com.team4.domain.member.Address;
import com.team4.domain.member.LicensePlate;
import com.team4.domain.member.Name;

import java.time.LocalDate;

public class MemberDto {
    private Name name;
    private Address address;
    private LicensePlate licensePlate;
    private String phoneNumber;
    private String email;
    private LocalDate registrationDate;

    public MemberDto(Name name, Address address, LicensePlate licensePlate, String phoneNumber, String email, LocalDate registrationDate) {
        this.name = name;
        this.address = address;
        this.licensePlate = licensePlate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
