package com.example.jersey.TaskPlannerAdd;

import com.example.jersey.Controller.Util;
import com.example.jersey.Database.TimeElementDatabase;
import com.example.jersey.Model.DateElements.Day;
import com.example.jersey.Model.HoldingElement.Task;
import com.example.jersey.Model.HoldingElement.Taskblock;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.*;

public class AddTask {
    private ArrayList<Taskblock> tasks = new ArrayList();
    //ArrayList actitvities()
    private Calendar today = Util.createCalender(Instant.now().toEpochMilli());

    public void addnewTask(Date startDate, Date end, int plannedhours, String taskname) {
        ArrayList<Day> days= listOfDays(startDate,end);
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
        placeTask(days, plannedhours, taskname);
    }

    public ArrayList<Day> getDaysWithScore(ArrayList<Day> e, int dayscore) {
        ArrayList<Day> days = new ArrayList<>();
        for (Day day : e) {
            if (day.getDayscore() == dayscore) {
                days.add(day);
            }

        }

        return days;
    }
    public ArrayList<Day> listOfDays(Date start, Date end){
        ArrayList<Day> makedays= new ArrayList();
        Date daymaker=start;
        while(!daymaker.equals(end)){
            Day day = new Day(daymaker);
            makedays.add(day);
            Calendar c = Calendar.getInstance();
            c.setTime(daymaker);
            c.add(Calendar.DATE, 1);
            daymaker = c.getTime();
        }
        return makedays;
    }
    public void placeTask(ArrayList<Day> Alldays, int plannedHours, String taskname) {

        int x = 100000;
        for (Day d : Alldays) {
            if (d.getDayscore() > x) ;
            x = d.getDayscore();
        }
        ArrayList<Day> optimaldays = getDaysWithScore(Alldays, x);
        //find optimal size
        //find optimal hours
        Day d = RandomDay(optimaldays);
        makeTaskBlock(d, 0, taskname);
        //plannedHours= plannedHours-2;
        if (plannedHours > 0) {
            placeTask(Alldays, plannedHours, taskname);

        } else {
            //task database
            return;

        }
    }

    public void makeTaskBlock(Day d, int StartTime, String taskname) {
        Date date = d.getDate();
        Date datetime1 = date;
        Date datetime2 = date;
        datetime1.setHours(StartTime);
        datetime2.setHours(StartTime + 2);
        Taskblock t = new Taskblock(datetime1, datetime2, taskname);
        tasks.add(t);


    }

    public Day RandomDay(ArrayList<Day> e) {
        Random rand = new Random();
        int random = rand.nextInt(e.size());
        return e.get(random);
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
        JSONArray output = timeElementDatabase.getAppointmentsOnDate(empty, Util.DateToString(today), futureDate, userToken, timezoneOffset);
        output = timeElementDatabase.getAppointmentsOnRepeat(output, userToken, timezoneOffset);

        for (int i = 0; i < output.length(); i++) {
            JSONObject appointment = output.getJSONObject(i);
            String timeB = appointment.getString("timeB");
            String timeE = appointment.getString("timeE");
            Date date = (Date) appointment.get("date");
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

            System.out.println("appointment");
            System.out.println("*" + date.toString());
            System.out.println("*" + repeating);
        }
    }

    public boolean isPlannable(Task task) {
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



