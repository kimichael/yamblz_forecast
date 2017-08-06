package com.example.kimichael.yamblz_forecast.domain.interactor.settings;

import android.content.Context;

import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.data.network.places.PlacesRepository;
import com.example.kimichael.yamblz_forecast.data.network.places.response.PlacesResponse;
import com.example.kimichael.yamblz_forecast.data.network.places.response.Prediction;
import com.example.kimichael.yamblz_forecast.domain.interactor.SingleInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInfo;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.PlacesRequest;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SchedulersModule;
import com.example.kimichael.yamblz_forecast.utils.PlaceData;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;
import com.google.android.gms.location.places.Place;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created on 22.07.2017.
 */
public class SettingsInteractor extends SingleInteractor {
    private final PlacesRepository placesRepository;
    private final Context context;

    private final PreferencesManager manager;

    @Inject
    SettingsInteractor(@Named(SchedulersModule.JOB) Scheduler threadExecutor,
                       @Named(SchedulersModule.UI) Scheduler postExecutionThread, PlacesRepository placesRepository,
                       Context context, PreferencesManager manager) {
        super(threadExecutor, postExecutionThread);
        this.placesRepository = placesRepository;
        this.context = context;
        this.manager = manager;
    }

    public void getPlaces(SingleObserver<List<Prediction>> observer, PlacesRequest param) {
        BuildUseCaseObservable<List<Prediction>, PlacesRequest> build = params1 ->
                placesRepository.getPlaces(param)
                        .map(PlacesResponse::getPredictions);
        execute(observer, build, param);
    }

    public void getLocale(SingleObserver<PlaceData> observer, Prediction param) {
        BuildUseCaseObservable<PlaceData, Prediction> build = params1 ->
                placesRepository.getLocale(param.getPlaceId())
                        .map(detail->
                            new PlaceData(detail.getName(),
                                    detail.getResult().getGeometry().getLocation().getLat(),
                                    detail.getResult().getGeometry().getLocation().getLng())
                        );
        execute(observer, build, param);
    }

}
