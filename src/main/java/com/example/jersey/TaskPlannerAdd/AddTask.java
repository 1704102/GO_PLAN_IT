package com.example.jersey.TaskPlannerAdd;

import com.example.jersey.Appointment.Appointment;
import com.example.jersey.Appointment.DateAppointment;
import com.example.jersey.Appointment.RepeatingAppointment;
import com.example.jersey.Controller.Controller;
import com.example.jersey.Controller.JsonElementParser;
import com.example.jersey.Controller.Util;
import com.example.jersey.Database.TaskDatabase;
import com.example.jersey.Database.TimeElementDatabase;
import com.example.jersey.Model.DateElements.Day;
import com.example.jersey.Model.HoldingElement.Task;
import com.example.jersey.Model.HoldingElement.Taskblock;
import com.example.jersey.Model.TimeElements.TimeElement;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AddTask {

    LocalDate today = LocalDate.now();
    HashMap<LocalDate, Day> days;

    public void GeneratePlanning(JSONObject input){
        LocalDate futureDate = LocalDate.of(2018,8,20);
        String token = input.getString("token");
        String offset = input.getString("timeOffset");

        days = fillDays(futureDate,getRepAppointments(token,offset),token,offset);

        planTasks(input);
    }

    public void planTasks(JSONObject input){
        ArrayList<Task> tasks = JsonElementParser.parseTaskArray(input, Integer.parseInt(input.getString("timeOffset")));
        System.out.println(tasks);

        //TODO plan task into days
        tasks.sort(Comparator.comparing(task -> task.getDeadline()));
        tasks.forEach(task -> {
            ArrayList<Taskblock> blocks = task.getTaskBocks();
            HashMap<LocalDate , Day> prepDays = getDaysWithLowestScore(task.getDeadline());
            while (blocks.size() != 0){
                for(int i = 0; i < blocks.size(); i++){
                    if (prepDays.size() == 0) prepDays = getDaysWithLowestScore(task.getDeadline());
                    Day day  =  getRandomDay(prepDays);
                    day.tasksofday.add(blocks.get(0));
                    day.addscore(120);
                    blocks.remove(0);
                }
            }
        });

        days.forEach((key, value)->{
            value.tasksofday.forEach(taskBlock->{
                tasks.forEach(task->{
                    if (taskBlock.getTask().getName().equals(task.getName())){

                    }
                });
            });
        });

        TimeElementDatabase database = new TimeElementDatabase();
        database.resetTimeElements(Controller.getUser(input.getString("token")).getId());


    }


    public Day getRandomDay (HashMap<LocalDate, Day> e) {
        Random rand = new Random();
        Set keyset = e.keySet();
        int randI = rand.nextInt(keyset.size());
        Day day = e.get(keyset.toArray()[randI]);
        e.remove(keyset.toArray()[randI]);
        return day;
    }


    public HashMap<LocalDate, Day> fillDays(LocalDate end, HashMap<Integer, ArrayList<Appointment>> appointments, String token, String timeOffset){

        HashMap<LocalDate, Day> days = new HashMap<>();
        TimeElementDatabase database = new TimeElementDatabase();

        for (LocalDate date = today; date.isBefore(end); date = date.plusDays(1)){
            Day day = new Day(date);
            if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7){
                day.addscore(100);
            }

            appointments.get(date.getDayOfWeek().getValue() - 1).forEach(app->{
                day.appointmentsOfToday.add(app);
            });

            days.put(date, day);
        }
        String timeB = Util.DateToString(Util.createCalender(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
        String timeE = Util.DateToString(Util.createCalender(Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
        database.getAppointmentsOnDate(new JSONArray(),timeB, timeE, token, timeOffset).forEach(appointment->{
            DateAppointment appointment1 = JsonElementParser.parseDateAppointment((JSONObject) appointment);
            days.get(appointment1.getDate()).addAppointment(appointment1);
        });

        days.forEach((key, value)->{
            value.calculatePoints();
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

    public HashMap<LocalDate, Day> getDaysWithLowestScore(LocalDate deadline){
        HashMap<LocalDate, Day> output = new HashMap<>();

        Object[] keySet = days.keySet().toArray();
        int lowest = days.get(keySet[0]).getDayscore();
        for (int i = 1; i < keySet.length; i++){
            LocalDate date = (LocalDate) keySet[i];
            if (date.isBefore(deadline)) {
                if (days.get(keySet[i]).getDayscore() < lowest) {
                    lowest = days.get(keySet[i]).getDayscore();
                }
            }
        }

        int finalLowest = lowest;
        days.forEach((key, value) -> {
            if (value.getDayscore() == finalLowest) {
                if (key.isBefore(deadline)) {
                    output.put(key, value);
                }
            }
        });
        return output;
    }


}

