package com.example.kimichael.yamblz_forecast.presentation.presenter.settings;

import com.example.kimichael.yamblz_forecast.data.network.places.response.Prediction;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.PlacesRequest;
import com.example.kimichael.yamblz_forecast.domain.interactor.settings.SettingsInteractor;
import com.example.kimichael.yamblz_forecast.presentation.BasePresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.places.SuggestsView;
import com.example.kimichael.yamblz_forecast.utils.PlaceData;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created on 22.07.2017.
 */
public class SuggestsPresenter extends BasePresenter<SuggestsView> {

    private SettingsInteractor settingsInteractor;
    private PreferencesManager manager;

    public SuggestsPresenter(SettingsInteractor settingsInteractor, PreferencesManager manager) {
        this.settingsInteractor = settingsInteractor;
        this.manager = manager;
    }

    @Inject

    public void getCitiesIds(String queue){
        settingsInteractor.getPlaces(getTextChangeObserver(), new PlacesRequest(queue));
    }

    private SingleObserver<List<Prediction>> getTextChangeObserver(){
        return new SingleObserver<List<Prediction>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Prediction> placesResponse) {
                Timber.d("onNext: "+placesResponse.toString());
                if (getView()!=null) getView().addList(placesResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Timber.e(e.getMessage());
            }

        };
    }
    public void saveCity(Prediction prediction){
        settingsInteractor.getLocale(getPlaceDetailObserver(), prediction);
    }

    private SingleObserver<PlaceData> getPlaceDetailObserver(){
        return new SingleObserver<PlaceData>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull PlaceData placesResponse) {
                Timber.d("onNext: "+placesResponse.toString());
                manager.savePlace(placesResponse);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Timber.e(e.getMessage());
            }

        };
    }
}
