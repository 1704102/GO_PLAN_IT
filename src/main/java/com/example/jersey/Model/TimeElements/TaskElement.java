package com.example.jersey.Model.TimeElements;

import com.example.jersey.Model.HoldingElement.Task;

import java.sql.Time;
import java.util.Date;

public class TaskElement extends TimeElement{

    private Task task;
    private Date date;

    public TaskElement(Task task, Time timeB, Time timeE, Date date){
        super(timeB,timeE);
        this.task = task;
        this.date = date;
    }
}
