package com.example.jersey.Model.DateElements;
import com.example.jersey.Appointment.Appointment;
import com.example.jersey.Model.TimeElements.*;
import com.example.jersey.Controller.Util;
import com.example.jersey.Model.HoldingElement.Taskblock;

import java.sql.Time;
import java.util.*;

public class Day {
    private int timeSpent = 0;
    private int dayscore = 0;
    private ArrayList<Integer> freehours = new ArrayList<Integer>();
    private Date date=null;
    public ArrayList<Taskblock> tasksofday  = new ArrayList();
    public ArrayList<Appointment> appointmentsOfToday  = new ArrayList();
    public Day(){}



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
    public void AddTaskBlock(Taskblock t) {
        tasksofday.add(t);
    }


    public Day(Date date1){
        date = date1;
    }

    public Taskblock getTaskBlock(Time begin, Time end, int duration){

        // sort task on begin time
        appointmentsOfToday.sort(Comparator.comparing(o -> o.getTimeB()));

        ArrayList<TimeElement> blocks = getTimeElements(begin,end);

        return null;
    }

    ArrayList<TimeElement> getTimeElements(Time begin, Time end){
        ArrayList<TimeElement> list = new ArrayList<>();
        appointmentsOfToday.sort(Comparator.comparing(o -> o.getTimeB()));

        // get time between begin and first task
        list.add(new TimeElement(begin,appointmentsOfToday.get(0).getTimeB()));

        // get time between appointments
        for(int i = 0; i < appointmentsOfToday.size() -1; i++){
            list.add(new TimeElement(appointmentsOfToday.get(i).getTimeE(), appointmentsOfToday.get(i+1).getTimeB()));
        }

        // get time between last task and end
        list.add(new TimeElement(appointmentsOfToday.get(appointmentsOfToday.size()-1).getTimeE(), end));

        return list;
    }

    //TODO check function getTimeElements
    // has same functionality


//    public Taskblock getlargestFreehours(String name) {
//        TreeMap tm = new TreeMap();
//        for(Taskblock b: tasksofday) {
//            tm.put(b.getStartTime(),b.getEndTime() );
//        }
//        Set set = tm.entrySet();
//        Iterator i = set.iterator();
//        int starttime = 900;
//        int endtime=0;
//        int Duration = 0;
//        int longestDuration=0;
//        int longestStarttime=0;
//        while(i.hasNext()) {
//            Map.Entry me = (Map.Entry)i.next();
//            endtime = (int) me.getKey();
//            if(endtime>starttime && starttime>2100 && endtime>2100) {
//                Duration = endtime-starttime;
//                System.out.println(Duration+ "");
//                if (Duration>longestDuration) {
//                    longestDuration = Duration;
//                    longestStarttime=endtime;}
//            }
//            starttime = (int)me.getValue();
//
//
//        }
//        if (starttime ==900 && endtime==0) {
//            longestDuration =1200;
//            longestStarttime=900;
//        }
//        if (longestDuration>200) {Taskblock t = new Taskblock(getDate(),longestStarttime,(longestStarttime+2),name);
//            return t;}
//        else{
//            Taskblock t = new Taskblock(getDate(),longestStarttime,longestDuration,name);
//            return t;
//
//        }
//
//
//    }


    public void setScore(int x) {
        dayscore = 0;
    }

    public void addscore(int x) {
        dayscore = dayscore + x;
    }

    private boolean isWeekend(Calendar c){
        return (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    public ArrayList<Appointment> getAppointmentsOfToday() {
        return appointmentsOfToday;
    }

    public void setAppointmentsOfToday(ArrayList<Appointment> appointmentsOfToday) {
        this.appointmentsOfToday = appointmentsOfToday;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addAppointment(Appointment appointment){
        appointmentsOfToday.add(appointment);
    }

    public int getDayscore() {
        return dayscore;
    }
    public String toString() {
        return date.toString()+"  "+ getDayscore() +"   bugtestday";
    }
}

