package com.example.jersey.Resources;

import com.example.jersey.Controller.Util;
import com.example.jersey.Database.DatabaseHelper;
import com.example.jersey.Database.TimeElementDatabase;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import javax.ws.rs.*;
import java.util.Calendar;

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
    public String getTimeElements(String x){
        JSONObject object = new JSONObject(x);
        if (!object.getString("token").equals("null")) {
            TimeElementDatabase d = new TimeElementDatabase();
            return d.getTimeElements(object).toString();
        }else return "";
    }
}