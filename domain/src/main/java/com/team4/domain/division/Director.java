package com.team4.domain.division;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Director {
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;

    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Director() {
    }
}
