package com.example.merchant;

import java.util.ArrayList;

public class Questions {
    public String quizId;
    public String quizType;
    public Question question;

    public Questions(String quizId, String quizType, Question question) {
        this.quizId = quizId;
        this.quizType = quizType;
        this.question = question;
    }

    public String getQuizId() {
        return quizId;
    }

    public String getQuizType() {
        return quizType;
    }

    public Question getQuestion() {
        return question;
    }
}
