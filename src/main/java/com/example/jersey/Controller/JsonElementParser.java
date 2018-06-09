package com.example.jersey.Controller;

import com.example.jersey.Appointment.DateAppointment;
import com.example.jersey.Appointment.RepeatingAppointment;
import org.json.JSONObject;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JsonElementParser {

    public static DateAppointment parseDateAppointment(JSONObject object){
        String name = object.getString("name");
        String timeB = object.getString("timeB");
        String timeE = object.getString("timeE");
        Date date = new Date();
        try {
            date = (Date)object.get("date");
        }catch (Exception e){}
        LocalDate dat2 = LocalDate.parse(Util.DateToString(Util.createCalender(date.getTime())));
        return new DateAppointment(name, Time.valueOf(timeB+ ":00"),Time.valueOf(timeE+ ":00"), dat2);
    }

    public static RepeatingAppointment parseRepeatingAppointment(JSONObject object){
        String name = object.getString("name");
        String timeB = object.getString("timeB");
        String timeE = object.getString("timeE");
        int repeating = object.getInt("day");
        return new RepeatingAppointment(name,Time.valueOf(timeB + ":00"),Time.valueOf(timeE+ ":00"),repeating);
    }


}
