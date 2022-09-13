package org.example.CallbackEntry;

import lombok.Data;

import java.util.Date;

/**
 * @ClassNameThreePoint
 * @Discription
 * 三点报实体类：
 * 入站出站及最高点的方位俯仰距离及星下点经纬度
 * @Authorxu
 * @Date2022/8/21 22:29
 * @Version1.0
 **/
@Data
public class ThreePoint {

    //弧段号
    private String arcNumber;

    //入站时间
    private Date startStationTime;

    //入站方位角
    private Double startAzimuth;

    //入站俯仰角
    private Double startElevation;

    //入站距离
    private Double startRange;

    //入站星下点经度纬度
    private SubSatPoint startSubSat;

    //最高点时间
    private Date topStationTime;

    //最高点方位角
    private Double topAzimuth;

    //最高点俯仰角
    private Double topElevation;

    //最高点距离
    private Double topRange;

    //最高点星下点经纬度
    private SubSatPoint topSubSat;

    //出站点时间
    private Date endStationTime;

    //出站方位角
    private Double endAzimuth;

    //出站俯仰角
    private Double endElevation;

    //出站距离
    private Double endRange;

    //出站星下点经纬度
    private SubSatPoint endSubSat;
    @Data
    public static class SubSatPoint{
        //星下点经度
        private Double longitude;

        //星下点纬度
        private Double latitude;
    }
}
