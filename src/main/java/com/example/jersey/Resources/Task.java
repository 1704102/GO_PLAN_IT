package com.example.jersey.Resources;

import com.example.jersey.Database.TaskDatabase;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/task")
public class Task {

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTask(String x){
        JSONObject input = new JSONObject(x);
        TaskDatabase taskDatabase = new TaskDatabase();
        return taskDatabase.getTasks(input).toString();
    }
}
