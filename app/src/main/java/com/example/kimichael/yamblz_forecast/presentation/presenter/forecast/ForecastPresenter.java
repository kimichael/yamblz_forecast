package com.example.kimichael.yamblz_forecast.presentation.presenter.forecast;

import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInfo;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastRequest;
import com.example.kimichael.yamblz_forecast.presentation.BasePresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.ForecastView;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastPresenter extends BasePresenter<ForecastView> {

    private ForecastInteractor forecastInteractor;
    private ForecastInfo cachedForecast;
    // Temporary Moscow city id
    public static final String MOSCOW_ID = "524894";

    @Inject
    public ForecastPresenter(ForecastInteractor forecastInteractor) {
        this.forecastInteractor = forecastInteractor;
    }

    public void getForecast(boolean forceUpdate) {
        if (cachedForecast != null && !forceUpdate) {
            getView().showForecast(cachedForecast);
            return;
        }
        forecastInteractor.execute(new DisposableObserver<ForecastInfo>() {
            @Override
            public void onNext(@NonNull ForecastInfo forecast) {
                cachedForecast = forecast;
                getView().showForecast(forecast);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showError();
            }

            @Override
            public void onComplete() {

            }
        }, new ForecastRequest(MOSCOW_ID, forceUpdate));
    }

}
