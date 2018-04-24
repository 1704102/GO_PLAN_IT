package com.example.jersey.Model.HoldingElement;

import java.util.ArrayList;

public class Task {

    private String name;
    private String deadline;

    private boolean finished;

    private ArrayList<SubTask> subTasks;

    public Task(String name, String deadline, boolean finished, ArrayList<SubTask> subTasks) {
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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
