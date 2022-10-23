package com.example.merchant;

public class Quest {
    public String description;
    public String goalTitle;
    public String points;
    public String questId;
    public String questTitle;
    public String when;
    public String who;

    public Quest() {
    }

    public Quest(String description, String goalTitle, String points, String questId, String questTitle, String when, String who) {
        this.description = description;
        this.goalTitle = goalTitle;
        this.points = points;
        this.questId = questId;
        this.questTitle = questTitle;
        this.when = when;
        this.who = who;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}

