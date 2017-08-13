package com.example.kimichael.yamblz_forecast.presenters;

import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.data.network.places.response.Prediction;
import com.example.kimichael.yamblz_forecast.domain.interactor.settings.SettingsInteractor;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.PhoneWeatherPresenter;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SuggestsPresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.main.phone.MainWeatherView;
import com.example.kimichael.yamblz_forecast.presentation.view.places.SuggestsFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Sinjvf on 13.08.2017.
 * check invocation of interactor methods when presenter call
 */

@RunWith(MockitoJUnitRunner.class)
public class PhoneWeatherInteractorTest {
    @Mock
    private SuggestsFragment view;

    private PhoneWeatherPresenter<MainWeatherView> presenter;
    private SettingsInteractor interactor;
    private Scheduler scheduler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.interactor = Mockito.mock(SettingsInteractor.class);
        this.scheduler = Mockito.mock(Scheduler.class);
        presenter = new PhoneWeatherPresenter<MainWeatherView>(interactor,scheduler);

    }

    @Test
    public void checkGetCities() {
        presenter.getCities();
        verify(interactor).getAllCities(any());
    }

}
