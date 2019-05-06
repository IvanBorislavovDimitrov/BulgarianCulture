package com.bulgarian.culture.factory;

import com.bulgarian.culture.database.QuestionTableHelper;
import com.bulgarian.culture.service.api.QuestionService;
import com.bulgarian.culture.service.impl.DefaultQuestionService;

public final class QuestionServiceFactory {

    private QuestionServiceFactory() {

    }

    public static QuestionService getDefaultQuestionService(QuestionTableHelper questionTableHelper) {
        return new DefaultQuestionService(questionTableHelper);
    }
}
