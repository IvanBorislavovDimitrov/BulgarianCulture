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

import static com.bulgarian.culture.constants.Constants.DB_VERSION;

public class UserTableHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "users";
    private static final String ID_COL = "id";
    private static final String USERNAME_COL = "username";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    private static final int NON_SUCCESSFUL_INSERT = -1;
    private static final int ID_COL_INDEX = 0;
    private static final int USERNAME_COL_INDEX = 1;
    private static final int EMAIL_COL_INDEX = 2;
    private static final int PASSWORD_COL_INDEX = 3;

    public UserTableHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_COL + " VARCHAR(200), " +
                EMAIL_COL + " VARCHAR(200), " +
                PASSWORD_COL + " VARCHAR(200))";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean save(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME_COL, user.getUsername());
        contentValues.put(EMAIL_COL, user.getEmail());
        contentValues.put(PASSWORD_COL, user.getPassword());

        Log.d(TAG, "save: Adding " + user.getUsername() + " to " + TABLE_NAME);

        return db.insert(TABLE_NAME, null, contentValues) == NON_SUCCESSFUL_INSERT;
    }

    public List<String> findUserUsernames() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> users = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                users.add(cursor.getString(USERNAME_COL_INDEX));
            }
        }

        return users;
    }

    public List<String> findUserEmails() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> emails = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                emails.add(cursor.getString(EMAIL_COL_INDEX));
            }
        }

        return emails;
    }

    public User findUserByUsername(String username) {
        SQLiteDatabase db = getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username=?", new String[]{username})) {
            if (!cursor.moveToNext()) {
                return null;
            }
            return new User.Builder()
                    .id(cursor.getString(ID_COL_INDEX))
                    .username(cursor.getString(USERNAME_COL_INDEX))
                    .email(cursor.getString(EMAIL_COL_INDEX))
                    .password(cursor.getString(PASSWORD_COL_INDEX))
                    .build();
        }
    }

}
