package com.example.jersey.Database;

import com.example.jersey.Controller.Controller;
import com.example.jersey.Controller.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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



                array.put(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();

        return array;
    }

    public JSONArray getTask(JSONObject input){
        JSONArray array = new JSONArray();

        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from task where id = ?");
            preparedStatement.setInt(1,input.getInt("id"));
            ResultSet s = preparedStatement.executeQuery();
            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getInt("id"));
                object.put("name", s.getString("name"));
                object.put("description", s.getString("description"));
                object.put("date", s.getDate("deadline"));
                if (s.getTime("deadline") != null) {
                    object.put("time", Util.getTime(s.getTime("deadline"), input.getString("timeOffset")));
                }
                array.put(object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();

        return array;
    }

    public int addTasks(JSONObject input) {
        connect();
        int id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into task (user_id) values (?)");
            preparedStatement.setInt(1, Controller.getUser(input.getString("token")).getId());
            preparedStatement.execute();

            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT max(id) as max FROM task where user_id = ?");
            preparedStatement1.setInt(1,Controller.getUser(input.getString("token")).getId());
            ResultSet s = preparedStatement1.executeQuery();
            while (s.next()){
                id = s.getInt("max");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
        return id;
    }

    public JSONArray getFullTasks(JSONObject input){
        JSONArray array = new JSONArray();
        connect();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from task where id = ?");
            preparedStatement.setInt(1, input.getInt("id"));
            ResultSet s = preparedStatement.executeQuery();
            while (s.next()){
                JSONObject object = new JSONObject();
                object.put("id", s.getInt("id"));
                object.put("name", s.getString("name"));
                object.put("description", s.getString("description"));
                if (s.getDate("deadline") != null){
                    object.put("deadline", Util.DateToString(Util.createCalender(s.getDate("deadline").getTime())));
                    object.put("time", Util.getTime(s.getTime("deadline"), input.getString("timeOffset")));
                }else{
                    object.put("deadline", "");
                    object.put("time", "");
                }
                array.put(object.put("tasks", getSubTasks(s.getInt("id"))));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();

        return array;
    }

    public JSONArray getSubTasks(int id){
        JSONArray output = new JSONArray();
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement("select * from subtask where task_id = ?");
            preparedStatement1.setInt(1, id);
            ResultSet s1 = preparedStatement1.executeQuery();

            while (s1.next()){

                JSONObject subtask = new JSONObject();
                subtask.put("id", s1.getInt("id"));
                subtask.put("name", s1.getString("name"));
                subtask.put("done", s1.getBoolean("done"));
                subtask.put("skipped", s1.getInt("skipped"));
                subtask.put("hours", s1.getInt("hours"));

                output.put(subtask);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }

    public void saveFullTask(JSONObject input) {
        connect();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from subtask where task_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(input.getString("id")));
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        saveTask(input);
        saveSubTasks(input);
        disconnect();
    }

    public void saveSubTasks(JSONObject input){
        try {
            JSONArray jsonArray = input.getJSONArray("subTasks");
            for(int i = 0; i < jsonArray.length(); i++) {
                System.out.println(jsonArray.getJSONObject(i).toString());
                JSONObject object = jsonArray.getJSONObject(i);
                PreparedStatement preparedStatement = connection.prepareStatement("insert into subtask (name,  hours, done, task_id, skipped) values (?,?,?,?,0)");
                preparedStatement.setString(1, object.getString("name"));
                preparedStatement.setInt(2, Integer.parseInt(object.getString("hours")));
                preparedStatement.setBoolean(3, object.getBoolean("done1"));
                preparedStatement.setInt(4, Integer.parseInt(input.getString("id")));
                preparedStatement.execute();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTask(JSONObject input){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update task set name=?, description=?, deadline=STR_TO_DATE(?,'%Y-%m-%d %H:%i') where id=?");
            preparedStatement.setString(1, input.getString("name"));
            preparedStatement.setString(2, input.getString("description"));
            preparedStatement.setString(3, String.valueOf(input.getString("date") + " " + input.getString("time")));
            preparedStatement.setInt(4, Integer.parseInt(input.getString("id")));
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteTask(JSONObject input) {
        connect();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from task where id = ?");
            preparedStatement.setInt(1, Integer.parseInt(input.getString("id")));
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        disconnect();
    }

    public ArrayList<String> getSubtasks(JSONObject input){
        ArrayList<String> arrayList = new ArrayList();

        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from subtask where task_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(input.getString("id")));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                arrayList.add(resultSet.getString("name"));
            }
        }catch (Exception e){

        }
        disconnect();

        return arrayList;
    }
}
