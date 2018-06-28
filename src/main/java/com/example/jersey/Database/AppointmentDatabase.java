package com.example.jersey.Database;

import com.example.jersey.Controller.Controller;
import com.example.jersey.Controller.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;


public class AppointmentDatabase extends DatabaseHelper {

    public void addAppointment(JSONObject appointment) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public JSONArray getAppointments(JSONObject input) {
        JSONArray array = new JSONArray();

        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * , DATE_FORMAT(timeB, '%H:%i') as `timeBAltered` , DATE_FORMAT(timeE, '%H:%i') as `timeEAltered` from appointment where user_id = ?");
            preparedStatement.setInt(1, Controller.getUser(input.getString("token")).getId());
            ResultSet s = preparedStatement.executeQuery();
            while (s.next()) {
                JSONObject object = new JSONObject();
                object.put("id", s.getInt("id"));
                object.put("name", s.getString("name"));
                object.put("date", s.getDate("date"));
                object.put("timeB", s.getString("timeBAltered"));
                object.put("timeE", s.getString("timeEAltered"));
                array.put(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
        return array;
    }

    public void alterAppointment(JSONObject appointment) {
        connect();
        try {
            if (appointment.getString("date").equals("")) {
                PreparedStatement preparedStatement = connection.prepareStatement("update appointment set name = ?, timeB = TIME(?), timeE = TIME(?), repeating = ? where id  = ?");
                preparedStatement.setString(1, appointment.getString("name"));
                preparedStatement.setString(2, appointment.getString("timeB"));
                preparedStatement.setString(3, appointment.getString("timeE"));
                preparedStatement.setString(4, appointment.getString("repeating"));
                preparedStatement.setInt(5, appointment.getInt("id"));
                preparedStatement.execute();
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement("update appointment set name = ?, timeB = TIME(?), timeE = TIME(?), date = STR_TO_DATE(?,'%Y-%m-%d') where id  = ?");
                preparedStatement.setString(1, appointment.getString("name"));
                preparedStatement.setString(2, appointment.getString("timeB"));
                preparedStatement.setString(3, appointment.getString("timeE"));
                preparedStatement.setString(4, appointment.getString("date"));
                preparedStatement.setInt(5, appointment.getInt("id"));
                preparedStatement.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public void deleteAppointment(int id) {
        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from appointment where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public JSONObject getAppointment(int id, String timeOffset) {
        connect();
        JSONObject appointment = new JSONObject();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from appointment where id = ?");
            statement.setInt(1,id);
            ResultSet s = statement.executeQuery();
            while (s.next()){
                appointment.put("id", s.getInt("id"));
                appointment.put("name", s.getString("name"));
                appointment.put("timeB", Util.getTime(s.getTime("timeB"), timeOffset));
                appointment.put("timeE", Util.getTime(s.getTime("timeE"), timeOffset));
                if (s.getDate("date") != null){
                    appointment.put("date", LocalDate.parse(Util.DateToString(Util.createCalender(s.getDate("date").getTime()))));
                }else {
                    String repeating = s.getString("repeating");
                    String[] split = repeating.split(",");
                    int[] repeat = new int[split.length];
                    for( int i = 0; i < split.length; i++){
                       repeat[i] = Util.dayToInt(split[i]);
                    }
                    appointment.put("repeating", repeat);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
        return appointment;
    }

}
