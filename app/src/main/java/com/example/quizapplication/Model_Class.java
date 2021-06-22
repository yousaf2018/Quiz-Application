package com.example.quizapplication;

public class Model_Class {
    private int quiz_icon;
    private String quiz_title;

    Model_Class(int quiz_icon,String quiz_title){
        this.quiz_icon = quiz_icon;
        this.quiz_title = quiz_title;
    }

    public int getQuiz_icon() {
        return quiz_icon;
    }

    public void setQuiz_icon(int quiz_icon) {
        this.quiz_icon = quiz_icon;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }
}
