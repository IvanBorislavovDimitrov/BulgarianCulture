package com.bulgarian.culture.exception;

public class InvalidUsernameException extends UserException {

    private static final String INVALID_USERNAME = "Username must be at least 2 symbols long";

    public InvalidUsernameException() {
        super(INVALID_USERNAME);
    }
}
