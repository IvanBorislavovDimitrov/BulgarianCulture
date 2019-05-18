package com.bulgarian.culture.repository.api;

import com.bulgarian.culture.model.enity.Question;

import java.util.List;

public interface QuestionRepository extends Repository<Question> {

    List<Question> getQuestions();

    int getQuestionsCount();

    Question getQuestionById(int id);
}
