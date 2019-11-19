package com.team4.api.member.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AddressDto {
    private String fullAddress;

    @JsonCreator
    public AddressDto(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
