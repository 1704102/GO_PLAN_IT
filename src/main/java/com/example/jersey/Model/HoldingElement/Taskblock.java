package com.example.jersey.Model.HoldingElement;

import java.util.Date;


import java.util.Date;

public class Taskblock {
    private int startTime;
    private int endTime;
    private Date date;
    private int duration;
    private String taskname;
    private String subtask;


    public Taskblock(Date start, int startime, int endtime, String taskname) {

        date= start;
        startTime= startime;
        endTime=endtime;
        this.taskname=taskname;
        duration = (endTime-startTime)/100;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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