package com.team4.api.division;

import java.util.Objects;

public class DivisionDto {

    public final String name;
    public final String originalName;
    public final DirectorDto director;

    public DivisionDto(String name, String originalName, DirectorDto director) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisionDto that = (DivisionDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(originalName, that.originalName) &&
                Objects.equals(director, that.director);
    }


}
