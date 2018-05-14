package com.example.jersey.Database;

import com.example.jersey.Controller.Controller;
import org.json.JSONObject;

import java.sql.PreparedStatement;

public class AppointmentDatabase extends DatabaseHelper{

    public void addAppointment(JSONObject appointment){
        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into appointment (user_id,name, timeB, timeE, repeating) values (?,?,TIME(?),TIME(?),?);\n");
            preparedStatement.setInt(1, Controller.getUser(appointment.getString("token")).getId());
            preparedStatement.setString(2,appointment.getString("name"));
            preparedStatement.setString(3,appointment.getString("timeB"));
            preparedStatement.setString(4,appointment.getString("timeE"));
            preparedStatement.setString(5,appointment.getString("repeating"));
            preparedStatement.execute();
        }catch (Exception e){e.printStackTrace();}
        disconnect();
    }
}
