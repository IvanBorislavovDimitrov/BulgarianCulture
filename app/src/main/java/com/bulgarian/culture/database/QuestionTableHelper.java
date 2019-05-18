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
import com.bulgarian.culture.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import static com.bulgarian.culture.constants.Constants.DB_VERSION;
import static com.bulgarian.culture.constants.Constants.ID_COL;
import static com.bulgarian.culture.constants.Constants.QUESTIONS_FILENAME;
import static com.bulgarian.culture.constants.Constants.TAG;

public class QuestionTableHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "questions";
    private static final String TEXT_COL = "text";
    private static final String VALID_ANSWER_COL = "valid_answer";

    private Context context;

    public QuestionTableHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
        this.context = context;
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VALID_ANSWER_COL + " VARCHAR(200), " +
                TEXT_COL + " VARCHAR(200))";
        db.execSQL(createTable);
    }

    public void populateQuestionsTable() {
        if (isTablePopulated()) {
            return;
        }
        Reader fileReader = ReaderFactory.getDefaultFileReader(QUESTIONS_FILENAME, context);
        Parser jsonParser = ParserFactory.getDefaultJSONParser();
        Question[] questions = jsonParser.parse(fileReader.readAll(), Question[].class);
        for (Question question : questions) {
            addQuestion(question);
        }
    }

    private boolean isTablePopulated() {
        try (Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, new String[]{})) {
            return cursor.moveToNext();
        }
    }

    public void addQuestion(Question question) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEXT_COL, question.getText());
        contentValues.put(VALID_ANSWER_COL, question.getTrueAnswer().getText());
        Log.d(TAG, "save: Adding " + question.getText() + " to " + TABLE_NAME);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<String> findQuestionsNames() {
        SQLiteDatabase db = getWritableDatabase();
        List<String> emails = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                emails.add(cursor.getString(1));
            }
        }
        return emails;
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
