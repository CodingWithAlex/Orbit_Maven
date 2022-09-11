package CallbackEntry;

import java.util.Date;

public class InputParam {

    //    String name = "NOOR-2";
    //    String line1 = "1 51954U 22024A   22095.15428803  .00012839  00000+0  58480-3 0  9993";
    //    String line2 = "2 51954  58.2896  32.5975 0013833 295.0466 199.1831 15.20983858  4246";

    private Date nowTime;

    private Date startTime;

    private Date endTime;

    private String satelliteName;

    private String line1;

    private String line2;

    private String siteName;

    private Double siteLongitude;

    private Double siteLatitude;

    private Double siteAltitude;

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSatelliteName() {
        return satelliteName;
    }

    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Double getSiteLongitude() {
        return siteLongitude;
    }

    public void setSiteLongitude(Double siteLongitude) {
        this.siteLongitude = siteLongitude;
    }

    public Double getSiteLatitude() {
        return siteLatitude;
    }

    public void setSiteLatitude(Double siteLatitude) {
        this.siteLatitude = siteLatitude;
    }

    public Double getSiteAltitude() {
        return siteAltitude;
    }

    public void setSiteAltitude(Double siteAltitude) {
        this.siteAltitude = siteAltitude;
    }
}
