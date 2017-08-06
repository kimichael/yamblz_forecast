package com.example.kimichael.yamblz_forecast.presentation.presenter.forecast;

import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.requests.ForecastRequest;
import com.example.kimichael.yamblz_forecast.presentation.BasePresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.ForecastView;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastPresenter extends BasePresenter<ForecastView> {

    private ForecastInteractor forecastInteractor;
    @Inject
    PreferencesManager manager;

    @Inject
    public ForecastPresenter(ForecastInteractor forecastInteractor) {
        this.forecastInteractor = forecastInteractor;
    }

    public void getWeather(boolean forceUpdate) {
        forecastInteractor.getWeather(getRequest(forceUpdate))
                .subscribe(getWeatherObserver());
    }

    public void getForecast(boolean forceUpdate) {
        forecastInteractor.getForecast(getRequest(forceUpdate))
                .subscribe(getForecastObserver());
    }

    public SingleObserver<ForecastInfo> getWeatherObserver() {
        return new SingleObserver<ForecastInfo>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull ForecastInfo forecastInfo) {
                if (getView() != null) getView().showCurrentWeather(forecastInfo);
                forecastInteractor.saveWeather(forecastInfo);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Timber.e(e);
                if (getView() != null) getView().showError(e);
            }
        };
    }


    public SingleObserver<List<ForecastInfo>> getForecastObserver() {
        return new SingleObserver<List<ForecastInfo>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<ForecastInfo> forecastInfo) {
                if (getView() != null) getView().showForecast(forecastInfo);
                forecastInteractor.saveForecast(forecastInfo);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Timber.e(e);
                if (getView() != null) getView().showError(e);
            }
        };
    }

    public ForecastRequest getRequest(boolean update) {
        if (getView() == null) return null;
        return new ForecastRequest(getView().getPlace(), update);
    }
}
