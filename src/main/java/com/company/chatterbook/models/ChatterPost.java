package com.company.chatterbook.models;

public class ChatterPost {
    //create text var
    private String text;

    public ChatterPost(String text) {
        this.text = text;
    }

    // create getter and setter methods
    public String getText() {
       return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
