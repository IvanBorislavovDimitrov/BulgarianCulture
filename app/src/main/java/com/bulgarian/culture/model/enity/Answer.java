package com.bulgarian.culture.model.enity;

import androidx.annotation.NonNull;

public class Answer extends IdEntity {

    private String text;

    public Answer() {
        // Required by ObjectMapper (Jackson)
    }

    public Answer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return getText();
    }
}
