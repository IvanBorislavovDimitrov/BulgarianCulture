package com.bulgarian.culture.factory;

import com.bulgarian.culture.database.UserTableHelper;
import com.bulgarian.culture.service.api.UserService;
import com.bulgarian.culture.service.impl.DefaultUserService;

public final class UserServiceFactory {

    private UserServiceFactory() {
    }

    public static UserService getDefaultUserService(UserTableHelper userTableHelper) {
        return new DefaultUserService(userTableHelper);
    }
}
