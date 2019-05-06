package com.bulgarian.culture.model.enity;

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
}
