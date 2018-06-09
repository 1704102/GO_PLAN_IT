package com.example.jersey.Resources;

import com.example.jersey.Database.TimeElementDatabase;
import com.example.jersey.TaskPlannerAdd.AddTask;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/agenda")
public class Agenda {

//    @POST
//    @Consumes("application/json")
//    @Path("/create")
//    public void create(String x) {
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(Long.parseLong(x));
//        System.out.println(c.getTime().toString());
//        System.out.println(Util.getFirstDayOfTheWeek(c).getTime().toString());
//    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String getTimeElements(String x) {
        JSONObject object = new JSONObject(x);

        AddTask task = new AddTask();
        task.addAppointments(object);


        if (!object.getString("token").equals("null")) {
            TimeElementDatabase d = new TimeElementDatabase();
            return d.getTimeElements(object).toString();
        } else return "";
    }
}