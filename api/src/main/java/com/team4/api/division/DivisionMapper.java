package com.team4.api.division;

import com.team4.domain.division.Director;
import com.team4.domain.division.Division;

public class DivisionMapper {

    public static Division mapToDivision(DivisionDto divisionDto){
        return new Division(divisionDto.name, divisionDto.originalName, new Director(divisionDto.director.firstName, divisionDto.director.lastName));
    }

    public static DivisionDto mapToDivisionDto(Division division){
        return new DivisionDto(
                division.getName(),
                division.getOriginalName(),
                new DirectorDto(
                        division.getDirector().getFirstName(),
                        division.getDirector().getLastName()));
    }
}
