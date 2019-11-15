package com.team4.domain.parkinglot;

public class DomainException extends RuntimeException {

    public DomainException(String errorMessage) {
        super(errorMessage);
    }
}
