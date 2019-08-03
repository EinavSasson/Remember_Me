package com.rememberme;

import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String getDayDate(Date dayDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayDate);
        return String.format("d", calendar.DAY_OF_MONTH);
    }
    public static String getMonthDate(Date monthDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthDate);
        return String.format("m", calendar.MONTH);
    }
}
