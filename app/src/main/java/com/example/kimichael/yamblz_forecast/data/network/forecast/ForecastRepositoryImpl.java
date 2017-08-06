package com.example.kimichael.yamblz_forecast.data.network.forecast;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.kimichael.yamblz_forecast.BuildConfig;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.utils.PlaceData;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Single;
import timber.log.Timber;

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
    public Single<Forecast> getForecast(@NonNull ForecastRequest request) {
        if (sharedPreferences.contains(PREF_LAST_RESPONSE) && !request.isForceUpdate())
            return Single.just(
                    gson.fromJson(sharedPreferences
                                    .getString(PREF_LAST_RESPONSE, null), Forecast.class));
        else
            return openWeatherClient.getForecast(request.getCityLat(), request.getCityLon(), OpenWeatherClient.RUSSIAN, BuildConfig.OPEN_WEATHER_MAP_API_KEY);
    }

    @Override
    public Single<Forecast> updateForecast(PlaceData cityLatLng) {
        String lat = String.valueOf(cityLatLng.getLatitude());
        String lng = String.valueOf(cityLatLng.getLongitude());
        return openWeatherClient.getForecast(lat, lng, OpenWeatherClient.RUSSIAN, BuildConfig.OPEN_WEATHER_MAP_API_KEY);
    }

    @Override
    public void saveForecast(Forecast forecast) {
        sharedPreferences.edit()
                .putString(PREF_LAST_RESPONSE, gson.toJson(forecast))
                .apply();
    }

    @Override
    public void handleException(Throwable throwable) {
        Timber.e(throwable.getMessage());
    }
}
