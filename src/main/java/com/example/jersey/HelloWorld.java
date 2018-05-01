package com.example.jersey;

import com.example.jersey.Controller.Util;
import com.example.jersey.Database.DatabaseHelper;
import com.example.jersey.Database.TimeElementDatabase;

import javax.ws.rs.*;
import java.util.Calendar;

@Path("/agenda")
public class HelloWorld {

    @POST
    @Consumes("application/json")
    @Path("/create")
    public void create(String x) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(x));
        System.out.println(c.getTime().toString());
        System.out.println(Util.getFirstDayOfTheWeek(c).getTime().toString());
    }

    @GET
    @Produces("application/json")
    public String getTimeElements(){
        TimeElementDatabase d = new TimeElementDatabase();
        return d.getTimeElements().toString();
    }
}