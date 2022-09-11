package CallbackEntry;

import lombok.Data;

@Data
public class RaeParam {

    private Double Range;

    private Double Azimuth;

    private Double Elevation;

    private Double longitude;

    private Double latitude;

    public Double getRange() {
        return Range;
    }

    public void setRange(Double range) {
        Range = range;
    }

    public Double getAzimuth() {
        return Azimuth;
    }

    public void setAzimuth(Double azimuth) {
        Azimuth = azimuth;
    }

    public Double getElevation() {
        return Elevation;
    }

    public void setElevation(Double elevation) {
        Elevation = elevation;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
