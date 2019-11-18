package com.team4.api.member;

import com.team4.domain.member.Address;
import com.team4.domain.member.LicensePlate;
import com.team4.domain.member.Name;

import java.time.LocalDate;

public class CreateMemberDto {

    private Name name;
    private Address address;
    private LicensePlate licensePlate;
    private String phoneNumber;
    private String email;

    public CreateMemberDto(Name name, Address address, LicensePlate licensePlate, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.licensePlate = licensePlate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
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


}
