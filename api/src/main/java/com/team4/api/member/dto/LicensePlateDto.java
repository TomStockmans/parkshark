package com.team4.api.member.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class LicensePlateDto {
    private String plateNumber;
    private String issuingCountry;

    @JsonCreator
    public LicensePlateDto(String plateNumber, String issuingCountry) {
        this.plateNumber = plateNumber;
        this.issuingCountry = issuingCountry;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }
}
