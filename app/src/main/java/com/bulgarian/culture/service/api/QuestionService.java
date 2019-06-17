package com.bulgarian.culture.service.api;

import com.bulgarian.culture.model.dto.QuestionViewModel;

import java.util.List;

public interface QuestionService {
    List<QuestionViewModel> getRandomQuestions();
}
