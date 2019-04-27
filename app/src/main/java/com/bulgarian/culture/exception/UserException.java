package com.bulgarian.culture.exception;

public abstract class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
