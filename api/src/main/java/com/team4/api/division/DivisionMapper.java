package com.team4.api.division;

import com.team4.domain.division.Director;
import com.team4.domain.division.Division;

public class DivisionMapper {

    public static Division mapToDivision(CreateDivisionDto divisionDto){
        return new Division(divisionDto.name, divisionDto.originalName, new Director(divisionDto.director.firstName, divisionDto.director.lastName), null);
    }

    public static Division mapToDivision(CreateDivisionDto divisionDto, Division parent){
        return new Division(divisionDto.name, divisionDto.originalName, new Director(divisionDto.director.firstName, divisionDto.director.lastName), parent);
    }

    public static DivisionDto mapToDivisionDto(Division division){
        return new DivisionDto(
                division.getId(),
                division.getName(),
                division.getOriginalName(),
                new DirectorDto(
                        division.getDirector().getFirstName(),
                        division.getDirector().getLastName()));
    }
}
