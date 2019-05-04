package com.bulgarian.culture.exception;

public class InvalidEmailException extends UserException {

    private static final String INVALID_EMAIL = "The email is not valid";

    public InvalidEmailException() {
        super(INVALID_EMAIL);
    }
}
