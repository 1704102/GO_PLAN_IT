package com.example.jersey.Appointment;

import java.sql.Time;
import java.time.LocalDate;

public abstract class Appointment {
    private String name;
    private Time timeB;
    private Time timeE;

    public Appointment(String name, Time timeB, Time timeE) {
        this.name = name;
        this.timeB = timeB;
        this.timeE = timeE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTimeB() {
        return timeB;
    }

    public void setTimeB(Time timeB) {
        this.timeB = timeB;
    }

    public Time getTimeE() {
        return timeE;
    }

    public void setTimeE(Time timeE) {
        this.timeE = timeE;
    }

}
