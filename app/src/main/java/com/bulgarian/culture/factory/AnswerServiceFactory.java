package com.bulgarian.culture.factory;

import com.bulgarian.culture.database.AnswerTableHelper;
import com.bulgarian.culture.service.api.AnswerService;
import com.bulgarian.culture.service.impl.DefaultAnswerService;

public final class AnswerServiceFactory {

    private AnswerServiceFactory() {

    }

    public static AnswerService getDefaultAnswerService(AnswerTableHelper answerTableHelper) {
        return new DefaultAnswerService(answerTableHelper);
    }
}
