package com.example.jersey.Controller;

import java.util.Calendar;

public class Util {

    public static Calendar createCalander(long x){
        return null;
    }

    public static Calendar getFirstDayOfTheWeek(Calendar calendar){
        int pDay = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        Calendar cal = calendar;
        cal.add(Calendar.DATE, -1 * pDay );
        return cal;
    }


}
