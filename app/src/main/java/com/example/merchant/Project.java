package com.example.merchant;

public class Project {
    public String current;
    public String desc;
    public String goal;
    public String goaltitle;
    public String image;
    public String projectID;
    public String projecturl;
    public String title;
    public String url;

    public Project() {
    }

    public Project(String current, String desc, String goal, String goaltitle, String image, String projectID, String projecturl, String title, String url) {
        this.current = current;
        this.desc = desc;
        this.goal = goal;
        this.goaltitle = goaltitle;
        this.image = image;
        this.projectID = projectID;
        this.projecturl = projecturl;
        this.title = title;
        this.url = url;
    }

    public String getCurrent() {
        return current;
    }

    public String getDesc() {
        return desc;
    }

    public String getGoal() {
        return goal;
    }

    public String getGoaltitle() {
        return goaltitle;
    }

    public String getImage() {
        return image;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getProjecturl() {
        return projecturl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
