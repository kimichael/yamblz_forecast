package com.example.kimichael.yamblz_forecast.data.network.forecast;

import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.utils.PlaceData;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 16.07.17
 */
public interface ForecastRepository {

    Single<Forecast> getForecast(ForecastRequest request);
    Single<Forecast> updateForecast(PlaceData cityLatLng);
    void saveForecast(Forecast forecast);
    void handleException(Throwable throwable);
}
