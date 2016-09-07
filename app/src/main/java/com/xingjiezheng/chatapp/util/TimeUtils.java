package com.xingjiezheng.chatapp.util;

import java.util.Calendar;

/**
 * Created by XingjieZheng
 * on 2016/7/11.
 */
public class TimeUtils {

    private TimeUtils() {

    }

    public static String getDateByTimeMillis(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        if (isSameDate(timeMillis, getCurrentTimeMillis())) {
            return calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        } else {
            return /*calendar.get(Calendar.YEAR) + "."
                    + */calendar.get(Calendar.MONTH) + "月"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "日";
        }
    }

    public static boolean isSameDate(long timeMillis1, long timeMillis2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis1);
        String date1 = calendar.get(Calendar.YEAR) + "."
                + calendar.get(Calendar.MONTH) + "."
                + calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(timeMillis2);
        String date2 = calendar.get(Calendar.YEAR) + "."
                + calendar.get(Calendar.MONTH) + "."
                + calendar.get(Calendar.DAY_OF_MONTH);
        return date1.equals(date2);
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
