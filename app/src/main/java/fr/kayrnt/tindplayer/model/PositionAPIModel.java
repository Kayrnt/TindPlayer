package fr.kayrnt.tindplayer.model;

import com.google.gson.annotations.Expose;

/**
 * Created by Kayrnt on 17/01/15.
 */
public class PositionAPIModel {

    @Expose
    private double lat;
    @Expose
    private double lon;


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
