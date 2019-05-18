package com.bulgarian.culture.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.factory.AnswerRepositoryFactory;
import com.bulgarian.culture.factory.AnswerServiceFactory;
import com.bulgarian.culture.factory.QuestionServiceFactory;
import com.bulgarian.culture.factory.QuestionTableHelperFactory;
import com.bulgarian.culture.model.dto.QuestionViewModel;
import com.bulgarian.culture.service.api.AnswerService;
import com.bulgarian.culture.service.api.QuestionService;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private QuestionService questionService;
    private AnswerService answerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initDependencies();
        showQuestion();
    }

    // The order of initialization must not be changed (hack...)
    private void initDependencies() {
        questionService = QuestionServiceFactory
                .getDefaultQuestionService(QuestionTableHelperFactory
                        .getDefaultQuestionTableHelper(this));
        answerService = AnswerServiceFactory
                .getDefaultAnswerService(AnswerRepositoryFactory
                        .getDefaultAnswerRepository(this));
    }

    private void showQuestion() {
        QuestionViewModel questionViewModel = questionService.getRandomQuestion();
    }
}
