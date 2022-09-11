package CallbackEntry;

import CoordinateTrans.CoordTrans;
import SGP4.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassNameCallback
 * @Discription
 * 可见性预报接口调用：
 * 根据时间范围和两行计算三点报
 * @Authorxu
 * @Date2022/8/21 22:29
 * @Version1.0
 **/
public class   Callback {

    public static ThreePoint getThreePoint(){
        ThreePoint threePoint = new ThreePoint();
        return threePoint;
    }

    public static RaeParam getRaeParam(InputParam inputParam){
        RaeParam raeParam = new RaeParam();
        SGP4SatData data = new SGP4SatData();
        char opsmode = SGP4utils.OPSMODE_IMPROVED; // OPSMODE_IMPROVED
        SGP4unit.Gravconsttype gravconsttype = SGP4unit.Gravconsttype.wgs84;
        boolean result1 = SGP4utils.readTLEandIniSGP4(inputParam.getSatelliteName(), inputParam.getLine1(), inputParam.getLine2(), opsmode, gravconsttype, data);
        if(!result1)
        {
            System.out.println("Error Reading / Ini Data, error code: " + data.error);
        }
        TimeTransfer timeTransfer = new TimeTransfer();
        Date nowTime = timeTransfer.utf8ToUtc(inputParam.getNowTime());
        Double nowJulianDay = timeTransfer.adToJulianDay(nowTime);
        double minutesSinceEpoch = (nowJulianDay-data.jdsatepoch)*24.0*60.0;
        double[] pos = new double[3];
        double[] vel = new double[3];
        boolean result = SGP4unit.sgp4(data, minutesSinceEpoch, pos, vel);
        if(!result)
        {
            System.out.println("Error in Sat Prop");
        }
        List<Double> rae = CoordTrans.coordTransfer(inputParam.getSiteLongitude(),inputParam.getSiteLatitude(),inputParam.getSiteAltitude(),
                pos[0]*1000,pos[1]*1000,pos[2]*1000);
        SubSatPoint subSatPoint = new SubSatPoint();
        raeParam.setRange(rae.get(0));
        raeParam.setAzimuth(rae.get(1));
        raeParam.setElevation(rae.get(2));
        raeParam.setLongitude(subSatPoint.subSatLon(pos[0],pos[1],pos[2]));
        raeParam.setLatitude(subSatPoint.subSatLat(pos[0],pos[1],pos[2]));
        return raeParam;
    }




}
