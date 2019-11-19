package com.team4.api.member.mapper;

import com.team4.api.member.dto.NameDto;
import com.team4.domain.member.Name;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {
    public static NameDto toNameDto(Name name) {
        return new NameDto(name.getFirstName() + " " + name.getLastName());
    }
}
