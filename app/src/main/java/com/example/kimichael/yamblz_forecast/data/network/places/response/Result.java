package com.example.kimichael.yamblz_forecast.data.network.places.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sinjvf on 06.08.2017.
 * data class
 */

public class Result {
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
