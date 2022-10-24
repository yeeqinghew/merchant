package com.example.merchant;

public class Quiz {
    public String points;
    public String questId;
    public String quizID;
    public String quizType;

    public Quiz() {
    }

    public Quiz(String points, String questId, String quizID, String quizType) {
        this.points = points;
        this.questId = questId;
        this.quizID = quizID;
        this.quizType = quizType;
    }

    public String getPoints() {
        return points;
    }

    public String getQuestId() {
        return questId;
    }

    public String getQuizID() {
        return quizID;
    }

    public String getQuizType() {
        return quizType;
    }


}
