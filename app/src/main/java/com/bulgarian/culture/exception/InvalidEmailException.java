package com.bulgarian.culture.exception;

public class InvalidEmailException extends UserException {

    private static final String INVALID_EMAIL = "Email not valid";

    public InvalidEmailException() {
        super(INVALID_EMAIL);
    }
}
