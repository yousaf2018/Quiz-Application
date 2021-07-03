package com.example.quizapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.R)
public class quiz {
    private String id;
    private String title;
    private Map<String,Questions> questions = Map.of();
    public  quiz(){

    }
    public quiz(String id, String title, Map<String, Questions> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Questions> questions) {
        this.questions = questions;
    }
}
