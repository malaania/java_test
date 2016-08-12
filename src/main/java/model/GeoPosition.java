package model;

/**
 * Represents geographic position of the city.
 */
public class GeoPosition {

    private double latitude;
    private double longitude;

    public GeoPosition() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
