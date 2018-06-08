package com.example.jersey.Controller;

import com.example.jersey.Appointment.DateAppointment;
import com.example.jersey.Appointment.RepeatingAppointment;
import org.json.JSONObject;

import java.util.Date;

public class JsonElementParser {

    public static DateAppointment parseDateAppointment(JSONObject object){
        String name = object.getString("name");
        String timeB = object.getString("timeB");
        String timeE = object.getString("timeE");
        Date date = new Date();
        try {
            date = (Date) object.get("date");
        }catch (Exception e){}
        return new DateAppointment(name,timeB,timeE,date);
    }

    public static RepeatingAppointment parseRepeatingAppointment(JSONObject object){
        String name = object.getString("name");
        String timeB = object.getString("timeB");
        String timeE = object.getString("timeE");
        int repeating = object.getInt("day");
        return new RepeatingAppointment(name,timeB,timeE,repeating);
    }


}
