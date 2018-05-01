package com.example.jersey.Database;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeElementDatabase extends DatabaseHelper{

    public JSONArray getTimeElements(){
        JSONArray array = new JSONArray();
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from time_element as a left join task as b on a.task_id = b.id");
            ResultSet s = statement.executeQuery();

            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getString("a.id"));
                object.put("name", s.getString("b.name"));
                object.put("timeB", s.getString("a.timeB"));
                object.put("timeE", s.getString("a.timeE"));
                if(s.getString("a.task_id") != null){
                    object.put("type", "task");
                }else{
                    object.put("type", "appointment");
                }
                System.out.println(object.toString());
                array.put(object);
            }
            System.out.println(array.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();;
        return array;
    }


}
