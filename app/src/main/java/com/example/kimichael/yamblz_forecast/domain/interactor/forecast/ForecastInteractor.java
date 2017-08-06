package com.example.kimichael.yamblz_forecast.domain.interactor.forecast;

import android.content.Context;

import com.example.kimichael.yamblz_forecast.data.network.forecast.ForecastRepository;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.SingleInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SchedulersModule;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;

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

    private final PreferencesManager manager;

    @Inject
    public ForecastInteractor(@Named(SchedulersModule.JOB) Scheduler threadExecutor,
                       @Named(SchedulersModule.UI) Scheduler postExecutionThread, ForecastRepository forecastRepository,
                       Context context, PreferencesManager manager) {
        super(threadExecutor, postExecutionThread);
        this.forecastRepository = forecastRepository;
        this.context = context;
        this.manager = manager;
    }

    public void getForecast(SingleObserver<ForecastInfo> observer,
                                               ForecastRequest params) {
        BuildUseCaseObservable<ForecastInfo, ForecastRequest> build = params1 -> {
            Single<Forecast> response = forecastRepository.getForecast(params1);
            response.subscribe(forecastRepository::saveForecast, forecastRepository::handleException);
            return response.map(ForecastInfo::from);
        };
        execute(observer, build, params);
    }

    public ForecastRequest getRequest(boolean update){
        return new ForecastRequest(manager.getPlace(), update);
    }


}
