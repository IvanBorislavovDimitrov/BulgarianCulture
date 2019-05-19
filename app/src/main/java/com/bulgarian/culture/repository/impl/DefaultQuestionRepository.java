package com.bulgarian.culture.repository.impl;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.model.enity.Question;
import com.bulgarian.culture.repository.api.QuestionRepository;

import java.util.List;

public class DefaultQuestionRepository implements QuestionRepository {

    private final DatabaseHelper databaseHelper;

    public DefaultQuestionRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
        databaseHelper.populateQuestionsTable();
    }

    @Override
    public void save(Question question) {
        databaseHelper.addQuestion(question);
    }

    @Override
    public Question findById(String id) {
        return null;
    }

    @Override
    public List<Question> getQuestions() {
        return databaseHelper.getQuestionsWithCorectAnswers();
    }

    @Override
    public int getQuestionsCount() {
        return databaseHelper.getQuestionsCount();
    }

    @Override
    public Question getQuestionById(int id) {
        return databaseHelper.getQuestionById(id);
    }

    @Override
    public List<Question> getRandomQuestions(int randomQuestionIndex, int length) {
        return databaseHelper.getQuestionsBetween(randomQuestionIndex, length);
    }
}
