package com.example.kimichael.yamblz_forecast.domain.interactor.forecast;

import android.content.Context;

import com.example.kimichael.yamblz_forecast.data.network.forecast.ForecastRepository;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.ForecastResponse;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.WeatherResponse;
import com.example.kimichael.yamblz_forecast.domain.interactor.SingleInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SchedulersModule;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastInteractor extends SingleInteractor {

    private final ForecastRepository forecastRepository;
    private final Context context;

    @Inject
    public ForecastInteractor(@Named(SchedulersModule.JOB) Scheduler threadExecutor,
                       @Named(SchedulersModule.UI) Scheduler postExecutionThread, ForecastRepository forecastRepository,
                       Context context, PreferencesManager manager) {
        super(threadExecutor, postExecutionThread);
        this.forecastRepository = forecastRepository;
        this.context = context;
    }

    public Single<ForecastInfo>  getWeather(
                                ForecastRequest params) {
        BuildUseCaseObservable<ForecastInfo, ForecastRequest> build = forecastRepository::getWeather;
        return execute(build, params);
    }

    public Single<List<ForecastInfo>> getForecast(ForecastRequest params) {
        BuildUseCaseObservable<List<ForecastInfo>, ForecastRequest> build = forecastRepository::getForecast;
        return execute( build, params);
    }

    public void saveWeather(ForecastInfo data){
        forecastRepository.saveWeather(data);
    }

    public void saveForecast(List<ForecastInfo> data){
        forecastRepository.saveForecast(data);
    }



}
