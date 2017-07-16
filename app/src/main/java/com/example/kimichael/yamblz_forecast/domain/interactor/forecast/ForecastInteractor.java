package com.example.kimichael.yamblz_forecast.domain.interactor.forecast;

import com.example.kimichael.yamblz_forecast.data.network.forecast.OpenWeatherClient;
import com.example.kimichael.yamblz_forecast.data.network.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.Interactor;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SchedulersModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastInteractor extends Interactor<Forecast, String> {

    private final OpenWeatherClient openWeatherClient;

    @Inject
    ForecastInteractor(@Named(SchedulersModule.JOB) Scheduler threadExecutor,
                       @Named(SchedulersModule.UI) Scheduler postExecutionThread, OpenWeatherClient openWeatherClient) {
        super(threadExecutor, postExecutionThread);
        this.openWeatherClient = openWeatherClient;
    }

    @Override
    protected Observable<Forecast> buildUseCaseObservable(String cityId) {
        return openWeatherClient.getForecast(cityId).toObservable();
    }


}
