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

public class AnswerTableHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "answers";
    private static final String TEXT_COL = "text";
    private static final String QUESTION_ID_COL = "question_id";

    private Context context;

    public AnswerTableHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
        this.context = context;
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TEXT_COL + " VARCHAR(200), " +
                QUESTION_ID_COL + " int, " +
                "FOREIGN KEY (" + QUESTION_ID_COL + ") REFERENCES " + QuestionTableHelper.TABLE_NAME + "(" + ID_COL + "))";
        db.execSQL(createTable);
    }

    public void populateAnswerTable() {
        if (isTablePopulated()) {
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

    private boolean isTablePopulated() {
        try (Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, new String[]{})) {
            return cursor.moveToNext();
        }
    }

    private void addAnswer(Answer answer, int questionId) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEXT_COL, answer.getText());
        contentValues.put(QUESTION_ID_COL, questionId);
        Log.d(TAG, "save: Adding " + answer.getText() + " to " + TABLE_NAME);
        db.insert(TABLE_NAME, null, contentValues);
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
