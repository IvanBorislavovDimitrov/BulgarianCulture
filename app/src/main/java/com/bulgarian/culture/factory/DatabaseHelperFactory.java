package com.bulgarian.culture.factory;

import android.content.Context;

import com.bulgarian.culture.database.DatabaseHelper;

public final class DatabaseHelperFactory {

    private DatabaseHelper databaseHelper;

    private DatabaseHelperFactory() {

    }

    public static DatabaseHelper getDatabaseHelper(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        return databaseHelper;
    }
}
