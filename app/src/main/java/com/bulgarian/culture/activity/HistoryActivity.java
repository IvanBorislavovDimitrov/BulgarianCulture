package com.bulgarian.culture.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.factory.AnswerRepositoryFactory;
import com.bulgarian.culture.factory.AnswerServiceFactory;
import com.bulgarian.culture.factory.QuestionServiceFactory;
import com.bulgarian.culture.factory.QuestionTableHelperFactory;
import com.bulgarian.culture.repository.api.AnswerRepository;
import com.bulgarian.culture.service.api.AnswerService;
import com.bulgarian.culture.service.api.QuestionService;

public class HistoryActivity extends AppCompatActivity {

    private QuestionService questionService;
    private AnswerService answerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture);
        initDependencies();

    }

    // The order of initializing must not be changed (hack...)
    private void initDependencies() {
        questionService = QuestionServiceFactory
                .getDefaultQuestionService(QuestionTableHelperFactory
                        .getDefaultQuestionTableHelper(this));
        answerService = AnswerServiceFactory
                .getDefaultAnswerService(AnswerRepositoryFactory
                        .getDefaultAnswerRepository(this));
    }
}
