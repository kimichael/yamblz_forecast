package com.example.kimichael.yamblz_forecast.domain.interactor.forecast;

/**
 * Created by Kim Michael on 18.07.17
 */
public class ForecastRequest {

    private final String cityId;
    private final boolean forceUpdate;

    public ForecastRequest(String cityId, boolean forceUpdate) {
        this.cityId = cityId;
        this.forceUpdate = forceUpdate;
    }

    public String getCityId() {
        return cityId;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }
}
