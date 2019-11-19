package com.team4.api.member;

import com.team4.domain.member.Address;
import com.team4.domain.member.LicensePlate;
import com.team4.domain.member.Name;

import java.time.LocalDate;

public class CreateMemberDto {

    private long id;
    private Name name;
    private Address address;
    private LicensePlate licensePlate;
    private String phoneNumber;
    private String email;
    private String membershipLevel;

    public CreateMemberDto(long id, Name name, Address address, LicensePlate licensePlate, String phoneNumber, String email, String membershipLevel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.licensePlate = licensePlate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        if (!membershipLevel.equals("SILVER") && !membershipLevel.equals("GOLD")) {
            this.membershipLevel = "BRONZE";
        } else {
            this.membershipLevel = membershipLevel;
        }
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

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
}
