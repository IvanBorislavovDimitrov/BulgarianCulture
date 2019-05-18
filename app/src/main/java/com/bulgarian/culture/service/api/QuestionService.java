package com.bulgarian.culture.service.api;

import com.bulgarian.culture.model.dto.QuestionViewModel;

public interface QuestionService {
    QuestionViewModel getRandomQuestion();
}
