package com.example.jersey.Appointment;

import java.time.LocalDate;

public abstract class Appointment {
    private String name;
    private String timeB;
    private String timeE;

    public Appointment(String name, String timeB, String timeE) {
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

    public String getTimeB() {
        return timeB;
    }

    public void setTimeB(String timeB) {
        this.timeB = timeB;
    }

    public String getTimeE() {
        return timeE;
    }

    public void setTimeE(String timeE) {
        this.timeE = timeE;
    }

}
