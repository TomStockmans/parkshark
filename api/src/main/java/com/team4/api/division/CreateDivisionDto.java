package com.team4.api.division;

public class CreateDivisionDto {
    public final String name;
    public final String originalName;
    public final DirectorDto director;

    public CreateDivisionDto(String name, String originalName, DirectorDto director) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }
}
