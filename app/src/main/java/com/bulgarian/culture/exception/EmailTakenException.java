package com.bulgarian.culture.exception;

public class EmailTakenException extends UserException {

    private static final String EMAIL_TAKEN = "Email is taken";

    public EmailTakenException() {
        super(EMAIL_TAKEN);
    }
}
