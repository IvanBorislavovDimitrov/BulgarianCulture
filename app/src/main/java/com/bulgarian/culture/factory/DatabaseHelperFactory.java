package com.bulgarian.culture.factory;

import android.content.Context;

import com.bulgarian.culture.database.DatabaseHelper;

public final class DatabaseHelperFactory {

    private DatabaseHelper databaseHelper;

    private DatabaseHelperFactory() {

    }

    public synchronized static DatabaseHelper getDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }
}
