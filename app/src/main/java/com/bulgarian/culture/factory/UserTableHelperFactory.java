package com.bulgarian.culture.factory;

import android.content.Context;

import com.bulgarian.culture.database.UserTableHelper;

public final class UserTableHelperFactory {

    private UserTableHelperFactory() {

    }

    public static UserTableHelper getUserTableHelper(Context context) {
        return new UserTableHelper(context);
    }
}
