package com.example.jersey.Model.HoldingElement;

import java.sql.Time;
import java.time.LocalDate;

public class SubTask {

    private int id;
    private String name;
    private LocalDate deadline;

    private int estimatedHours;

    private boolean finished;

    public SubTask(int id,String name, LocalDate deadline, int estimatedHours, boolean finished) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.estimatedHours = estimatedHours;
        this.finished = finished;
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

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getId() {
        return id;
    }
}
