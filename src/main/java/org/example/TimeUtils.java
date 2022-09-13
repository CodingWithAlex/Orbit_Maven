package org.example;

import org.example.CallbackEntry.InputParam;
import org.example.CallbackEntry.ThreePoint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.example.CallbackEntry.Callback.getThreePoint;

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
        InputParam inputParam = new InputParam();
        inputParam.setStartTime(new Date());
        inputParam.setEndTime(addMinute(24*60,new Date()));
        inputParam.setSatelliteName("NOOR-2");
        inputParam.setSiteName("西安");
        inputParam.setLine1("1 51954U 22024A   22095.15428803  .00012839  00000+0  58480-3 0  9993");
        inputParam.setLine2("2 51954  58.2896  32.5975 0013833 295.0466 199.1831 15.20983858  4246");
        inputParam.setSiteAltitude(0.58);
        inputParam.setSiteLongitude(107.4);
        inputParam.setSiteLatitude(34.42);
        long actionTime = System.currentTimeMillis();
        List<ThreePoint> threePointLists = getThreePoint(inputParam);
        long finalTime = System.currentTimeMillis();
        System.out.println("程序运行时间："+(finalTime-actionTime)+"ms");
    }
}
