package com.team4.api.member.mapper;

import com.team4.api.member.dto.LicensePlateDto;
import com.team4.domain.member.LicensePlate;
import org.springframework.stereotype.Component;

@Component
public class LicensePlateMapper {
    public static LicensePlateDto toLicensePlateDto(LicensePlate licensePlate) {
        return new LicensePlateDto(licensePlate.getPlateNumber(), licensePlate.getIssuingCountry());
    }
}
