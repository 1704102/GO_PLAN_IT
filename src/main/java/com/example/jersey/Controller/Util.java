package com.example.jersey.Controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Util {

    public static Calendar createCalender(long x){
        System.out.println(x);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(x));
        System.out.println(c.getTime().toString());
        return c;
    }

    public static Calendar getFirstDayOfTheWeek(Calendar calendar){
        int pDay = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        Calendar cal = calendar;
        cal.add(Calendar.DATE, -1 * pDay );
        return cal;
    }

    public static String getTime(Time date, String offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String hour = String.valueOf(date.getHours() + ((Integer.parseInt(offset) - date.getTimezoneOffset()) / 60));
        String minutes = String.valueOf(date.getMinutes());
        if(minutes.equals("0")){
            minutes = "00";
        }
        return hour + ":" + minutes;
    }

    public static String getDay(Timestamp date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (day == -1) day = 6;
        return String.valueOf(day);
    }

    public static String generateToken(){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


}
