package com.bulgarian.culture.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bulgarian.culture.model.enity.User;

import java.util.ArrayList;
import java.util.List;

public class UserTableHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "users";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final int NON_SUCCESSFUL_INSERT = -1;

    public UserTableHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COL + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean save(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL, user.getUsername());

        Log.d(TAG, "addData: Adding " + user.getUsername() + " to " + TABLE_NAME);

        return db.insert(TABLE_NAME, null, contentValues) == NON_SUCCESSFUL_INSERT;
    }

    public List<String> getUsers() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> users = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                users.add(cursor.getString(1));
            }
        }

        return users;
    }
}
