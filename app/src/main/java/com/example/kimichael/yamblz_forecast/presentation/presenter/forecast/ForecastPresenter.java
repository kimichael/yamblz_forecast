package com.example.kimichael.yamblz_forecast.presentation.presenter.forecast;

import com.example.kimichael.yamblz_forecast.data.network.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInteractor;
import com.example.kimichael.yamblz_forecast.presentation.BasePresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.ForecastView;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Kim Michael on 16.07.17
 */
public class ForecastPresenter extends BasePresenter<ForecastView> {

    private ForecastInteractor forecastInteractor;
    // Temporary Moscow city id
    public static final String MOSCOW_ID = "524894";

    public ForecastPresenter(ForecastInteractor forecastInteractor) {
        this.forecastInteractor = forecastInteractor;
    }

    public void getForecast() {
        forecastInteractor.execute(new DisposableObserver<Forecast>() {
            @Override
            public void onNext(@NonNull Forecast forecast) {
                getView().showForecast(forecast);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showError();
            }

            @Override
            public void onComplete() {

            }
        }, MOSCOW_ID);
    }

}
