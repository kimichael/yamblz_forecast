package com.example.kimichael.yamblz_forecast.data;

import com.example.kimichael.yamblz_forecast.data.network.response.Forecast;

import io.reactivex.Observable;

/**
 * Created by Kim Michael on 16.07.17
 */
public interface ForecastRepository {

    Observable<Forecast> getForecast(String cityId);
    void saveForecast(Forecast forecast);
}
