package com.example.jersey.Model.HoldingElement;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Task {

    private String name;
    private LocalDate deadline;
    private Time time;

    private ArrayList<SubTask> subTasks;

    public Task(String name, LocalDate deadline,Time time, ArrayList<SubTask> subTasks) {
        this.name = name;
        this.deadline = deadline;
        this.subTasks = subTasks;
        this.time = time;
    }

    public int getTotalEstimatedHours(){
        int total = 0;
        for(SubTask task : subTasks){
            total += task.getEstimatedHours();
        }
        return total;
    }

    public ArrayList<Taskblock> getTaskBocks(){
        ArrayList<Taskblock> taskBlocks = new ArrayList<>();
        int temp = (int) Math.ceil((double)getTotalEstimatedHours() / 2);;
        for(int i = 0; i < temp; i++){
            taskBlocks.add(new Taskblock(this));
        }
        return taskBlocks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}
