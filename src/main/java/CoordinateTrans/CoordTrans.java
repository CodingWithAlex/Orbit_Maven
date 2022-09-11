package CoordinateTrans;

import java.util.ArrayList;
import java.util.List;

public class CoordTrans {
    public CoordTrans() {
    }

    public static List<Double> coordTransfer(double longitude, double latitude, double altitude, double satXcoord, double satYcoord, double satZcoord) {
        List<Double> siteXyz = llaToEcef(longitude, latitude, altitude);
        List<Double> rae = ecefToEnu(longitude, latitude, siteXyz, satXcoord, satYcoord, satZcoord);
        return rae;
    }

    public static List<Double> ecefToEnu(double longitude, double latitude, List<Double> siteXyz, double x, double y, double z) {
        List<Double> rae = new ArrayList();
        double deg2rad = 0.017453292519943295;
        double rad2deg = 57.29577951308232;
        double lat = latitude * deg2rad;
        double lon = longitude * deg2rad;
        double EnuX = -Math.sin(lon) * (x - (Double)siteXyz.get(0)) + Math.cos(lon) * (y - (Double)siteXyz.get(1));
        double EnuY = -Math.cos(lon) * Math.sin(lat) * (x - (Double)siteXyz.get(0)) - Math.sin(lon) * Math.sin(lat) * (y - (Double)siteXyz.get(1)) + Math.cos(lat) * (z - (Double)siteXyz.get(2));
        double EnuZ = Math.cos(lon) * Math.cos(lat) * (x - (Double)siteXyz.get(0)) + Math.sin(lon) * Math.cos(lat) * (y - (Double)siteXyz.get(1)) + Math.sin(lat) * (z - (Double)siteXyz.get(2));
        double range = Math.sqrt(EnuX * EnuX + EnuY * EnuY + EnuZ * EnuZ);
        double azimuth = 0.0;
        double elevation = 0.0;
        if (EnuX >= 0.0 && EnuY > 0.0) {
            azimuth = Math.asin(EnuX / Math.sqrt(EnuX * EnuX + EnuY * EnuY));
        } else if (EnuX >= 0.0 && EnuY < 0.0) {
            azimuth = Math.PI - Math.asin(EnuX / Math.sqrt(EnuX * EnuX + EnuY * EnuY));
        } else if (EnuX < 0.0 && EnuY > 0.0) {
            azimuth = 6.283185307179586 + Math.asin(EnuX / Math.sqrt(EnuX * EnuX + EnuY * EnuY));
        } else {
            azimuth = Math.PI - Math.asin(EnuX / Math.sqrt(EnuX * EnuX + EnuY * EnuY));
        }

        if (EnuZ > 0.0) {
            elevation = 1.5707963267948966 - Math.asin(Math.sqrt(EnuX * EnuX + EnuY * EnuY) / range);
        } else {
            elevation = Math.asin(Math.sqrt(EnuX * EnuX + EnuY * EnuY) / range) - 1.5707963267948966;
        }

        rae.add(range);
        rae.add(azimuth * rad2deg);
        rae.add(elevation * rad2deg);
        return rae;
    }

    public static List<Double> llaToEcef(double longitude, double latitude, double altitude) {
        double WGS84_A = 6378137.0;
        double WGS84_f = 0.003352810664724998;
        double WGS84_E2 = WGS84_f * (2.0 - WGS84_f);
        double deg2rad = 0.017453292519943295;
        double lat = latitude * deg2rad;
        double lon = longitude * deg2rad;
        double N = WGS84_A / Math.sqrt(1.0 - WGS84_E2 * Math.sin(lat) * Math.sin(lat));
        double x = (N + altitude) * Math.cos(lat) * Math.cos(lon);
        double y = (N + altitude) * Math.cos(lat) * Math.sin(lon);
        double z = (N * (1.0 - WGS84_f) * (1.0 - WGS84_f) + altitude) * Math.sin(lat);
        List<Double> siteEcef = new ArrayList();
        siteEcef.add(x);
        siteEcef.add(y);
        siteEcef.add(z);
        return siteEcef;
    }
}
