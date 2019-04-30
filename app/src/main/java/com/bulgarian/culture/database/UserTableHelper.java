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
    private static final String NAME_COL = "name";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    private static final int NON_SUCCESSFUL_INSERT = -1;
    private static final int NAME_COL_INDEX = 1;
    private static final int EMAIL_COL_INDEX = 2;

    public UserTableHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COL + " VARCHAR(200), " +
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
        contentValues.put(NAME_COL, user.getUsername());

        Log.d(TAG, "save: Adding " + user.getUsername() + " to " + TABLE_NAME);

        return db.insert(TABLE_NAME, null, contentValues) == NON_SUCCESSFUL_INSERT;
    }

    public List<String> findUserUsernames() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> users = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                users.add(cursor.getString(NAME_COL_INDEX));
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
}
