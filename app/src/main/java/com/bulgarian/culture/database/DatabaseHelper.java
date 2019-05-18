package com.bulgarian.culture.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bulgarian.culture.factory.ParserFactory;
import com.bulgarian.culture.factory.ReaderFactory;
import com.bulgarian.culture.io.Reader;
import com.bulgarian.culture.model.enity.Answer;
import com.bulgarian.culture.model.enity.Question;
import com.bulgarian.culture.model.enity.User;
import com.bulgarian.culture.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import static com.bulgarian.culture.constants.Constants.DB_VERSION;
import static com.bulgarian.culture.constants.Constants.ID_COL;
import static com.bulgarian.culture.constants.Constants.QUESTIONS_FILENAME;
import static com.bulgarian.culture.constants.Constants.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String USERS_TABLE_NAME = "users";
    private static final String USERS_USERNAME_COL = "username";
    private static final String USERS_EMAIL_COL = "email";
    private static final String USERS_PASSWORD_COL = "password";
    private static final int USERS_ID_COL_INDEX = 0;
    private static final int USERS_USERNAME_COL_INDEX = 1;
    private static final int USERS_EMAIL_COL_INDEX = 2;
    private static final int USERS_PASSWORD_COL_INDEX = 3;

    private static final String QUESTIONS_TABLE_NAME = "questions";
    private static final String QUESTIONS_TEXT_COL = "question_text";
    private static final String VALID_ANSWER_COL = "valid_answer";

    private static final String ANSWERS_TABLE_NAME = "answers";
    private static final String ANSWERS_TEXT_COL = "answer_text";
    private static final String QUESTION_ID_COL = "question_id";

    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, QUESTIONS_TABLE_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userCreateTable = "CREATE TABLE " + USERS_TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_USERNAME_COL + " VARCHAR(200), " +
                USERS_EMAIL_COL + " VARCHAR(200), " +
                USERS_PASSWORD_COL + " VARCHAR(200))";
        db.execSQL(userCreateTable);

        String questionsTableCreate = "CREATE TABLE " + QUESTIONS_TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VALID_ANSWER_COL + " VARCHAR(200), " +
                QUESTIONS_TEXT_COL + " VARCHAR(200))";
        db.execSQL(questionsTableCreate);

        String answersTableCreate = "CREATE TABLE " + ANSWERS_TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ANSWERS_TEXT_COL + " VARCHAR(200), " +
                QUESTION_ID_COL + " int, " +
                "FOREIGN KEY (" + QUESTION_ID_COL + ") REFERENCES " + DatabaseHelper.QUESTIONS_TABLE_NAME + "(" + ID_COL + "))";
        db.execSQL(answersTableCreate);
    }

    public void saveUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_USERNAME_COL, user.getUsername());
        contentValues.put(USERS_EMAIL_COL, user.getEmail());
        contentValues.put(USERS_PASSWORD_COL, user.getPassword());
        Log.d(TAG, "saveUser: Adding " + user.getUsername() + " to " + USERS_TABLE_NAME);
        db.insert(USERS_TABLE_NAME, null, contentValues);
    }

    public List<String> findUserUsernames() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> users = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                users.add(cursor.getString(USERS_USERNAME_COL_INDEX));
            }
        }
        return users;
    }

    public List<String> findUserEmails() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> emails = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                emails.add(cursor.getString(USERS_EMAIL_COL_INDEX));
            }
        }
        return emails;
    }

    public User findUserByUsername(String username) {
        SQLiteDatabase db = getWritableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME + " WHERE username=?", new String[]{username})) {
            if (!cursor.moveToNext()) {
                return null;
            }
            return new User.Builder()
                    .id(Integer.parseInt(cursor.getString(USERS_ID_COL_INDEX)))
                    .username(cursor.getString(USERS_USERNAME_COL_INDEX))
                    .email(cursor.getString(USERS_EMAIL_COL_INDEX))
                    .password(cursor.getString(USERS_PASSWORD_COL_INDEX))
                    .build();
        }
    }

    public void populateAnswerTable() {
        if (isAnswersTablePopulated()) {
            return;
        }
        Reader fileReader = ReaderFactory.getDefaultFileReader(QUESTIONS_FILENAME, context);
        Parser jsonParser = ParserFactory.getDefaultJSONParser();
        Question[] questions = jsonParser.parse(fileReader.readAll(), Question[].class);
        for (int i = 0; i < questions.length; i++) {
            for (Answer answer : questions[i].getAnswers()) {
                addAnswer(answer, i + 1);
            }
        }
    }

    private boolean isAnswersTablePopulated() {
        try (Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + ANSWERS_TABLE_NAME, new String[]{})) {
            return cursor.moveToNext();
        }
    }

    private void addAnswer(Answer answer, int questionId) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ANSWERS_TEXT_COL, answer.getText());
        contentValues.put(QUESTION_ID_COL, questionId);
        Log.d(TAG, "saveUser: Adding " + answer.getText() + " to " + ANSWERS_TABLE_NAME);
        db.insert(ANSWERS_TABLE_NAME, null, contentValues);
    }

    public void populateQuestionsTable() {
        if (isQuestionsTablePopulated()) {
            return;
        }
        Reader fileReader = ReaderFactory.getDefaultFileReader(QUESTIONS_FILENAME, context);
        Parser jsonParser = ParserFactory.getDefaultJSONParser();
        Question[] questions = jsonParser.parse(fileReader.readAll(), Question[].class);
        for (Question question : questions) {
            addQuestion(question);
        }
    }

    private boolean isQuestionsTablePopulated() {
        try (Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + QUESTIONS_TABLE_NAME, new String[]{})) {
            return cursor.moveToNext();
        }
    }

    public void addQuestion(Question question) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUESTIONS_TEXT_COL, question.getText());
        contentValues.put(VALID_ANSWER_COL, question.getTrueAnswer().getText());
        Log.d(TAG, "saveUser: Adding " + question.getText() + " to " + QUESTIONS_TABLE_NAME);
        db.insert(QUESTIONS_TABLE_NAME, null, contentValues);
    }

    public List<String> getQuestionAnswers(int questionId) {
        SQLiteDatabase db = getReadableDatabase();
        List<String> answers = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM  TABLE_NAME WHERE QQUESTION_ID_COL = ?", new String[]{String.valueOf(questionId)})) {
            while (cursor.moveToNext()) {
                answers.add(cursor.getString(1));
            }
        }
        return answers;
    }

    public List<Question> getQuestionsWithCoorectAnswers(){
        SQLiteDatabase db = getReadableDatabase();
        List<Question> questions = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM  TABLE_NAME", null )){
            while(cursor.moveToNext()){
                Question question = new Question(cursor.getString(1), new Answer(cursor.getString(2)));
                questions.add(question);
            }
        }
        return questions;
    }
    public int getQuestionsCount() {
        SQLiteDatabase db = getReadableDatabase();
        int count;
        try(Cursor cr = db.rawQuery("SELECT TEXT_COL FROM TABLE_NAME", null)){
            count = cr.getCount();
        }
        return count;
    }

    public Question getQuestionById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Question question;
        try(Cursor cr = db.rawQuery("SELECT * FROM TABLE_NAME WHERE ID_COL = ?", new String[]{String.valueOf(id)})){
            question = new Question(cr.getString(1), new Answer(cr.getString(21)));
        }

        return question;
    }

    public List<String> findQuestionsNames() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> emails = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + QUESTIONS_TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                emails.add(cursor.getString(1));
            }
        }
        return emails;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE " + QUESTIONS_TABLE_NAME);
        db.execSQL("DROP TABLE " + ANSWERS_TABLE_NAME);
    }

    public List<Question> getQuestionsBetween(int randomQuestionIndex, int length) {

        return null;
    }
}
