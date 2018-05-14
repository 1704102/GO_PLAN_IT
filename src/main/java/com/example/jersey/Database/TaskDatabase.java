package com.example.jersey.Database;

import com.example.jersey.Controller.Controller;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaskDatabase extends DatabaseHelper {

    public JSONArray getTasks(JSONObject input){
        JSONArray array = new JSONArray();

        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from task where user_id = ?");
            preparedStatement.setInt(1,Controller.getUser(input.getString("token")).getId());
            ResultSet s = preparedStatement.executeQuery();
            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getInt("id"));
                object.put("name", s.getString("name"));
                object.put("description", s.getString("description"));
                object.put("date", s.getDate("deadline"));
                object.put("time", s.getTime("deadline"));
                System.out.println(object.toString());
                array.put(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();

        return array;
    }
}
