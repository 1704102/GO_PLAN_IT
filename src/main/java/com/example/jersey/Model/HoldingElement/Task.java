package com.example.jersey.Model.HoldingElement;

import java.util.ArrayList;
import java.util.Calendar;

public class Task {

    private String name;
    private Calendar deadline;

    private boolean finished;

    private ArrayList<SubTask> subTasks;

    public Task(String name, Calendar deadline, boolean finished, ArrayList<SubTask> subTasks) {
        this.name = name;
        this.deadline = deadline;
        this.finished = finished;
        this.subTasks = subTasks;
    }

    public int getTotalEstimatedHours(){
        int total = 0;
        for(SubTask task : subTasks){
            total += task.getEstimatedHours();
        }
        return total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}
