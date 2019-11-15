package com.team4.domain.member;

public abstract class Person {
    //TODO embedded, override attributes
    private final Name name;
    private final Address address;
    private final String phoneNumber;
    private final String email;

    public Person(Name name, Address address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
