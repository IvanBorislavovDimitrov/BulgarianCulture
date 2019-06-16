package com.bulgarian.culture.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bulgarian.culture.R;
import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.factory.AnswerServiceFactory;
import com.bulgarian.culture.factory.DatabaseHelperFactory;
import com.bulgarian.culture.factory.QuestionServiceFactory;
import com.bulgarian.culture.fragrment.QuestionsFragment;
import com.bulgarian.culture.fragrment.SectionsStatePagerAdapter;
import com.bulgarian.culture.model.dto.QuestionViewModel;
import com.bulgarian.culture.service.api.AnswerService;
import com.bulgarian.culture.service.api.QuestionService;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private QuestionService questionService;
    private AnswerService answerService;
    private SectionsStatePagerAdapter sectionsStatePagerAdapter;
    private ViewPager viewPager;

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
        List<QuestionViewModel> questionViewModels = questionService.getRandomQuestion();
        sectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        setupViewPage(viewPager, questionViewModels.get(0));
    }

    private void setupViewPage(ViewPager viewPager, QuestionViewModel questionViewModel) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new QuestionsFragment(questionViewModel), "QuestionsFragment");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }
}
