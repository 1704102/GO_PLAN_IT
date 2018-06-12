package com.example.jersey.Controller;

import com.example.jersey.Appointment.DateAppointment;
import com.example.jersey.Appointment.RepeatingAppointment;
import com.example.jersey.Database.TaskDatabase;
import com.example.jersey.Model.HoldingElement.SubTask;
import com.example.jersey.Model.HoldingElement.Task;
import javafx.scene.media.SubtitleTrack;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

    public static ArrayList<Task> parseTaskArray(JSONObject input, int offset){
        ArrayList<Task> tasks = new ArrayList<>();
        TaskDatabase database = new TaskDatabase();
        JSONArray array = database.getTasks(input);
        array.forEach(task->{
            tasks.add(parseTask((JSONObject) task, offset));
        });
        return tasks;
    }

    private static Task parseTask(JSONObject input, int offset){
       String name = input.getString("name");
        Date date = new Date();
        Time time = new Time(0,0,0);
        try {
            date = (Date)input.get("date");
            time = (Time)input.get("time");
        }catch (Exception e){}
        LocalDate dat2 = LocalDate.parse(Util.DateToString(Util.createCalender(date.getTime())));
        ArrayList<SubTask> subTasks = getSubtasks(input.getInt("id"));
        return new Task(name, dat2, Util.applyOffset(time, offset), subTasks);
    }

    private static ArrayList<SubTask> getSubtasks(int id){
        ArrayList<SubTask> subTasks = new ArrayList<>();
        TaskDatabase database = new TaskDatabase();
        database.connect();
        JSONArray array = database.getSubTasks(id);
        array.forEach(subTask->{
            subTasks.add(parseSubTask((JSONObject)subTask));
        });
        database.disconnect();
        return subTasks;
    }

    private static SubTask parseSubTask(JSONObject input){
        int id = input.getInt("id");
        String name = input.getString("name");
        Date date = new Date();
        try {
            date = (Date)input.get("date");
        }catch (Exception e){}
        LocalDate dat2 = LocalDate.parse(Util.DateToString(Util.createCalender(date.getTime())));
        boolean finished = input.getBoolean("done");
        int estimate = input.getInt("hours");

        SubTask subTask = new SubTask(id,name, dat2, estimate, finished);
        return subTask;
    }


}
