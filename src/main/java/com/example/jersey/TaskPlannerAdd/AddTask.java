package com.example.jersey.TaskPlannerAdd;

import com.example.jersey.Controller.Util;
import com.example.jersey.Database.AppointmentDatabase;

import com.example.jersey.Database.TimeElementDatabase;
import com.example.jersey.Model.DateElements.Day;
import jdk.nashorn.internal.parser.DateParser;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.security.jca.GetInstance;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTask {


    public void AddnewTask(/*current date, deadline date*/) {
        // get all days starting from current day until due date of task,
        // and for all the days get the times of the activities on set day and
        // all the repeating tasks. then give the day a score(bonus points on weekend 1 point)
        // and a higher score in the last quarter of the project time(15 bonus points)

        // check if the amount of free time is enough to plan the task.

        // find the day with the least points
        // from the data base get the free time hours
        // place a time block there on a free time space.
    }

    private int getDaysUntilDeadline(Calendar currentDate, Calendar deadline) {
        return Util.daysBetween(currentDate.getTime(), deadline.getTime());
    }

    public ArrayList<Day> sortDays(int amountDays) {
        ArrayList<Day> days = new ArrayList();
        Calendar calendar = Util.createCalender(Instant.now().toEpochMilli());
        for (int i = 0; i < amountDays; i++) {
            days.add(new Day() {{
                setDate(calendar.getTime());
            }});
            calendar.add(Calendar.DATE, 1);
        }
        return days;
    }

    public void addAppointments(JSONObject input, ArrayList<Day> days){
        TimeElementDatabase timeElementDatabase = new TimeElementDatabase();
        Date date2 = new Date(Instant.now().toEpochMilli() + (1000*60*60*24*364*50*100));
        JSONArray output = timeElementDatabase.getAppointmentsOnDate(new JSONArray(), Util.DateToString(Util.createCalender(Instant.now().toEpochMilli())), Util.DateToString(Util.createCalender(date2.getTime())),input.getString("token"), input.getString("timeOffset"));
        output = timeElementDatabase.getAppointmentsOnRepeat(output, input.getString("token"), input.getString("timeOffset"));
        for(int i = 0; i < output.length(); i++) {
            JSONObject appointment = output.getJSONObject(i);
            String timeB = appointment.getString("timeB");
            String timeE = appointment.getString("timeE");
            Date date = new Date(0);
            int repeating = 0;
            try {
                date = (Date) appointment.get("date");
            } catch (Exception e) {
            }
            try {
                repeating = appointment.getInt("day");
            }catch (Exception e){

            }
            System.out.println("appointment");
            System.out.println("*" + date.toString());
            System.out.println("*" + repeating);
        }

    }



}
