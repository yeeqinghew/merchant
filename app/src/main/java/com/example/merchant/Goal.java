package com.example.merchant;

public class Goal {
    public String goalslogo;
    private String goalstitle;
    private String subdescription;
    private String url;

    public Goal() {

    }

    public Goal(String goalslogo, String goalstitle, String subdescription, String url) {
        this.goalslogo = goalslogo;
        this.goalstitle = goalstitle;
        this.subdescription = subdescription;
        this.url = url;
    }

    public void setGoalslogo(String goalslogo) {
        this.goalslogo = goalslogo;
    }

    public void setGoalstitle(String goalstitle) {
        this.goalstitle = goalstitle;
    }

    public void setSubdescription(String subdescription) {
        this.subdescription = subdescription;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGoalslogo() {
        return goalslogo;
    }

    public String getGoalstitle() {
        return goalstitle;
    }

    public String getSubdescription() {
        return subdescription;
    }

    public String getUrl() {
        return url;
    }
}
