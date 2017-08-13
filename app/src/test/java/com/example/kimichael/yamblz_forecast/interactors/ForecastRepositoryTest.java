package com.example.kimichael.yamblz_forecast.interactors;

import android.content.Context;

import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.data.database.DbClientImpl;
import com.example.kimichael.yamblz_forecast.data.network.forecast.ForecastRepository;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.ForecastResponse;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInteractor;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.ForecastPresenter;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

/**
 * Created by Sinjvf on 30.07.2017.
 * check invocation of repository methods when interactor call
 */

@RunWith(MockitoJUnitRunner.class)
public class ForecastRepositoryTest {
    @Mock
    private ForecastRepository repository;
    @Mock
    private Scheduler threadExecutor;
    @Mock
    private Scheduler postExecutionThread;
    @Mock
    private Context context;
    @Mock
    private PreferencesManager manager;
    @Mock
    private DbClientImpl client;

    private ForecastInteractor interactor;
    private ForecastPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        interactor = new ForecastInteractor(threadExecutor, postExecutionThread, repository, context, client, manager);
        presenter = new ForecastPresenter(interactor);

        Mockito.when(repository.getForecast(anyObject())).thenReturn(new Single<List<ForecastInfo>>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super List<ForecastInfo>> observer) {

            }
        });
    }


    @Test
    public void checkRepositoryGetForecast() {
        interactor.getForecast(presenter.getRequest(true), new SingleObserver<List<ForecastInfo>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<ForecastInfo> forecastInfos) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        verify(repository).getForecast(anyObject());
    }


}
