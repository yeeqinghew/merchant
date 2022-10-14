package com.example.merchant;

public class Activity {
    public String activityDescription;
    public String activityTitle;
    public String goalTitle;
    public String points;
    public String qrData;
    public String questId;

    public Activity() {
    }

    public Activity(String activityDescription, String activityTitle, String goalTitle, String points, String qrData, String questId) {
        this.activityDescription = activityDescription;
        this.activityTitle = activityTitle;
        this.goalTitle = goalTitle;
        this.points = points;
        this.qrData = qrData;
        this.questId = questId;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }
}
