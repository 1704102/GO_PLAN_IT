package com.example.jersey.Model;

import java.time.LocalDate;

public class Appointment {
    private String name;
    private String timeB;
    private String timeE;
    private LocalDate date;

    public Appointment(String name, String timeB, String timeE, LocalDate date) {
        this.name = name;
        this.timeB = timeB;
        this.timeE = timeE;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "name='" + name + '\'' +
                ", timeB='" + timeB + '\'' +
                ", timeE='" + timeE + '\'' +
                ", date=" + date +
                '}';
    }
}
