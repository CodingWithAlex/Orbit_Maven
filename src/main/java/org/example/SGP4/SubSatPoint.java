package org.example.SGP4;

/**
 * @ClassNameSubSatPoint
 * @Discription
 * 地固系转星下点经纬度：
 * x,y,z->longitude,latitude
 * @Authorxu
 * @Date2022/8/21 22:19
 * @Version1.0
 **/
public class SubSatPoint {
    public static Double subSatLon(double x, double y, double z) {
        double longitude = Math.atan2(y, x) * 180.0 / Math.PI;
        return longitude;
    }

    public static Double subSatLat(double x, double y, double z) {
        double r = Math.sqrt(x * x + y * y);
        double latitude = Math.atan2(z, r) * 180.0 / Math.PI;
        return latitude;
    }
}
