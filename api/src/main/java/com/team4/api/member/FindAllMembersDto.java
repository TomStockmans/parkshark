package com.team4.api.member;

import com.team4.domain.member.Name;

import java.time.LocalDate;

public class FindAllMembersDto {
    private long id;
    private Name name;
    private String plateNumber;
    private String phoneNumber;
    private String email;
    private LocalDate registrationDate;

    public FindAllMembersDto(long id, Name name, String plateNumber, String phoneNumber, String email, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.plateNumber = plateNumber;
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
