package com.example.kimichael.yamblz_forecast.domain.interactor.forecast;

import com.example.kimichael.yamblz_forecast.data.ForecastRepository;
import com.example.kimichael.yamblz_forecast.data.network.forecast.OpenWeatherClient;
import com.example.kimichael.yamblz_forecast.data.network.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.Interactor;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SchedulersModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastInteractor extends Interactor<Forecast, String> {

    private final ForecastRepository forecastRepository;

    @Inject
    ForecastInteractor(@Named(SchedulersModule.JOB) Scheduler threadExecutor,
                       @Named(SchedulersModule.UI) Scheduler postExecutionThread, ForecastRepository forecastRepository) {
        super(threadExecutor, postExecutionThread);
        this.forecastRepository = forecastRepository;
    }

    @Override
    protected Observable<Forecast> buildUseCaseObservable(String cityId) {
        Observable<Forecast> response = forecastRepository.getForecast(cityId);
        response.subscribe(new Consumer<Forecast>() {
            @Override
            public void accept(@NonNull Forecast forecast) throws Exception {
                forecastRepository.saveForecast(forecast);
            }
        });
        return response;
    }


}
