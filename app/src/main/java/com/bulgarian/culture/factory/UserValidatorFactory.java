package com.bulgarian.culture.factory;

import com.bulgarian.culture.validator.UserValidator;

public final class UserValidatorFactory {

    private static UserValidator userValidator;

    private UserValidatorFactory() {

    }

    public static UserValidator getDefaultValidator() {
        if (userValidator == null) {
            userValidator = new UserValidator();
        }
        return userValidator;
    }
}
