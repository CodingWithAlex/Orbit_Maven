package SGP4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @ClassNameADtoJD
 * @Discription
 * 时间日期转换：
 * 公历时间转儒略日
 * @Authorxu
 * @Date2022/4/517:19
 * @Version1.0
 **/
public class TimeTransfer {
    //Date形式的时间转儒略日
    public static Double adToJulianDay(int year,int mouth,int day,int hour,int minute,int second){
        Double JulianDay = adToJD(year,mouth,day,hour,minute,second);
        return JulianDay;
    }
    public static Double adToJulianDay(Date date){
        int year,mouth,day,hour,minute,second;
        String transformTime = transformatDate(date);
        year = Integer.valueOf(transformTime.substring(0,4));
        mouth = Integer.valueOf(transformTime.substring(5,7));
        day = Integer.valueOf(transformTime.substring(8,10));
        hour = Integer.valueOf(transformTime.substring(11,13));
        minute = Integer.valueOf(transformTime.substring(14,16));
        second = Integer.valueOf(transformTime.substring(17,19));
        Double JulianDay = adToJD(year,mouth,day,hour,minute,second);
        return JulianDay;
    }
    public static Double adToJD(int year,int mouth,int day,int hour,int minute,int second){
        if (mouth == 1||mouth == 2){
            year = year - 1;
            mouth = mouth + 12;
        }
        int A = (int)(year/100);
        int B = 2 - A + (int)(A/4);
        Double JD = (int)( 365.25*(year+4716) ) + (int)( 30.6001*(mouth+1) ) + day + B - 1524.5;
        JD = JD + (hour*60*60 + minute*60 + second + 0.0)/86400;
        return JD;
    }
    //date日期转换，将Date格式的时间转为"yyyy-MM-dd HH:mm:ss"形式的字符串
    public static String transformatDate(Date date) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String transformDate=simpleDateFormat.format(date);
        return transformDate;
    }
    //date日期转换，将Date格式的时间转为"yyyy-MM-dd HH:mm:ss"形式的字符串
    public static Date DateToStrng(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newdate = null;
        try {
            newdate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newdate;
    }
    public static Date utf8ToUtc(Date date) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(date);
        nowTime.add(Calendar.HOUR, -8);
        Date utcTime = nowTime.getTime();
        return utcTime;
    }
    public static Date utcToUtf8(Date date) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(date);
        nowTime.add(Calendar.HOUR, 8);
        Date utcTime = nowTime.getTime();
        return utcTime;
    }
}
