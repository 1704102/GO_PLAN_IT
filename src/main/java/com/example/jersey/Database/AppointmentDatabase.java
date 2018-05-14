package com.example.jersey.Database;

import com.example.jersey.Controller.Controller;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AppointmentDatabase extends DatabaseHelper{

    public void addAppointment(JSONObject appointment){
        connect();
        try {
            if (appointment.getString("date").equals("")) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into appointment (user_id,name, timeB, timeE, repeating) values (?,?,TIME(?),TIME(?),?);");
                preparedStatement.setInt(1, Controller.getUser(appointment.getString("token")).getId());
                preparedStatement.setString(2, appointment.getString("name"));
                preparedStatement.setString(3, appointment.getString("timeB"));
                preparedStatement.setString(4, appointment.getString("timeE"));
                preparedStatement.setString(5, appointment.getString("repeating"));
                preparedStatement.execute();
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into appointment (user_id,name, timeB, timeE, date) values (?,?,TIME(?),TIME(?),STR_TO_DATE(?,'%Y-%m-%d'));");
                preparedStatement.setInt(1, Controller.getUser(appointment.getString("token")).getId());
                preparedStatement.setString(2, appointment.getString("name"));
                preparedStatement.setString(3, appointment.getString("timeB"));
                preparedStatement.setString(4, appointment.getString("timeE"));
                preparedStatement.setString(5, appointment.getString("date"));
                preparedStatement.execute();
            }
        }catch (Exception e){e.printStackTrace();}
        disconnect();
    }

    public JSONArray getAppointments(JSONObject input) {
        JSONArray array = new JSONArray();

        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * , DATE_FORMAT(timeB, \"%H:%i\") as `timeBAltered` , DATE_FORMAT(timeE, \"%H:%i\") as `timeEAltered` from appointment where user_id = ?");
            preparedStatement.setInt(1,Controller.getUser(input.getString("token")).getId());
            ResultSet s = preparedStatement.executeQuery();
            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getInt("id"));
                object.put("name", s.getString("name"));
                object.put("date", s.getDate("date"));
                object.put("timeB", s.getString("timeBAltered"));
                object.put("timeE", s.getString("timeEAltered"));
                array.put(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
        return array;
    }

    public void deleteAppointment(int id) {
        connect();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("delete from appointment where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
    }
}
