package com.bulgarian.culture.factory;

import com.bulgarian.culture.validator.UserValidator;

public final class UserValidatorFactory {

    private UserValidatorFactory() {

    }

    public static UserValidator getDefaultValidator() {
        return new UserValidator();
    }
}
