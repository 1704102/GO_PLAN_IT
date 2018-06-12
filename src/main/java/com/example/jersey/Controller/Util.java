package com.example.jersey.Controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Util {

    public static Calendar createCalender(long x) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(x));
        return c;
    }

    public static Calendar getFirstDayOfTheWeek(Calendar calendar) {
        int pDay = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        Calendar cal = calendar;
        cal.add(Calendar.DATE, -1 * pDay);
        return cal;
    }

    public static String getTime(Time date, String offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = date.getHours() + ((Integer.parseInt(offset) - date.getTimezoneOffset()) / 60);
        if (hour==-1) hour = 23;

        return nullCheck(hour )+ ":" + nullCheck(date.getMinutes());
    }

    public static String getDay(Timestamp date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (day == -1) day = 6;
        return String.valueOf(day);
    }

    public static String generateToken() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public static String DateToString(Calendar calendar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(calendar.get(Calendar.YEAR));
        stringBuilder.append("-");
        stringBuilder.append(nullCheck(calendar.get(Calendar.MONTH) + 1));
        stringBuilder.append("-");
        stringBuilder.append(nullCheck(calendar.get(Calendar.DAY_OF_MONTH)));
        return stringBuilder.toString();
    }

    public static String nullCheck(int number){
        StringBuilder output = new StringBuilder();
        if(number < 10){
            output.append("0" + number);
            return output.toString();
        }else {return String.valueOf(number);}
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

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static LocalDate stringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-dd-mm", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(string, formatter);
        System.out.println(date); // 2010-01-02
        return date;
    }

    public static Time applyOffset(Time time, int offset){
        time.setTime(time.getTime() + (offset * 500 * 60));
        return time;
    }


}
