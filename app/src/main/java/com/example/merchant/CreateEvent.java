package com.example.merchant;

public class CreateEvent {
    public String certificates;
    public String challengetitle;
    public String count;
    public String eventdate;
    public String eventdescription;
    public String eventendtime;
    public String eventlocation;
    public String eventpoints;
    public String eventstarttime;
    public String eventtimeend;
    public String eventtimestart;
    public String eventtitle;
    public String gmapslocation;
    public String goalpoints;
    public String goaltitle;
    public String image;
    public String maxparticipants;
    public String qrdata;

    public CreateEvent() {

    }

    public CreateEvent(String certificates, String challengetitle, String count,
                       String eventdate, String eventdescription, String eventendtime,
                       String eventlocation, String eventpoints, String eventstarttime,
                       String eventtimeend, String eventtimestart, String eventtitle,
                       String gmapslocation, String goalpoints, String goaltitle,
                       String image, String maxparticipants, String qrdata) {
        this.certificates = certificates;
        this.challengetitle = challengetitle;
        this.count = count;
        this.eventdate = eventdate;
        this.eventdescription = eventdescription;
        this.eventendtime = eventendtime;
        this.eventlocation = eventlocation;
        this.eventpoints = eventpoints;
        this.eventstarttime = eventstarttime;
        this.eventtimeend = eventtimeend;
        this.eventtimestart = eventtimestart;
        this.eventtitle = eventtitle;
        this.gmapslocation = gmapslocation;
        this.goalpoints = goalpoints;
        this.goaltitle = goaltitle;
        this.image = image;
        this.maxparticipants = maxparticipants;
        this.qrdata = qrdata;
    }
}
