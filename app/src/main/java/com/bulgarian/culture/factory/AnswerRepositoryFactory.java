package com.bulgarian.culture.factory;

import android.content.Context;

import com.bulgarian.culture.database.AnswerTableHelper;

public final class AnswerRepositoryFactory {

    private AnswerRepositoryFactory() {

    }

    public static AnswerTableHelper getDefaultAnswerRepository(Context context) {
        return new AnswerTableHelper(context);
    }
}
