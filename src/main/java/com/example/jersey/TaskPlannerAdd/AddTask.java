package com.example.jersey.TaskPlannerAdd;

import com.example.jersey.Appointment.Appointment;
import com.example.jersey.Appointment.DateAppointment;
import com.example.jersey.Appointment.RepeatingAppointment;
import com.example.jersey.Controller.JsonElementParser;
import com.example.jersey.Controller.Util;
import com.example.jersey.Database.TimeElementDatabase;
import com.example.jersey.Model.DateElements.Day;
import com.example.jersey.Model.HoldingElement.Task;
import com.example.jersey.Model.HoldingElement.Taskblock;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AddTask {
    private ArrayList<Taskblock> generatedtasks = new ArrayList();
    //ArrayList actitvities()
    LocalDate today = LocalDate.now();

    public ArrayList<Taskblock> getGeneratedtasks() {
        return generatedtasks;
    }

    public void AddnewTask(Date startDate, Date end, int plannedhours, String taskname) {
//        ArrayList<Day> days = listOfDays(startDate, end);
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
//        placeTask(days, plannedhours, taskname);
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

    public void placeTask(ArrayList<Day> Alldays, int plannedHours, String taskname) {

        int x = 100000;
        for (Day d : Alldays) {
            if (d.getDayscore() < x) {
                x = d.getDayscore();
            }

        }

        ArrayList<Day> optimaldays = getDaysWithScore(Alldays, x);
        if (plannedHours > 0) {

            placeTask(Alldays, plannedHours, taskname);

        } else {
            //task database
            return;

        }

    }


    public Day RandomDay (HashMap<LocalDate, Day> e) {
        Random rand = new Random();
        Set keyset = e.keySet();
        return e.get(keyset.toArray()[rand.nextInt(keyset.size())]);
    }


    public HashMap<LocalDate, Day> fillDays(LocalDate end, HashMap<Integer, ArrayList<Appointment>> appointments, String token, String timeOffset){

        HashMap<LocalDate, Day> days = new HashMap<>();
        TimeElementDatabase database = new TimeElementDatabase();

        for (LocalDate date = today; date.isBefore(end); date = date.plusDays(1)){
            Day day = new Day(date);
            if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7){
                day.addscore(30);
            }

            appointments.get(date.getDayOfWeek().getValue() - 1).forEach(app->{
                day.appointmentsOfToday.add(app);
                day.addscore(10);
            });

            days.put(date, day);
        }
        String timeB = Util.DateToString(Util.createCalender(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
        String timeE = Util.DateToString(Util.createCalender(Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
        database.getAppointmentsOnDate(new JSONArray(),timeB, timeE, token, timeOffset).forEach(appointment->{
            DateAppointment appointment1 = JsonElementParser.parseDateAppointment((JSONObject) appointment);
            days.get(appointment1.getDate()).addAppointment(appointment1);
            days.get(appointment1.getDate()).addscore(10);
        });

        return days;
    }

    public HashMap<Integer, ArrayList<Appointment>> getRepAppointments(String token, String timeOffset){
        TimeElementDatabase database = new TimeElementDatabase();
        HashMap<Integer, ArrayList<Appointment>> appointments = new HashMap();

        for(int i =0; i < 7; i++){
            appointments.computeIfAbsent(i, k -> new ArrayList<>());
        }

        database.getAppointmentsOnRepeat(new JSONArray(),token,timeOffset).forEach(appointment->{
            RepeatingAppointment appointment1 = (JsonElementParser.parseRepeatingAppointment((JSONObject) appointment));
            appointments.get(appointment1.getRepeating()).add(appointment1);
        });

        return appointments;
    }
    public void addAppointments (JSONObject input){
        LocalDate futureDate = LocalDate.of(2018,8,20);
        String token = input.getString("token");
        String offset = input.getString("timeOffset");

        HashMap<LocalDate, Day> days = fillDays(futureDate,getRepAppointments(token,offset),token,offset);


    }

    public void addTask(Day day, Task task){
        //day.AddTaskBlock(new Taskblock());
    }

    public HashMap<LocalDate, Day> getDaysWithLowestScore(HashMap<LocalDate, Day> days){
        HashMap<LocalDate, Day> output = new HashMap<>();

        Object[] keySet = days.keySet().toArray();
        int lowest = days.get(keySet[0]).getDayscore();
        for (int i = 1; i < keySet.length; i++){
            if (days.get(keySet[i]).getDayscore() < lowest){
                lowest = days.get(keySet[i]).getDayscore();
            }
        }

        int finalLowest = lowest;
        days.forEach((key, value) -> {
            if (value.getDayscore() == finalLowest){
                output.put(key, value);
            }
        });
        return output;
    }


}

