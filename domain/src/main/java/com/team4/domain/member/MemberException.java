package com.team4.domain.member;

public class MemberException extends RuntimeException {
    public MemberException(String errorMessage) {
        super(errorMessage);
    }
}
