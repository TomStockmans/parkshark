package com.team4.api.member.dto;

import com.team4.domain.member.*;

public class CreateMemberDtoValidator {
    public static void isValidCreateMemberDto(CreateMemberDto createMemberDto) {
        isEmailValid(createMemberDto.getEmail());
        isNameValid(createMemberDto.getName());
        isAddressValid(createMemberDto.getAddress());
        isLicensePlateValid(createMemberDto.getLicensePlate());
        isPhoneNumberValid(createMemberDto.getPhoneNumber());
    }

    private static void isPhoneNumberValid(String phoneNumber) {
        if(!phoneNumber.matches("0?[0-9]{9}")) {
            throw new CreateMemberDtoException("Invalid phone number");
        }
    }

    private static void isLicensePlateValid(LicensePlate licensePlate) {
        if(licensePlate == null || licensePlate.getPlateNumber().isEmpty() || licensePlate.getIssuingCountry().isEmpty()) {
            throw new CreateMemberDtoException("Please provide a license plate");
        }
    }

    private static void isAddressValid(Address address) {
        if(address == null || address.getCountry().isEmpty() || address.getHouseNumber().isEmpty() || address.getStreetName().isEmpty() || address.getZipCode().isEmpty()) {
            throw new CreateMemberDtoException("Please provide a valid address");
        }
    }

    private static void isNameValid(Name name) {
        if(name == null || name.getFirstName().isEmpty() || name.getLastName().isEmpty()) {
            throw new CreateMemberDtoException("Please provide a name");
        }
    }

    private static void isEmailValid(String email) {
        if(!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new CreateMemberDtoException("Invalid email address");
        }
    }
}
