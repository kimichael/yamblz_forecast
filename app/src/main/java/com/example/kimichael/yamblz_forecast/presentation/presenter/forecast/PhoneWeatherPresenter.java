package com.example.kimichael.yamblz_forecast.presentation.presenter.forecast;

import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.domain.interactor.settings.SettingsInteractor;
import com.example.kimichael.yamblz_forecast.presentation.presenter.BasePresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.main.phone.MainWeatherView;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;


/**
 * Created by Sinjvf on 08.08.2017.
 * presenter for main screen
 */

public class PhoneWeatherPresenter<T extends MainWeatherView>  extends BasePresenter<T> {

    private SettingsInteractor interactor;
    private int currentCityPos = 0;
    protected List<PlaceData> currentList;


    @Inject
    public PhoneWeatherPresenter(SettingsInteractor forecastInteractor) {
        this.interactor = forecastInteractor;
    }

    public void getCities() {
        PublishSubject<List<PlaceData>> subject = PublishSubject.create();
        subject.observeOn(AndroidSchedulers.mainThread())
                .subscribe(citiesObservable);
        interactor.getAllCities(subject);
    }

    public int setItemPos(int currentCityPos) {
        this.currentCityPos = currentCityPos;
        return currentCityPos;
    }

    private Observer<List<PlaceData>> citiesObservable = new Observer<List<PlaceData>>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull List<PlaceData> placeData) {
            if (getView() != null) getView().updateCitiesList(placeData, currentCityPos);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    };

    public void setCurrentList(List<PlaceData> currentList) {
        this.currentList = currentList;
    }
}
