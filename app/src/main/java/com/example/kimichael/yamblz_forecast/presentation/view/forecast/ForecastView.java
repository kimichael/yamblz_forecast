package com.example.kimichael.yamblz_forecast.presentation.view.forecast;

import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInfo;
import com.example.kimichael.yamblz_forecast.presentation.BaseView;
import com.example.kimichael.yamblz_forecast.utils.PlaceData;

import java.util.List;

/**
 * Created by Kim Michael on 16.07.17
 */
public interface ForecastView extends BaseView {

    void showCurrentWeather(ForecastInfo forecast);
    void showForecast(List<ForecastInfo> forecastsList);
    PlaceData getPlace();

    void showError(Throwable e);
}
