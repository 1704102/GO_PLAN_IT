package com.example.jersey.Resources;

import com.example.jersey.Database.TimeElementDatabase;
import com.example.jersey.TaskPlannerAdd.AddTask;
import org.json.JSONObject;

import javax.ws.rs.*;

@Path("/agenda")
public class Agenda {

    @Path("/generate")
    @POST
    public String generatePlanning(String x){
        JSONObject object = new JSONObject(x);
        AddTask task = new AddTask();
        task.GeneratePlanning(object);
        return "";
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String getTimeElements(String x) {
        JSONObject object = new JSONObject(x);
        if (!object.getString("token").equals("null")) {
            TimeElementDatabase d = new TimeElementDatabase();
            return d.getTimeElements(object).toString();
        } else return "";
    }
}