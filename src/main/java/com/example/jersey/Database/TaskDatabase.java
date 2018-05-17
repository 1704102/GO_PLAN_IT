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
                    int hours = s.getTime("deadline").getHours();
                    StringBuilder stringBuilder = new StringBuilder();
                    if (s.getTime("deadline").getMinutes() < 10){
                        stringBuilder.append("0" + s.getTime("deadline").getMinutes());
                    }else {
                        stringBuilder.append(s.getTime("deadline").getMinutes());
                    }
                    object.put("time", s.getTime("deadline").getHours() + ":" + stringBuilder.toString());
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
          PreparedStatement preparedStatement = connection.prepareStatement("select * from task where user_id = ?");
          preparedStatement.setInt(1, Controller.getUser(input.getString("token")).getId());
          ResultSet s = preparedStatement.executeQuery();
          while (s.next()){
              JSONObject object = new JSONObject();
              object.put("id", s.getInt("id"));
              object.put("name", s.getString("name"));
              object.put("description", s.getString("description"));
              object.put("deadline", s.getDate("deadline"));

              JSONArray subtasks = new JSONArray();

              PreparedStatement preparedStatement1 = connection.prepareStatement("select * from subtask where task_id = ?");
              preparedStatement1.setInt(1, s.getInt("id"));
              ResultSet s1 = preparedStatement1.executeQuery();

              while (s1.next()){

                  JSONObject subtask = new JSONObject();
                  subtask.put("id", s1.getInt("id"));
                  subtask.put("name", s1.getString("name"));
                  subtask.put("done", s1.getBoolean("done"));
                  subtask.put("skipped", s1.getInt("skipped"));
                  subtask.put("hours", s1.getInt("hours"));

                  subtasks.put(subtask);
              }

              object.put("tasks", subtasks);
              array.put(object);
          }
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();

        return array;
    }

        public void saveFullTask(JSONObject input) {
        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update task set name=?, description=?, deadline=STR_TO_DATE(?,'%Y-%m-%d %H:%i') where id=?");
            preparedStatement.setString(1,input.getString("name"));
            preparedStatement.setString(2,input.getString("description"));
            preparedStatement.setString(3,String.valueOf(input.getString("date") + " " + input.getString("time")));
            preparedStatement.setInt(4, Integer.parseInt(input.getString("id")));
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        disconnect();
    }
}
