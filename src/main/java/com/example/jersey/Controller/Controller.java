package com.example.jersey.Controller;

import com.example.jersey.Model.User;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {

    private static ArrayList<User> users;

    public Controller(){
        users = new ArrayList<>();
        runApplication();
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static User getUser(String token){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getToken().equals(token)){
                return users.get(i);
            }
        }
        return null;
    }

    public void runApplication(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < users.size(); i++){
                    if (users.get(i).getSessionTime() == 14){
                        users.remove(i);
                    }else{
                        users.get(i).setSessionTime(users.get(i).getSessionTime() + 1);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,(1000 * 60));
    }


}
