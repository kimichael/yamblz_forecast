package com.example.kimichael.yamblz_forecast.data.network.forecast;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.kimichael.yamblz_forecast.data.network.forecast.response.ForecastResponse;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.WeatherResponse;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInfo;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.utils.PlaceData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    public Single<ForecastInfo> getWeather(@NonNull ForecastRequest request) {

            return openWeatherClient.getWeather(request.getCityLat(), request.getCityLon(),
                    Locale.getDefault().getLanguage().toLowerCase())
                    .map(ForecastInfo::from);
    }

    @Override
    public Single<ForecastInfo> updateWeather(PlaceData cityLatLng) {
        String lat = String.valueOf(cityLatLng.getLatitude());
        String lng = String.valueOf(cityLatLng.getLongitude());
        return openWeatherClient.getWeather(lat, lng, Locale.getDefault().getLanguage().toLowerCase())
                .map(ForecastInfo::from);
    }

    @Override
    public Single<List<ForecastInfo>> getForecast(ForecastRequest request) {
        return openWeatherClient.getForecast(request.getCityLat(), request.getCityLon(),
                Locale.getDefault().getLanguage().toLowerCase())
                .map(ForecastResponse::getList)
                .map(list->{
                    List<ForecastInfo> resultList = new ArrayList<>();
                    for (WeatherResponse item:list){
                        resultList.add(ForecastInfo.from(item));
                    }
                    return resultList;
                });
    }

    @Override
    public void saveWeather(ForecastInfo forecast) {
        sharedPreferences.edit()
                .putString(PREF_LAST_RESPONSE, gson.toJson(forecast))
                .apply();
    }

    @Override
    public void saveForecast(List<ForecastInfo> forecast) {

    }

    @Override
    public void handleException(Throwable throwable) {
        Timber.e(throwable.getMessage());
    }
}
