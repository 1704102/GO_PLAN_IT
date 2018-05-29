package com.example.jersey.Controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Util {

    public static Calendar createCalender(long x){
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
        if (hour.equals("-1")) hour = "23";
        String minutes = String.valueOf(date.getMinutes());
        if(Integer.parseInt(minutes) < 10){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0" + minutes);
            minutes = stringBuilder.toString();
        }
        if(Integer.parseInt(hour) < 10){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0" + hour);
            hour = stringBuilder.toString();
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
        return salt.toString();
    }

    public static String DateToString(Calendar calendar){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.DAY_OF_MONTH));
        stringBuilder.append("-");
        stringBuilder.append(calendar.get(Calendar.MONTH) + 1);
        stringBuilder.append("-");
        stringBuilder.append(calendar.get(Calendar.YEAR));
        return stringBuilder.toString();
    }

    public static int dayToInt(String day){
        switch (day){
            case "Monday":return 0;
            case "Tuesday":return 1;
            case "Wednesday":return 2;
            case "Thursday":return 3;
            case "Friday":return 4;
            case "Saturday":return 5;
            case "Sunday":return 6;
            default: return 0;
        }
    }


}
