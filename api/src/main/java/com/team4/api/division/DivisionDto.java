package com.team4.api.division;

public class DivisionDto {

    public final long id;
    public final String name;
    public final String originalName;
    public final DirectorDto director;

    public DivisionDto(long id, String name, String originalName, DirectorDto director) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }
}