package com.example.jersey.Appointment;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class DateAppointment extends Appointment{

    LocalDate date;


    public DateAppointment(String name, Time timeB, Time timeE, LocalDate date) {
        super(name, timeB, timeE);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
