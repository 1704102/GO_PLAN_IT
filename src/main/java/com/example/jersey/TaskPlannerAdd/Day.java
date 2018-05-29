package com.example.jersey.TaskPlannerAdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Day {
    private int timeSpent = 0;
    private int dayscore = 0;
    private ArrayList<Integer> freehours = new ArrayList<Integer>();
    private Date date;
    private Calendar calendar;

    {
        Calendar.getInstance();
    }

    public Day(){

    }

    public Day(Calendar c, boolean lastDays) {
        calendar = c;
        if (lastDays) {
            addscore(10);
        }
        if (isWeekend(c)) {
            addscore(7);
        }

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
        return (c.get(Calendar.DATE) == Calendar.SATURDAY || c.get(Calendar.DATE) == Calendar.SUNDAY);
    }

}

