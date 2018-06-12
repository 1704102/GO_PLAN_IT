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
    public ArrayList<TimeElement> blocks;


    public void AddTaskBlock(Taskblock t, Time begin, Time end) {

        t.setStartTime(blocks.get(0).getTimeB());
        t.setEndTime(blocks.get(0).getTimeE());
        blocks.remove(0);
        tasksofday.add(t);
    }


    public Day(LocalDate date1){
        date = date1;
    }

    public void addTask(Taskblock element, Time start, Time end){
        element.setStartTime(start);
        element.setEndTime(end);
        tasksofday.add(element);
    }

    public TimeElement getTimeElement(){

        // sort task on begin time
        appointmentsOfToday.sort(Comparator.comparing(o -> o.getTimeB()));

        TimeElement el = blocks.get(0);
        blocks.remove(0);

        return el;
    }

    public void getTimeElements(Time begin, Time end){
        blocks = new ArrayList<>();
        appointmentsOfToday.sort(Comparator.comparing(o -> o.getTimeB()));
        if (appointmentsOfToday.size() > 0) {

            // get time between begin and first task
            blocks.add(new TimeElement(begin, appointmentsOfToday.get(0).getTimeB()));

            // get time between appointments
            for (int i = 0; i < appointmentsOfToday.size() - 1; i++) {
                blocks.add(new TimeElement(appointmentsOfToday.get(i).getTimeE(), appointmentsOfToday.get(i + 1).getTimeB()));
            }

            // get time between last task and end
            blocks.add(new TimeElement(appointmentsOfToday.get(appointmentsOfToday.size() - 1).getTimeE(), end));
        }else{
            blocks.add(new TimeElement(begin,end));
        }
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

    public ArrayList<Taskblock> getTasksofday() {
        return tasksofday;
    }

    public String toString() {
        return date.toString()+"  "+ getDayscore() +"   bugtestday";
    }

    public void addscore(int i) {
        dayscore += i;
    }
}

