package com.example.jersey.TaskPlannerAdd;

import com.example.jersey.Controller.Util;
import com.example.jersey.Database.AppointmentDatabase;
import com.example.jersey.Model.DateElements.Day;
import jdk.nashorn.internal.parser.DateParser;
import org.json.JSONArray;
import org.json.JSONObject;

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

    private List<Day> sortDays(int amountDays) {
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

    private void addAppointments(JSONObject input, ArrayList<Day> days){
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        JSONArray ouput = appointmentDatabase.getAppointments(input);
        for(int i = 0; i < ouput.length(); i++){
            JSONObject appointment = ouput.getJSONObject(i);
            String timeB = appointment.getString("timeB");
            String timeE = appointment.getString("timeE");
            String date = appointment.getString("date");

            Date date2 = new Date(Date.parse(date));

        }



    }
}
