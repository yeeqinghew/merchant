package com.example.merchant;

import java.util.ArrayList;

public class Questions {
    public String quizId;
    public Question question;

    public Questions(String quizId, Question question) {
        this.quizId = quizId;
        this.question = question;
    }

    public String getQuizId() {
        return quizId;
    }

    public Question getQuestion() {
        return question;
    }
}
