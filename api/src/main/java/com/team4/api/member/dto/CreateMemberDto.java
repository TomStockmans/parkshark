package com.team4.api.member.dto;

import com.team4.domain.member.Address;
import com.team4.domain.member.LicensePlate;
import com.team4.domain.member.Name;

public class CreateMemberDto {

    private Name name;
    private Address address;
    private LicensePlate licensePlate;
    private String phoneNumber;
    private String email;
    private String membershipLevel;

    public CreateMemberDto(Name name, Address address, LicensePlate licensePlate, String phoneNumber, String email, String membershipLevel) {
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
