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
    public String getTasks(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        return taskDatabase.getTasks(input).toString();
    }

    @PUT
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String addTask(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        return String.valueOf(taskDatabase.addTasks(input));
    }

    @Path("/data")
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveTask(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        taskDatabase.saveFullTask(input);
    }

    @Path("/one")
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTask(String x){
        System.out.println(x);
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        return taskDatabase.getTask(input).toString();
    }
}
