package com.example.jersey.Database;

import com.example.jersey.Controller.Controller;
import com.example.jersey.Controller.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class TimeElementDatabase extends DatabaseHelper{

    public JSONArray getTimeElements(JSONObject input){
        Calendar dayStart = Util.createCalender(input.getLong("date"));
        Calendar dayEnd = Calendar.getInstance();
        dayEnd.setTime(dayStart.getTime());
        dayEnd.add(Calendar.DAY_OF_MONTH , 7);

        String dateStart = Util.DateToString(dayStart);
        String dateEnd = Util.DateToString(dayEnd);

        JSONArray array = new JSONArray();

        getTasks(array,dateStart,dateEnd,input.getString("token"), input.getString("timeOffset"));
        getAppointmentsOnDate(array,dateStart,dateEnd,input.getString("token"), input.getString("timeOffset"));
        getAppointmentsOnRepeat(array,input.getString("token"), input.getString("timeOffset"));

        System.out.println(array.toString());

        return array;
    }

    public JSONArray getTasks(JSONArray array, String timeB, String timeE, String token, String timeOffset){
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from time_element as a left join subtask as b on a.subtask_id = b.id left join task as c on b.task_id = c.id where a.timeB BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d') and c.user_id = ?");
            statement.setString(1,timeB);
            statement.setString(2,timeE);
            statement.setInt(3, Controller.getUser(token).getId());
            ResultSet s = statement.executeQuery();

            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getString("a.id"));
                object.put("name", s.getString("name"));
                object.put("timeB", Util.getTime(s.getTime("timeB"),timeOffset));
                object.put("timeE", Util.getTime(s.getTime("timeE"), timeOffset));
                object.put("day", Util.getDay(s.getTimestamp("timeB")));
                object.put("type", "task");
                array.put(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();;
        return array;
    }

    public JSONArray getAppointmentsOnDate(JSONArray array, String timeB, String timeE, String token, String timeOffset){
        connect();
        System.out.println(token);
        try {
            PreparedStatement statement = connection.prepareStatement("select * from appointment where date BETWEEN STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d') and user_id = ?");
            statement.setString(1,timeB);
            statement.setString(2,timeE);
            statement.setInt(3, Controller.getUser(token).getId());
            ResultSet s = statement.executeQuery();


            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getString("id"));
                object.put("name", s.getString("name"));
                object.put("timeB", Util.getTime(s.getTime("timeB"), timeOffset));
                object.put("timeE",Util.getTime(s.getTime("timeE"), timeOffset));
                object.put("day", Util.getDay(s.getTimestamp("date")));
                object.put("type", "appointment");
                object.put("date", s.getDate("date"));
                array.put(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();;
        return array;
    }

    public JSONArray getAppointmentsOnRepeat(JSONArray array, String token, String timeOffset){
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from appointment where repeating != \"\" and user_id = ?");
            statement.setInt(1, Controller.getUser(token).getId());
            ResultSet s = statement.executeQuery();

            while (s.next()){
                String[] days = s.getString("repeating").split(",");
                for(String day: days){
                    JSONObject object = new JSONObject();
                    object.put("id", s.getString("id"));
                    object.put("name", s.getString("name"));
                    object.put("timeB", Util.getTime(s.getTime("timeB"), timeOffset));
                    object.put("timeE",Util.getTime(s.getTime("timeE"), timeOffset));
                    object.put("day", Util.dayToInt(day));
                    object.put("type", "appointment");
                    array.put(object);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();;
        return array;
    }

    public void resetTimeElements(int token) {
        connect();

        try {
            PreparedStatement statement = connection.prepareStatement("delete a from time_element as a left join subtask as b on a.subtask_id = b.id left join task as c on b.task_id = c.id where c.user_id = ?");
            statement.setInt(1, token);
            statement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
    }
}
