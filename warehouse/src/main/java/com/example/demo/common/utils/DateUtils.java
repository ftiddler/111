package com.example.demo.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Huang Yuchen
 * @version 1.0
 * @date 2021-03-21 13:40
 */
public class DateUtils {

    public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date CurrentDatetime() {
        return new Date();
    }

    public static Long currentTimestamp() {
        return CurrentDatetime().getTime();
    }

    public static String CurrentDatetimeString() {
        return CurrentDatetimeString(DATETIME_FORMAT);
    }

    public static String CurrentDatetimeString(String pattern) {
        if (pattern == null || pattern.isEmpty()) pattern = DATETIME_FORMAT;
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat(pattern);
        String format = dateFormat.format(date);
        return format;
    }

}
