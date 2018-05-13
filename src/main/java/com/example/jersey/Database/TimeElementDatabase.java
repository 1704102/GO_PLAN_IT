package com.example.jersey.Database;

import com.example.jersey.Controller.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class TimeElementDatabase extends DatabaseHelper{

    public JSONArray getTimeElements(JSONObject input){
        Calendar calendar = Util.createCalender(input.getLong("date"));
        System.out.println(calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        JSONArray array = new JSONArray();
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from time_element as a left join task as b on a.task_id = b.id");
            ResultSet s = statement.executeQuery();

            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getString("a.id"));
                object.put("name", s.getString("b.name"));
                object.put("timeB", Util.getTime(s.getTime("a.timeB"), input.getString("timeOffset")));
                object.put("timeE", Util.getTime(s.getTime("a.timeE"), input.getString("timeOffset")));
                object.put("day", Util.getDay(s.getTimestamp("a.timeB")));
                if(s.getString("a.task_id") != null){
                    object.put("type", "task");
                }else{
                    object.put("type", "appointment");
                }
                array.put(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();;
        return array;
    }


}
