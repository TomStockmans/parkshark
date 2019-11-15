package com.team4.domain.member;

public class LicensePlate {
    private final String plateNumber;
    private final String issuingCountry;

    public LicensePlate(String plateNumber, String issuingCountry) {
        this.plateNumber = plateNumber;
        this.issuingCountry = issuingCountry;
    }
}
