package com.bulgarian.culture.factory;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.service.api.AnswerService;
import com.bulgarian.culture.service.impl.DefaultAnswerService;

public final class AnswerServiceFactory {

    private static AnswerService answerService;

    private AnswerServiceFactory() {

    }

    public static AnswerService getDefaultAnswerService(DatabaseHelper databaseHelper) {
        if (answerService == null) {
            answerService = new DefaultAnswerService(databaseHelper);
        }
        return answerService;
    }
}
