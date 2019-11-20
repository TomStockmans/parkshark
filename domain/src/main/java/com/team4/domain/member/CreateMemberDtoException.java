package com.team4.domain.member;

public class CreateMemberDtoException extends RuntimeException {
    public CreateMemberDtoException(String errorMessage) {
        super(errorMessage);
    }
}
