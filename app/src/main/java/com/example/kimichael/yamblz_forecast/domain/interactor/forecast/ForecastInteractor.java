package com.example.kimichael.yamblz_forecast.domain.interactor.forecast;

import android.content.Context;

import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.data.database.CitiesTable;
import com.example.kimichael.yamblz_forecast.data.database.DbClientImpl;
import com.example.kimichael.yamblz_forecast.data.network.forecast.ForecastRepository;
import com.example.kimichael.yamblz_forecast.domain.interactor.SingleInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SchedulersModule;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastInteractor extends SingleInteractor {

    private final ForecastRepository forecastRepository;
    private final Context context;
    private final DbClientImpl dbClient;


    @Inject
    public ForecastInteractor(@Named(SchedulersModule.JOB) Scheduler threadExecutor,
                       @Named(SchedulersModule.UI) Scheduler postExecutionThread, ForecastRepository forecastRepository,
                       Context context, DbClientImpl dbClient) {
        super(threadExecutor, postExecutionThread);
        this.forecastRepository = forecastRepository;
        this.context = context;
        this.dbClient = dbClient;
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
        data.setDate(Calendar.getInstance().getTime().getTime());
        dbClient.saveWeather(data);
    }

    public void saveForecast(List<ForecastInfo> data){
        dbClient.saveForecast(data);
    }




}
