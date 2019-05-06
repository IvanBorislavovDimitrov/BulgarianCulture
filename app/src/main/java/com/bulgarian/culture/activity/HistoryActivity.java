package com.bulgarian.culture.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.factory.QuestionServiceFactory;
import com.bulgarian.culture.factory.QuestionTableHelperFactory;
import com.bulgarian.culture.service.api.QuestionService;

public class HistoryActivity extends AppCompatActivity {

    private QuestionService questionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture);
        initDependencies();

    }

    private void initDependencies() {
        this.questionService = QuestionServiceFactory
                .getDefaultQuestionService(QuestionTableHelperFactory
                        .getDefaultQuestionTableHelper(this));
    }
}
