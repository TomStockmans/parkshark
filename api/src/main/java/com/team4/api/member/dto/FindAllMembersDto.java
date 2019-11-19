package com.team4.api.member.dto;

import java.time.LocalDate;

public class FindAllMembersDto {
    private long id;
    private NameDto fullName;
    private String plateNumber;
    private String phoneNumber;
    private String email;
    private LocalDate registrationDate;

    public FindAllMembersDto(long id, NameDto fullName, String plateNumber, String phoneNumber, String email, LocalDate registrationDate) {
        this.id = id;
        this.fullName = fullName;
        this.plateNumber = plateNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public NameDto getName() {
        return fullName;
    }

    public void setName(NameDto fullName) {
        this.fullName = fullName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
