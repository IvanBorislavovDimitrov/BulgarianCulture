package com.bulgarian.culture.model.enity;

import java.util.ArrayList;
import java.util.List;

public class Question extends IdEntity {
    private String text;
    private List<Answer> answers = new ArrayList<>();
    private Answer trueAnswer;

    public Question() {
        // Required by ObjectMapper (Jackson)
    }

    public Question(String text, Answer trueAnswer) {
        this.text = text;
        this.trueAnswer = trueAnswer;
    }

    public Answer getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(Answer trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
