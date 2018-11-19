package main.java.Util;

/**
 * Created by Jp on 19/11/2018.
 */
public class JsonEvent {

    String eventDescription;
    String locationDescription;
    double longitude;
    double latitude;
    long timeMsEventDate;
    String initialState;

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTimeMsEventDate() {
        return timeMsEventDate;
    }

    public void setTimeMsEventDate(long timeMsEventDate) {
        this.timeMsEventDate = timeMsEventDate;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }
}
