package com.example.jersey.Model.TimeElements;

import java.sql.Time;

public class TimeElement {
    protected Time timeB;
    protected Time timeE;

    public TimeElement(Time timeB, Time timeE) {
        this.timeB = timeB;
        this.timeE = timeE;
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

    //get duration of timeElement in minutes
    public long getDuration(){
        return (timeE.getTime() - timeB.getTime())/1000/60/60;
    }
}
