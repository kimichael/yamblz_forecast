package com.example.kimichael.yamblz_forecast.presentation.view.forecast;

import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInfo;
import com.example.kimichael.yamblz_forecast.presentation.BaseView;

/**
 * Created by Kim Michael on 16.07.17
 */
public interface ForecastView extends BaseView {

    void showForecast(ForecastInfo forecast);

    void showError();
}
