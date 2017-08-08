package com.example.kimichael.yamblz_forecast.data.database;

import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;

import java.util.List;

/**
 * Created by Sinjvf on 08.08.2017.
 * interface for db working
 */

interface DbClient {

    void saveWeather(ForecastInfo forecast);
    void saveForecast(List<ForecastInfo> forecast);
    void deleteCity(PlaceData data);
    void addCity(PlaceData data);
    List<PlaceData> getAllCities();
    void delOldForecasts(ForecastInfo forecast, long timeInterval);
}
