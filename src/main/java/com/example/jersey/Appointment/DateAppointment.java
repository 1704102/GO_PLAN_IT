package com.example.jersey.Appointment;

import java.util.Date;

public class DateAppointment extends Appointment{

    Date date;


    public DateAppointment(String name, String timeB, String timeE, Date date) {
        super(name, timeB, timeE);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
