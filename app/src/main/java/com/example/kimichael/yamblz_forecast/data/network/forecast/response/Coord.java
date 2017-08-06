
package com.example.kimichael.yamblz_forecast.data.network.forecast.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
class Coord {

    @SerializedName("lon")
    private double lon;
    @SerializedName("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
