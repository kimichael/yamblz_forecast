package com.example.kimichael.yamblz_forecast.presentation.presenter.forecast;

import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.data.network.places.response.Prediction;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.PlacesRequest;
import com.example.kimichael.yamblz_forecast.domain.interactor.settings.SettingsInteractor;
import com.example.kimichael.yamblz_forecast.presentation.presenter.BasePresenter;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SuggestsPresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.main.phone.PhoneWeatherView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Sinjvf on 08.08.2017.
 * presenter for main screen
 */

public class PhoneWeatherPresenter extends BasePresenter<PhoneWeatherView> {

    private SettingsInteractor interactor;


    @Inject
    public PhoneWeatherPresenter(SettingsInteractor forecastInteractor) {
        this.interactor = forecastInteractor;
    }

    public List<PlaceData> getCities(){
        return interactor.getAllCities();
    }



}
