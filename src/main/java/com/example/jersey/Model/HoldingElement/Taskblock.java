package com.example.jersey.Model.HoldingElement;

import java.sql.Time;
import java.util.Date;


import java.util.Date;

public class Taskblock {
    private Time startTime;
    private Time endTime;
    private Date date;
    private long duration;
    private String taskname;
    private String subtask;


    public Taskblock(Date start, Time startime, Time endtime, String taskname) {

        date= start;
        startTime= startime;
        endTime=endtime;
        this.taskname=taskname;
        duration = (endTime.getTime()-startTime.getTime())/1000/60;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getSubtask() {
        return subtask;
    }

    public void setSubtask(String subtask) {
        this.subtask = subtask;
    }
    public String toString() {

        return "\n"+taskname+date.toString();
    }
}