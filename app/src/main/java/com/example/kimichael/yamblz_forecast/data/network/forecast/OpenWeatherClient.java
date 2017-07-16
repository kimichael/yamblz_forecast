package com.example.kimichael.yamblz_forecast.data.network.forecast;

import com.example.kimichael.yamblz_forecast.data.network.response.Forecast;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kim Michael on 16.07.17
 */
public interface OpenWeatherClient {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    String CITY_ID = "id";

    @GET("weather")
    Single<Forecast> getForecast(@Query(CITY_ID) String cityId);
}
