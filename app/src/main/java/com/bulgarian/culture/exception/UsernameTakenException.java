package com.bulgarian.culture.exception;

public class UsernameTakenException extends UserException {

    private static final String USERNAME_TAKEN = "Username is taken";

    public UsernameTakenException() {
        super(USERNAME_TAKEN);
    }
}
