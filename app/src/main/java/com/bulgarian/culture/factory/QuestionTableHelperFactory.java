package com.bulgarian.culture.factory;

import android.content.Context;

import com.bulgarian.culture.database.QuestionTableHelper;

public final class QuestionTableHelperFactory {

    private QuestionTableHelperFactory() {

    }

    public static QuestionTableHelper getDefaultQuestionTableHelper(Context context) {
        return new QuestionTableHelper(context);
    }
}
