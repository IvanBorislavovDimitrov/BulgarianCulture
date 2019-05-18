package com.bulgarian.culture.model.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel {

    private String text;
    private List<String> answers;
    private String trueAnswer;

    public QuestionViewModel() {
        answers = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}
