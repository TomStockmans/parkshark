package com.team4.api.member.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class NameDto {
    private String fullName;

    @JsonCreator
    public NameDto(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
