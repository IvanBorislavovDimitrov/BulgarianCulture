package com.bulgarian.culture.factory;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.service.api.QuestionService;
import com.bulgarian.culture.service.impl.DefaultQuestionService;

public final class QuestionServiceFactory {

    private static QuestionService questionService;

    private QuestionServiceFactory() {

    }

    public static QuestionService getDefaultQuestionService(DatabaseHelper questionTableHelper) {
        if (questionService == null) {
            questionService = new DefaultQuestionService(questionTableHelper);
        }
        return questionService;
    }
}
