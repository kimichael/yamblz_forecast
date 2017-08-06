package com.example.kimichael.yamblz_forecast.domain.interactor.settings;

import android.content.Context;

import com.example.kimichael.yamblz_forecast.data.network.places.PlacesRepository;
import com.example.kimichael.yamblz_forecast.data.network.places.response.PlacesResponse;
import com.example.kimichael.yamblz_forecast.data.network.places.response.Prediction;
import com.example.kimichael.yamblz_forecast.domain.interactor.SingleInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.PlacesRequest;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SchedulersModule;
import com.example.kimichael.yamblz_forecast.utils.PlaceData;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

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

    public Single<List<Prediction>> getPlaces(PlacesRequest param) {
        BuildUseCaseObservable<List<Prediction>, PlacesRequest> build = params1 ->
                placesRepository.getPlaces(param)
                        .map(PlacesResponse::getPredictions);
         return execute(build, param);
    }

    public Single<PlaceData> getLocale(Prediction param) {
        BuildUseCaseObservable<PlaceData, Prediction> build = params1 ->
                placesRepository.getLocale(param.getPlaceId())
                        .map(detail->
                            new PlaceData(detail.getName(),
                                    detail.getResult().getGeometry().getLocation().getLat(),
                                    detail.getResult().getGeometry().getLocation().getLng())
                        );
        return execute(build, param);
    }

}
