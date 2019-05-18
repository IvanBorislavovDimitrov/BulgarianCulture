package com.bulgarian.culture.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.factory.AnswerServiceFactory;
import com.bulgarian.culture.factory.DatabaseHelperFactory;
import com.bulgarian.culture.factory.QuestionServiceFactory;
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
        DatabaseHelper databaseHelper = DatabaseHelperFactory.getDatabaseHelper(this);
        questionService = QuestionServiceFactory.getDefaultQuestionService(databaseHelper);
        answerService = AnswerServiceFactory.getDefaultAnswerService(databaseHelper);
    }

    private void showQuestion() {
        List<QuestionViewModel> questionViewModel = questionService.getRandomQuestion();
    }
}
