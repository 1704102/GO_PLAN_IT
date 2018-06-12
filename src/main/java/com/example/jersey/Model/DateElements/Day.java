package com.example.jersey.Model.DateElements;
import com.example.jersey.Appointment.Appointment;
import com.example.jersey.Model.TimeElements.*;
import com.example.jersey.Model.HoldingElement.Taskblock;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class Day {
    private int dayscore = 0;
    private LocalDate date;
    public ArrayList<Taskblock> tasksofday  = new ArrayList();
    public ArrayList<Appointment> appointmentsOfToday  = new ArrayList();
    public Day(){}
    public ArrayList<TimeElement> blocks;

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
        //appointmentsOfToday.sort(Comparator.comparing(o -> o.getTimeB()));
        int i = blocks.size()/2;
        TimeElement el = blocks.get(i);
        blocks.remove(i);

        return el;
    }

    public void getFreeTimeBlocks(Time begin, Time end){
        blocks = new ArrayList<>();
        appointmentsOfToday.sort(Comparator.comparing(o -> o.getTimeB()));

        if (appointmentsOfToday.size() > 0) {

            // get time between begin and first task
            generateTimeElements(begin, appointmentsOfToday.get(0).getTimeB(), 2);

//            // get time between appointments
            for (int i = 0; i < appointmentsOfToday.size() - 1; i++) {
                Time time1 = Time.valueOf(appointmentsOfToday.get(i).getTimeE().toLocalTime());
                time1.setTime(time1.getTime() + 1800000);
                Time time2 = Time.valueOf(appointmentsOfToday.get(i + 1).getTimeB().toLocalTime());
                generateTimeElements(time1, time2,2);
            }
//
//            // get time between last task and end
            Time time1 = Time.valueOf(appointmentsOfToday.get(appointmentsOfToday.size() - 1).getTimeE().toLocalTime());
            time1.setTime(time1.getTime() + 1800000);
            generateTimeElements(time1, end,2);
        }else{
            generateTimeElements(begin,end,2);
        }
        System.out.println("ss");

    }

    public void generateTimeElements(Time begin, Time end, int duration){
        Time time = Time.valueOf(begin.toLocalTime());
        Time time2 = Time.valueOf(begin.toLocalTime());
        time2.setTime(time2.getTime() + 7200000);
        while (time2.compareTo(end) < 0){
            blocks.add(new TimeElement(Time.valueOf(time.toLocalTime()), Time.valueOf(time2.toLocalTime())));
            time.setTime(time.getTime() + 7200000);
            time2.setTime(time2.getTime() + 7200000);
        }
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

