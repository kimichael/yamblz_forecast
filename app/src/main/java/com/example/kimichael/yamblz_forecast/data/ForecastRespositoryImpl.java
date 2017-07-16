package com.example.kimichael.yamblz_forecast.data;

import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.example.kimichael.yamblz_forecast.data.network.forecast.OpenWeatherClient;
import com.example.kimichael.yamblz_forecast.data.network.response.Forecast;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastRespositoryImpl implements ForecastRepository {

    private static final String PREF_LAST_RESPONSE = "pref_last_response";

    SharedPreferences sharedPreferences;
    OpenWeatherClient openWeatherClient;
    Gson gson;

    @Inject
    public ForecastRespositoryImpl(SharedPreferences sharedPreferences,
                                   OpenWeatherClient openWeatherClient,
                                   Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.openWeatherClient = openWeatherClient;
        this.gson = gson;
    }

    @Override
    public Observable<Forecast> getForecast(String cityId) {
        if (sharedPreferences.contains(PREF_LAST_RESPONSE))
            return Observable.just(
                    gson.fromJson(sharedPreferences
                                    .getString(PREF_LAST_RESPONSE, null), Forecast.class));
        else
            return openWeatherClient.getForecast(cityId).toObservable();
    }

    @Override
    public void saveForecast(Forecast forecast) {
        sharedPreferences.edit()
                .putString(PREF_LAST_RESPONSE, gson.toJson(forecast))
                .apply();
    }
}
