package com.team4.api.division;

import java.util.Objects;

public class DirectorDto {
    public final String firstName;
    public final String lastName;

    public DirectorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorDto that = (DirectorDto) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }
}
