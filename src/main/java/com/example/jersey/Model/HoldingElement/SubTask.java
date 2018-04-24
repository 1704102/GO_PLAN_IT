package com.example.jersey.Model.HoldingElement;

public class SubTask {

    private String name;
    private String deadline;

    private int estimatedHours;

    private boolean finished;

    public SubTask(String name, String deadline, int estimatedHours, boolean finished) {
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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
}
