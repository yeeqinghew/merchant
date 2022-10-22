package com.example.merchant;

public class CreateEvent {
    private String challengetitle;
    private String eventdate;
    private String eventdescription;
    private String eventendtime;
    private String eventlocation;
    private String eventpoints;
    private String eventstarttime;
    private String eventtitle;
    private String file;
    private String gmapslocation;
    private String goalspoints;
    private String goaltitle;
    private String maxparticipants;

    public CreateEvent() {

    }

    public CreateEvent(String challengetitle, String eventdate, String eventdescription, String eventendtime, String eventlocation, String eventpoints, String eventstarttime, String eventtitle, String file, String gmapslocation, String goalspoints, String goaltitle, String maxparticipants) {
        this.challengetitle = challengetitle;
        this.eventdate = eventdate;
        this.eventdescription = eventdescription;
        this.eventendtime = eventendtime;
        this.eventlocation = eventlocation;
        this.eventpoints = eventpoints;
        this.eventstarttime = eventstarttime;
        this.eventtitle = eventtitle;
        this.file = file;
        this.gmapslocation = gmapslocation;
        this.goalspoints = goalspoints;
        this.goaltitle = goaltitle;
        this.maxparticipants = maxparticipants;
    }

    public String getChallengetitle() {
        return challengetitle;
    }

    public String getEventdate() {
        return eventdate;
    }

    public String getEventdescription() {
        return eventdescription;
    }

    public String getEventendtime() {
        return eventendtime;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public String getEventpoints() {
        return eventpoints;
    }

    public String getEventstarttime() {
        return eventstarttime;
    }

    public String getEventtitle() {
        return eventtitle;
    }

    public String getFile() {
        return file;
    }

    public String getGmapslocation() {
        return gmapslocation;
    }

    public String getGoalspoints() {
        return goalspoints;
    }

    public String getGoaltitle() {
        return goaltitle;
    }

    public String getMaxparticipants() {
        return maxparticipants;
    }
}
