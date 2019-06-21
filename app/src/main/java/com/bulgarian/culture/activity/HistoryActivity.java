package com.bulgarian.culture.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bulgarian.culture.R;
import com.bulgarian.culture.constants.Constants;
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
    private SectionsStatePagerAdapter adapter;
    private ViewPager viewPager;
    private int currentQuestionNumber = 0;
    private int correctAnsweredQuestions = 0;
    private int incorrectAnsweredQuestions = 0;
    private int totalAnsweredQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initDependencies();
        questionService.clearQuestionsUsed();
        showQuestion();
        TextView correctAnswers = findViewById(R.id.correctAnswersCount);
        correctAnswers.setText(correctAnswers.getText() + ": " + 0 + "/" + Constants.TOTAL_QUESTIONS);
        TextView incorrectAnswers = findViewById(R.id.incorrectAnswersCount);
        incorrectAnswers.setText(incorrectAnswers.getText() + ": " + 0 + "/" + Constants.MAX_INCORRECT_QUESTIONS);
    }

    // The order of initialization must not be changed (hack...)
    private void initDependencies() {
        DatabaseHelper databaseHelper = DatabaseHelperFactory.getDatabaseHelper(this);
        questionService = QuestionServiceFactory.getDefaultQuestionService(databaseHelper);
        answerService = AnswerServiceFactory.getDefaultAnswerService(databaseHelper);
    }

    private void showQuestion() {
        sectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        fetchNewQuestions();
        viewPager.setAdapter(adapter);
    }

    public void fetchNewQuestions() {
        List<QuestionViewModel> questionViewModels = questionService.getRandomQuestions();
        adapter.clearList();
        for (QuestionViewModel questionViewModel : questionViewModels) {
            adapter.addFragment(new QuestionsFragment(questionViewModel));
        }
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(int currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public int getCorrectAnsweredQuestions() {
        return correctAnsweredQuestions;
    }

    public void setCorrectAnsweredQuestions(int correctAnsweredQuestions) {
        this.correctAnsweredQuestions = correctAnsweredQuestions;
    }

    public int getIncorrectAnsweredQuestions() {
        return incorrectAnsweredQuestions;
    }

    public void setIncorrectAnsweredQuestions(int incorrectAnsweredQuestions) {
        this.incorrectAnsweredQuestions = incorrectAnsweredQuestions;
    }

    public int getTotalAnsweredQuestions() {
        return totalAnsweredQuestions;
    }

    public void setTotalAnsweredQuestions(int totalAnsweredQuestions) {
        this.totalAnsweredQuestions = totalAnsweredQuestions;
    }

    public void finishWithSuccess() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    startActivity(new Intent(this, HistoryActivity.class));
                    finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    break;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You won! New Game?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    public void finishWithLoss() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    startActivity(new Intent(this, HistoryActivity.class));
                    finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    break;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You lost! New Game?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    public void updateCorrectQuestions(int currentCount) {
        TextView correctAnswers = findViewById(R.id.correctAnswersCount);
        String info = correctAnswers.getText().toString().split(": ")[0];
        correctAnswers.setText(info + ": " + currentCount + "/" + Constants.TOTAL_QUESTIONS);
    }

    public void updateIncorrectQuestions(int currentCount) {
        TextView incorrectAnswers = findViewById(R.id.incorrectAnswersCount);
        String info = incorrectAnswers.getText().toString().split(": ")[0];
        incorrectAnswers.setText(info + ": " + currentCount + "/" + Constants.MAX_INCORRECT_QUESTIONS);
    }
}
