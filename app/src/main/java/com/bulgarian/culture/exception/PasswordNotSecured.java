package com.bulgarian.culture.exception;

public class PasswordNotSecured extends UserException {

    private static final String PASSWORD_CONSTRAINT = "Password must be at least 2 symbols long";

    public PasswordNotSecured() {
        super(PASSWORD_CONSTRAINT);
    }
}
