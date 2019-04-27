package com.bulgarian.culture.exception;

public class PasswordsMismatchException extends UserException {

    private static final String PASSWORDS_MISMATCH = "Passwords are not equal";

    public PasswordsMismatchException() {
        super(PASSWORDS_MISMATCH);
    }
}
