package com.example.jersey.TaskPlannerAdd;
import java.util.ArrayList;

public class Day {
    private int timeSpent=0;
    private int Dayscore=0;
    private ArrayList<Integer> Freehours= new ArrayList<Integer>();
    private String date;
    public Day(){

    }


    //timespent+ standard score+ time into project+ random values.

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
