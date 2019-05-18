package com.bulgarian.culture.repository.api;

import com.bulgarian.culture.model.dto.QuestionViewModel;
import com.bulgarian.culture.model.enity.Question;

import java.util.List;

public interface QuestionRepository extends Repository<Question> {

    List<Question> getRandomQuestions(int randomQuestionIndex, int length);
}
