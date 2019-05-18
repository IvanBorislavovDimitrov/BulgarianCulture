package com.bulgarian.culture.factory;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.service.api.UserService;
import com.bulgarian.culture.service.impl.DefaultUserService;

public final class UserServiceFactory {

    private static UserService userService;

    private UserServiceFactory() {
    }

    public static UserService getDefaultUserService(DatabaseHelper databaseHelper) {
        if (userService == null) {
            userService = new DefaultUserService(databaseHelper);
        }
        return userService;
    }
}
