package com.example.jersey.Model;

public class User {

    int id;
    String token;
    int sessionTime;

    public User(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public int getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(int sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getToken() {
        return token;
    }
}
