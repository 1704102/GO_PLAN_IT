package com.example.jersey.Resources;

import com.example.jersey.Database.TaskDatabase;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/task")
public class Task {
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String addTask(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        return String.valueOf(taskDatabase.addTasks(input));
    }

    @Path("/data")
    @PUT
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveTask(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        taskDatabase.saveFullTask(input);
    }

    @PUT
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTask(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        return taskDatabase.getTask(input).toString();
    }

    @DELETE
    @Consumes("application/json")
    public void deleteTask(String x){
        System.out.println(x);
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        taskDatabase.deleteTask(input);
    }

    @Path("/check")
    @DELETE
    @Consumes("application/json")
    public void deleteTaskWithCheck(String x){
        System.out.println(x);
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        if (taskDatabase.getSubtasks(input).size() == 0){
            System.out.println(taskDatabase.getSubtasks(input).size());
            taskDatabase.deleteTask(input);
        }

    }
}
