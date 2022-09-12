package org.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public  static Integer  timeSubtractive(Date startTime,Date endTime){
        long startTimeNum = startTime.getTime();
        long endTimeNum = endTime.getTime();
        Integer timeSubtractive = (int)(endTimeNum - startTimeNum)/1000/60;
        return timeSubtractive;
    }
    public  static Date addMinute(Integer min,Date startTime){
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(startTime);
        nowTime.add(Calendar.MINUTE, min);
        return nowTime.getTime();
    }
    public  static Date addSecond(Integer sec,Date startTime){
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(startTime);
        nowTime.add(Calendar.SECOND, sec);
        return nowTime.getTime();
    }


    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(timeSubtractive(date,addSecond(61,date)));
    }
}
