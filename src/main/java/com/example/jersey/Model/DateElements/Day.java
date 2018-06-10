package com.example.jersey.Model.DateElements;
import com.example.jersey.Appointment.Appointment;
import com.example.jersey.Model.TimeElements.*;
import com.example.jersey.Controller.Util;
import com.example.jersey.Model.HoldingElement.Taskblock;

import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

public class Day {
    private int dayscore = 0;
    private LocalDate date;
    public ArrayList<Taskblock> tasksofday  = new ArrayList();
    public ArrayList<Appointment> appointmentsOfToday  = new ArrayList();
    public Day(){}

    public void AddTaskBlock(Taskblock t) {
        tasksofday.add(t);
    }


    public Day(LocalDate date1){
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

    public ArrayList<Appointment> getAppointmentsOfToday() {
        return appointmentsOfToday;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addAppointment(Appointment appointment){
        appointmentsOfToday.add(appointment);
    }

    public int getDayscore() {
        return dayscore;
    }

    public void calculatePoints(){
        appointmentsOfToday.sort(Comparator.comparing(o -> o.getTimeB()));
        appointmentsOfToday.forEach(appointment -> {
            dayscore += appointment.getDuration();
        });
    }

    public String toString() {
        return date.toString()+"  "+ getDayscore() +"   bugtestday";
    }

    public void addscore(int i) {
        dayscore += i;
    }
}

