package com.bulgarian.culture.fragrment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bulgarian.culture.R;
import com.bulgarian.culture.activity.HistoryActivity;
import com.bulgarian.culture.model.dto.QuestionViewModel;

public class QuestionsFragment extends Fragment {

    private QuestionViewModel questionViewModel;

    public QuestionsFragment(QuestionViewModel questionViewModel) {
        this.questionViewModel = questionViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_fragment_layout, container, false);
        ((HistoryActivity) getActivity()).setViewPager(0);
        TextView questionTextView = view.findViewById(R.id.questionName);
        Button firstQuestionButton = view.findViewById(R.id.firstQuestionButton);
        Button secondQuestionButton = view.findViewById(R.id.secondQuestionButton);
        Button thirdQuestionButton = view.findViewById(R.id.thirdQuestionButton);
        Button fourthQuestionButton = view.findViewById(R.id.fourthQuestionButton);
        questionTextView.setText(questionViewModel.getText());
        firstQuestionButton.setText(questionViewModel.getAnswers().get(0));
        secondQuestionButton.setText(questionViewModel.getAnswers().get(1));
        thirdQuestionButton.setText(questionViewModel.getAnswers().get(2));
        fourthQuestionButton.setText(questionViewModel.getAnswers().get(3));

        Button correctAnswerButton = getCorrectAnswerButton(firstQuestionButton, secondQuestionButton, thirdQuestionButton, fourthQuestionButton, questionViewModel.getTrueAnswer());

        correctAnswerButton.setOnClickListener(l -> {
            new AlertDialog.Builder(((HistoryActivity) getActivity()))
                    .setTitle("Correct")
                    .setMessage("Congratulations, let's move on")
                    .show();
        });
        if (firstQuestionButton != correctAnswerButton) {
            firstQuestionButton.setOnClickListener(l -> {
                new AlertDialog.Builder(((HistoryActivity) getActivity()))
                        .setTitle("Incorrect")
                        .setMessage("Try again")
                        .show();
            });
        }
        if (secondQuestionButton != correctAnswerButton) {
            secondQuestionButton.setOnClickListener(l -> {
                new AlertDialog.Builder(((HistoryActivity) getActivity()))
                        .setTitle("Incorrect")
                        .setMessage("Try again")
                        .show();
            });
        }
        if (thirdQuestionButton != correctAnswerButton) {
            thirdQuestionButton.setOnClickListener(l -> {
                new AlertDialog.Builder(((HistoryActivity) getActivity()))
                        .setTitle("Incorrect")
                        .setMessage("Try again")
                        .show();
            });
        }
        if (fourthQuestionButton != correctAnswerButton) {
            fourthQuestionButton.setOnClickListener(l -> {
                new AlertDialog.Builder(((HistoryActivity) getActivity()))
                        .setTitle("Incorrect")
                        .setMessage("Try again")
                        .show();
            });
        }
        return view;
    }

    private Button getCorrectAnswerButton(Button firstQuestionButton, Button secondQuestionButton, Button thirdQuestionButton, Button fourthQuestionButton, String trueAnswer) {
        if (firstQuestionButton.getText().equals(trueAnswer)) {
            return firstQuestionButton;
        }
        if (secondQuestionButton.getText().equals(trueAnswer)) {
            return secondQuestionButton;
        }
        if (thirdQuestionButton.getText().equals(trueAnswer)) {
            return thirdQuestionButton;
        }
        if (fourthQuestionButton.getText().equals(trueAnswer)) {
            return fourthQuestionButton;
        }
        return null;
    }


}
