package com.example.jersey.Model.TimeElements;

import com.example.jersey.Model.HoldingElement.Task;

public class TaskElement extends TimeElement{

    private Task task;

    public TaskElement(Task task,String timeB, String timeE, String date){
        this.task = task;
        this.timeB = timeB;
        this.timeE = timeE;
        this.date = date;
    }
}
