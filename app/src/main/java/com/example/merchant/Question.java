package com.example.merchant;

import java.util.ArrayList;

public class Question {
    public String questionTitle;
    public ArrayList questionOptions;
    public String questionAnswer;

    public Question(String questionTitle, ArrayList questionOptions, String questionAnswer) {
        this.questionTitle = questionTitle;
        this.questionOptions = questionOptions;
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public ArrayList getQuestionOptions() {
        return questionOptions;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }
}
