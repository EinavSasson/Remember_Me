package com.rememberme;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String getDateOfBirth(long userDob) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(userDob));
        return String.format("%d/%d/%d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR)
        );
    }
}
