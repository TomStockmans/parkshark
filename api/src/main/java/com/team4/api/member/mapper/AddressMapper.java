package com.team4.api.member.mapper;

import com.team4.api.member.dto.AddressDto;
import com.team4.domain.member.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public static AddressDto toAddressDto(Address address) {
        return new AddressDto(String.format("%s %s, %s %s", address.getStreetName(), address.getHouseNumber(), address.getZipCode(), address.getCountry()));
    }

}
