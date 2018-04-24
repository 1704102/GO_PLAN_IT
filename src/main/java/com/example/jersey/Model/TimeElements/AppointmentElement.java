package com.example.jersey.Model.TimeElements;

public class AppointmentElement extends TimeElement{

    private String name;

    private boolean repeating;
    private String repeatingType;

    public AppointmentElement(String name, boolean repeating, String repeatingType, String timeB, String timeE, String date) {
        this.name = name;
        this.repeating = repeating;
        this.repeatingType = repeatingType;
        this.timeB =timeB;
        this.timeE =timeE;
        this.date = date;
    }
}
