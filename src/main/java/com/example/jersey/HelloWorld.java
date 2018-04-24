package com.example.jersey;

import com.example.jersey.Controller.Controller;
import com.example.jersey.Controller.Util;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Path("/hello")
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
}