package com.example.kimichael.yamblz_forecast.repository;

import android.content.SharedPreferences;

import com.example.kimichael.yamblz_forecast.data.network.forecast.ForecastRepositoryImpl;
import com.example.kimichael.yamblz_forecast.data.network.forecast.OpenWeatherClient;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by Sinjvf on 30.07.2017.
 * check invocation of openWeatherClient methods when repository call
 */

//@RunWith(MockitoJUnitRunner.class)
public class ForecastRepositoryImplTest {
/*    @Mock
    private SharedPreferences sharedPreferences;
    @Mock
    private OpenWeatherClient openWeatherClient;


    private ForecastRepositoryImpl impl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Gson gson = new Gson();
        impl = new ForecastRepositoryImpl(openWeatherClient, gson);
    }

    @Test
    public void checkClientGetForecast() {
        impl.getForecast(new ForecastRequest(new PlaceData(3, "name", 1.0, 1.0), true));
        verify(openWeatherClient).getForecast(anyString(), anyString(), anyString());
    }

    @Test
    public void checkClientUpdateForecast() {
        impl.updateForecast(new PlaceData(3, "name", 1.0, 1.0));
        verify(openWeatherClient).getWeather(anyString(), anyString(), anyString());
    }*/

}
