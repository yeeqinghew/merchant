package com.example.merchant;

public class Quiz {
    public String description;
    public String points;
    public String questId;
    public String questTitle;
    public String questionbankurl;
    public String quizType;

    public Quiz() {
    }

    public Quiz(String description, String points, String questId, String questTitle, String questionbankurl, String quizType) {
        this.description = description;
        this.points = points;
        this.questId = questId;
        this.questTitle = questTitle;
        this.questionbankurl = questionbankurl;
        this.quizType = quizType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public String getQuestionbankurl() {
        return questionbankurl;
    }

    public void setQuestionbankurl(String questionbankurl) {
        this.questionbankurl = questionbankurl;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }
}
