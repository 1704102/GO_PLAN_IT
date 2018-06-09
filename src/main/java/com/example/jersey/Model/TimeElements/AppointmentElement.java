package com.example.jersey.Model.TimeElements;

import java.sql.Date;
import java.sql.Time;

public class AppointmentElement extends TimeElement{

    private String name;

    private boolean repeating;
    private String repeatingType;

    public AppointmentElement(String name, boolean repeating, String repeatingType, Time timeB, Time timeE) {
        super(timeB,timeE);
        this.name = name;
        this.repeating = repeating;
        this.repeatingType = repeatingType;
    }
}
