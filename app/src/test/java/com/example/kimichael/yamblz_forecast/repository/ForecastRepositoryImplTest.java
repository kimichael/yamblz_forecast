package com.example.kimichael.yamblz_forecast.repository;

import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.data.network.forecast.ForecastRepositoryImpl;
import com.example.kimichael.yamblz_forecast.data.network.forecast.OpenWeatherClient;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.ForecastResponse;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.ForecastPresenter;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by Sinjvf on 13.08.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ForecastRepositoryImplTest {
    @Mock
    private OpenWeatherClient openWeatherClient;
    private ForecastRepositoryImpl repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Gson gson = new Gson();
        repository = new ForecastRepositoryImpl(openWeatherClient, gson);
    }

    @Test
    public void updateForecast() {
        ForecastResponse resp = new ForecastResponse();
        Mockito.when(openWeatherClient.getForecast(any(), any(), anyString()))
                .thenReturn(Single.just(resp));
        repository.updateForecast(new PlaceData(4, "", 5, 6));
        verify(openWeatherClient).getForecast(any(), any(), any());
    }

    @Test
    public void getForecast() {
        ForecastResponse resp = new ForecastResponse();
        Mockito.when(openWeatherClient.getForecast(any(), any(), anyString()))
                .thenReturn(Single.just(resp));
        repository.getForecast(new ForecastRequest(new PlaceData(4, "", 5, 6), false));
        verify(openWeatherClient).getForecast(any(), any(), any());
    }
}
