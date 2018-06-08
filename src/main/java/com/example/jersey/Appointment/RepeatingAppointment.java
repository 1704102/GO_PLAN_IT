package com.example.jersey.Appointment;

public class RepeatingAppointment extends Appointment {

    int repeating;

    public RepeatingAppointment(String name, String timeB, String timeE, int repeating) {
        super(name, timeB, timeE);
        this.repeating = repeating;
    }

    public int getRepeating() {
        return repeating;
    }

    public void setRepeating(int repeating) {
        this.repeating = repeating;
    }
}
