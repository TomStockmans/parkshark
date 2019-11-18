package com.team4.domain.member;

import javax.persistence.Embeddable;

@Embeddable
public class LicensePlate {
    private String plateNumber;
    private String issuingCountry;

    public LicensePlate(String plateNumber, String issuingCountry) {
        this.plateNumber = plateNumber;
        this.issuingCountry = issuingCountry;
    }

    public LicensePlate() {
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }
}
