package com.example.merchant;

public class Project {
    public String closingDate;
    public Long current;
    public String desc;
    public String file;
    public String goal;
    public String goaltitle;
    public String title;
    public String url;

    public Project() {
    }

    public Project(String closingDate, Long current, String desc, String file, String goal, String goaltitle, String title, String url) {
        this.closingDate = closingDate;
        this.current = current;
        this.desc = desc;
        this.file = file;
        this.goal = goal;
        this.goaltitle = goaltitle;
        this.title = title;
        this.url = url;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public Long getCurrent() {
        return current;
    }

    public String getDesc() {
        return desc;
    }

    public String getFile() {
        return file;
    }

    public String getGoal() {
        return goal;
    }

    public String getGoaltitle() {
        return goaltitle;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
