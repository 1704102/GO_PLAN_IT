package com.example.jersey.Resources;

import com.example.jersey.Database.TaskDatabase;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tasks")
public class Tasks {

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTasks(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        return taskDatabase.getTasks(input).toString();
    }

}
