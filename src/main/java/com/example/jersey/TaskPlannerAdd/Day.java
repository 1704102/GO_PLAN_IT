package com.example.jersey.TaskPlannerAdd;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class Day {
    private int timeSpent=0;
    private int Dayscore=0;
    private ArrayList<Integer> Freehours= new ArrayList<Integer>();
    private Date date;
    Calendar c = Calendar.getInstance();
    public Day(Date d, boolean lastDays){
        date = d;
        if(lastDays==true) {
            addscore(10);
        }
        if(d.getDay()==6 || d.getDay()==7){
            addscore(7);
        }

    }


    //code for time block timespent+ standard score+ time into project+ random values.

    public void setTimeSpent(int x){
    timeSpent = x;
    addscore(x);
    if (timeSpent>=10){
            Dayscore = 10000;
    }
    }

    public void addTimeblock(int x){
        timeSpent = timeSpent + x;
        addscore(x);
        if (timeSpent>=10){
            Dayscore = 10000;
        }
    }
    public void setScore(int x){
        Dayscore = 0;
    }

    private void addscore(int x)
    {Dayscore = Dayscore + x;}

}
