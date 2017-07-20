package com.example.kimichael.yamblz_forecast.data;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.kimichael.yamblz_forecast.data.network.forecast.OpenWeatherClient;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastRequest;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastRepositoryImpl implements ForecastRepository {

    private static final String PREF_LAST_RESPONSE = "pref_last_response";

    private SharedPreferences sharedPreferences;
    private OpenWeatherClient openWeatherClient;
    private Gson gson;

    @Inject
    public ForecastRepositoryImpl(SharedPreferences sharedPreferences,
                                  OpenWeatherClient openWeatherClient,
                                  Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.openWeatherClient = openWeatherClient;
        this.gson = gson;
    }

    @Override
    public Observable<Forecast> getForecast(@NonNull ForecastRequest request) {
        if (sharedPreferences.contains(PREF_LAST_RESPONSE) && !request.isForceUpdate())
            return Observable.just(
                    gson.fromJson(sharedPreferences
                                    .getString(PREF_LAST_RESPONSE, null), Forecast.class));
        else
            return openWeatherClient.getForecast(request.getCityId(), OpenWeatherClient.RUSSIAN).toObservable();
    }

    @Override
    public Observable<Forecast> updateForecast(String cityId) {
        return openWeatherClient.getForecast(cityId, OpenWeatherClient.RUSSIAN).toObservable();
    }

    @Override
    public void saveForecast(Forecast forecast) {
        sharedPreferences.edit()
                .putString(PREF_LAST_RESPONSE, gson.toJson(forecast))
                .apply();
    }
}
