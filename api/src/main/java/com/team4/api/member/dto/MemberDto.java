package com.team4.api.member.dto;

import java.time.LocalDate;

public class MemberDto {
    private long id;
    private NameDto fullName;
    private AddressDto addressDto;
    private LicensePlateDto licensePlate;
    private String phoneNumber;
    private String email;
    private LocalDate registrationDate;
    private String membershipLevel;

    public MemberDto(long id, NameDto fullName, AddressDto addressDto, LicensePlateDto licensePlate, String phoneNumber, String email, String membershipLevel) {
        this.id = id;
        this.fullName = fullName;
        this.addressDto = addressDto;
        this.licensePlate = licensePlate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registrationDate = LocalDate.now();
        if (!membershipLevel.equals("SILVER") && !membershipLevel.equals("GOLD")) {
            this.membershipLevel = "BRONZE";
        } else {
            this.membershipLevel = membershipLevel;
        }
    }

    public NameDto getName() {
        return fullName;
    }

    public void setName(NameDto name) {
        this.fullName = name;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public LicensePlateDto getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlateDto licensePlate) {
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

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public long getId() {
        return id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
