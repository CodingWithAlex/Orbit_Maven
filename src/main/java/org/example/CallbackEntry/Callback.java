package org.example.CallbackEntry;

import org.example.CoordinateTrans.CoordTrans;
import org.example.SGP4.*;
import org.example.SGP4.SubSatPoint;

import java.util.*;

import static org.example.TimeUtils.*;
import static org.example.CallbackEntry.ThreePoint.*;

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
    public static List<ThreePoint> getThreePoint(InputParam inputParam){
        List<ThreePoint> threePoints = new ArrayList<>();
        InputParam inputParamNext = coverent(inputParam);
        InputParam inputParamAfter = coverent(inputParam);
        Integer index = timeSubtractive(inputParam.getStartTime(),inputParam.getEndTime())+1;
        List<RaeParam> raeParamLists= new ArrayList<>();
        Map<Integer,List<RaeParam>> raeMap = new TreeMap<>();
        for (int i = 0; i < index; i++) {
            inputParam.setNowTime(addMinute(i,inputParam.getStartTime()));
            inputParamNext.setNowTime(addMinute(i+1,inputParam.getStartTime()));
            if (getRaeParam(inputParam).getElevation()>0){
                raeParamLists.add(getRaeParam(inputParam));
                if (getRaeParam(inputParamNext).getElevation()<0){
                    raeMap.put(i,raeParamLists);
                    raeParamLists = new ArrayList<>();
                }
            }
        }
        raeMap.forEach((key,value)->{
            ThreePoint threePoint = new ThreePoint();
            //计算起始点的rae星下点经纬度等
            inputParam.setNowTime(value.get(0).getTime());
            for (int i = 0; getRaeParam(inputParam).getElevation()>0 ; i++) {
                inputParam.setNowTime(addSecond(-1*i,value.get(0).getTime()));
            }
            inputParam.setNowTime(addSecond(1,inputParam.getNowTime()));
            RaeParam raeParam = getRaeParam(inputParam);
            threePoint.setArcNumber(inputParam.getSatelliteName()+"_"+inputParam.getSiteName()+"_"+key+"_"+(int)(1000000*Math.random()));
            threePoint.setStartStationTime(inputParam.getNowTime());
            threePoint.setStartAzimuth(raeParam.getAzimuth());
            threePoint.setStartElevation(raeParam.getElevation());
            threePoint.setStartRange(raeParam.getRange());
            ThreePoint.SubSatPoint subSatPoint = new ThreePoint.SubSatPoint();
            subSatPoint.setLongitude(raeParam.getLongitude());
            subSatPoint.setLatitude(raeParam.getLatitude());
            threePoint.setStartSubSat(subSatPoint);
            //计算出站点的rae星下点经纬度等
            inputParam.setNowTime(value.get(value.size()-1).getTime());
            for (int i = 0; getRaeParam(inputParam).getElevation()>0 ; i++) {
                inputParam.setNowTime(addSecond(i,value.get(0).getTime()));
            }
            inputParam.setNowTime(addSecond(-1,inputParam.getNowTime()));
            raeParam = getRaeParam(inputParam);
            threePoint.setEndStationTime(inputParam.getNowTime());
            threePoint.setEndAzimuth(raeParam.getAzimuth());
            threePoint.setEndElevation(raeParam.getElevation());
            threePoint.setEndRange(raeParam.getRange());
            subSatPoint.setLongitude(raeParam.getLongitude());
            subSatPoint.setLatitude(raeParam.getLatitude());
            threePoint.setEndSubSat(subSatPoint);
            //计算最高点的rae星下点经纬度等
//            Integer raeSize = value.size();

            boolean isTop = false;
            // 确定最高点的起始位置
            int valueIndex = 0;
            for (int i = 1; i < value.size()-1; i++) {
                if (value.get(i).getElevation()>value.get(i+1).getElevation()&&value.get(i).getElevation()>value.get(i-1).getElevation()){
                    isTop = true;
                    valueIndex = i;
                    break;
                }
            }

            Date startDate = addSecond(-60,value.get(valueIndex).getTime());
            if (isTop){
                for (int i = 0; i < 120; i++) {
                    inputParam.setNowTime(addSecond(i,startDate));
                    inputParamNext.setNowTime(addSecond(1,startDate));
                    inputParamAfter.setNowTime(addSecond(-1,startDate));
                    Double raeElevation = getRaeParam(inputParam).getElevation();
                    Double raeElevationNext = getRaeParam(inputParamNext).getElevation();
                    Double raeElevationAfter = getRaeParam(inputParamAfter).getElevation();
                    if (raeElevation>raeElevationNext&&
                            raeElevation>raeElevationAfter){
                        break;
                    }
                }
            }else {
                inputParamNext.setNowTime(value.get(value.size()-1).getTime());
                inputParamAfter.setNowTime(value.get(0).getTime());
                if (getRaeParam(inputParamAfter).getElevation()>getRaeParam(inputParamNext).getElevation()){
                    inputParam.setNowTime(threePoint.getStartStationTime());
                }else {
                    inputParam.setNowTime(threePoint.getEndStationTime());
                }
            }
            raeParam = getRaeParam(inputParam);
            threePoint.setTopStationTime(inputParam.getNowTime());
            threePoint.setTopAzimuth(raeParam.getAzimuth());
            threePoint.setTopElevation(raeParam.getElevation());
            threePoint.setTopRange(raeParam.getRange());
            subSatPoint.setLongitude(raeParam.getLongitude());
            subSatPoint.setLatitude(raeParam.getLatitude());
            threePoint.setTopSubSat(subSatPoint);
            threePoints.add(threePoint);
        });
        return threePoints;
    }

    public static InputParam coverent(InputParam inputParam){
        InputParam newInputParam = new InputParam();
        newInputParam.setSiteLongitude(inputParam.getSiteLongitude());
        newInputParam.setSiteLatitude(inputParam.getSiteLatitude());
        newInputParam.setSiteAltitude(inputParam.getSiteAltitude());
        newInputParam.setLine1(inputParam.getLine1());
        newInputParam.setLine2(inputParam.getLine2());
        newInputParam.setSiteName(inputParam.getSiteName());
        newInputParam.setSatelliteName(inputParam.getSatelliteName());
        newInputParam.setStartTime(inputParam.getStartTime());
        newInputParam.setEndTime(inputParam.getEndTime());
        newInputParam.setNowTime(inputParam.getNowTime());
        return newInputParam;
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
        raeParam.setTime(inputParam.getNowTime());
        return raeParam;
    }




}
