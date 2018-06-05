package com.example.jersey.Model.DateElements;

import com.example.jersey.Controller.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Day {
    private int timeSpent = 0;
    private int dayscore = 0;
    private ArrayList<Integer> freehours = new ArrayList<Integer>();
    private Date date;

    public Day(){}


    public Day(Date date){

    }

    //code for time block timespent+ standard score+ time into project+ random values.

    public void setTimeSpent(int x) {
        timeSpent = x;
        addscore(x);
        if (timeSpent >= 10) {
            dayscore = 10000;
        }
    }

    public void addTimeblock(int x) {
        timeSpent = timeSpent + x;
        addscore(x);
        if (timeSpent >= 10) {
            dayscore = 10000;
        }
    }

    public void setScore(int x) {
        dayscore = 0;
    }

    private void addscore(int x) {
        dayscore = dayscore + x;
    }

    private boolean isWeekend(Calendar c){
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDayscore() {
        return dayscore;
    }
}

