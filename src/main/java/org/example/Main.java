package org.example;

import org.example.CoordinateTrans.CoordTrans;
import org.example.SGP4.*;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long actionTime = System.currentTimeMillis();
        SGP4SatData data = new SGP4SatData();
        // tle data
        String name = "NOOR-2";
        String line1 = "1 51954U 22024A   22095.15428803  .00012839  00000+0  58480-3 0  9993";
        String line2 = "2 51954  58.2896  32.5975 0013833 295.0466 199.1831 15.20983858  4246";

        // options
        char opsmode = SGP4utils.OPSMODE_IMPROVED; // OPSMODE_IMPROVED
        SGP4unit.Gravconsttype gravconsttype = SGP4unit.Gravconsttype.wgs84;

        // read in data and ini SGP4 data
        boolean result1 = SGP4utils.readTLEandIniSGP4(name, line1, line2, opsmode, gravconsttype, data);
        if(!result1)
        {
            System.out.println("Error Reading / Ini Data, error code: " + data.error);
            return;
        }

        String startTime = "2022-04-09 16:53:32";
        String endTime = "2022-04-09 23:53:32";
        Date nowTime = new Date();
        System.out.println(TimeTransfer.transformatDate(nowTime));
        TimeTransfer timeTransfer = new TimeTransfer();
        nowTime = timeTransfer.utf8ToUtc(nowTime);
        Double nowJulianDay = timeTransfer.adToJulianDay(nowTime);
        // prop to a given date
        double propJD = 2454983.0; // JD to prop to
        double minutesSinceEpoch = (nowJulianDay-data.jdsatepoch)*24.0*60.0;
        double[] pos = new double[3];
        double[] vel = new double[3];

        boolean result = SGP4unit.sgp4(data, minutesSinceEpoch, pos, vel);
        if(!result)
        {
            System.out.println("Error in Sat Prop");
            return;
        }
        double longitude = 100.0;
        double latitude = 60.0;
        double altitude = 1.0;
        List<Double> rae = CoordTrans.coordTransfer(longitude,latitude,altitude,pos[0]*1000,pos[1]*1000,pos[2]*1000);

        SubSatPoint subSatPoint = new SubSatPoint();
        Double subSatLon = subSatPoint.subSatLon(pos[0],pos[1],pos[2]);
        Double subSatLat = subSatPoint.subSatLat(pos[0],pos[1],pos[2]);
        // output
        System.out.println("Epoch of TLE (JD): " + data.jdsatepoch);
        System.out.println(name + ", " + pos[0]+ ", " + pos[1]+ ", " + pos[2]+ ", " + vel[0]+ ", " + vel[1]+ ", " + vel[2]);
        System.out.println("r:"+rae.get(0)/1000+" |a:"+rae.get(1)+" |e:"+rae.get(2));
        System.out.println("SubSatPoint: "+subSatLon+", "+subSatLat);
        long finalTime = System.currentTimeMillis();
        System.out.println("程序运行时间："+(finalTime-actionTime)+"ms");
    }
}