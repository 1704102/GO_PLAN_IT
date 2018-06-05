package com.example.jersey.TaskPlannerAdd;

import com.example.jersey.Controller.Util;
import com.example.jersey.Database.TimeElementDatabase;
import com.example.jersey.Model.DateElements.Day;
import com.example.jersey.Model.HoldingElement.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTask {

    private String today = Util.DateToString(Util.createCalender(Instant.now().toEpochMilli()));


    public void AddnewTask(/*current date, deadline date*/) {
        /**
         // DONE (getDaysUntilDeadline): get all days starting from current day until due date of task,
         // DONE (addAppointments): and for all the days get the times of the activities on set day and
         // all the repeating tasks. then give the day a score(bonus points on weekend 1 point)
         // and a higher score in the last quarter of the project time(15 bonus points)

         // check if the amount of free time is enough to plan the task.

         // find the day with the least points
         // from the data base get the free time hours
         // place a time block there on a free time space.
         */
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

    public void addAppointments(JSONObject input, ArrayList<Day> days) {
        TimeElementDatabase timeElementDatabase = new TimeElementDatabase();
        JSONArray empty = new JSONArray();
        String futureDate = Util.DateToString(Util.createCalender(new Date(Instant.now().toEpochMilli() + (long) (1000 * 60 * 60 * 24 * 364 * 50 * 100)).getTime()));
        String userToken = input.getString("token");
        String timezoneOffset = input.getString("timeOffset");
        JSONArray output = timeElementDatabase.getAppointmentsOnDate(empty, today, futureDate, userToken, timezoneOffset);
        output = timeElementDatabase.getAppointmentsOnRepeat(output, userToken, timezoneOffset);

        for (int i = 0; i < output.length(); i++) {
            JSONObject appointment = output.getJSONObject(i);
            String timeB = appointment.getString("timeB");
            String timeE = appointment.getString("timeE");
            Date date = new Date(0);
            int repeating = 0;
            try {
                date = (Date) appointment.get("date");
            } catch (Exception e) {
                e.getMessage();
            }
            try {
                repeating = appointment.getInt("day");
            } catch (Exception e) {
                e.getMessage();
            }

        }
    }

    public boolean isPlannable(Task task) {
        Calendar today = Util.createCalender(Instant.now().toEpochMilli());
        Calendar deadline = task.getDeadline();

        int talliedDays = getDaysUntilDeadline(today, deadline);
        List<Day> sortedDays = sortDays(talliedDays);

        for (Day d : sortedDays) {
            /** do stuff*/
            return true;
        }
        return false;
    }
}
