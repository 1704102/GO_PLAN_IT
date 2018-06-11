package com.example.jersey.Model.HoldingElement;

import java.sql.Time;
import java.util.Date;


import java.util.Date;

public class Taskblock {
    private Time startTime;
    private Time endTime;

    private Task task;
    private SubTask subtask;


    public Taskblock(Task task) {

        this.task=task;
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


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public SubTask getSubtask() {
        return subtask;
    }

    public void setSubtask(SubTask subtask) {
        this.subtask = subtask;
    }

    public String toString() {

        return "\n";
    }
}