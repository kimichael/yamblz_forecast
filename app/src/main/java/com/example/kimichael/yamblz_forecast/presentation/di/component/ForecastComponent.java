package com.example.kimichael.yamblz_forecast.presentation.di.component;

import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInteractor;
import com.example.kimichael.yamblz_forecast.domain.interactor.settings.SettingsInteractor;
import com.example.kimichael.yamblz_forecast.domain.service.forecast.ForecastJobService;
import com.example.kimichael.yamblz_forecast.presentation.di.module.ForecastModule;
import com.example.kimichael.yamblz_forecast.presentation.di.module.ForecastScreenModule;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SettingsModule;
import com.example.kimichael.yamblz_forecast.presentation.di.module.SettingsScreenModule;
import com.example.kimichael.yamblz_forecast.presentation.di.scope.ForecastScope;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 16.07.17
 */

@ForecastScope
@Subcomponent(modules = {ForecastModule.class})
public interface ForecastComponent {

    ForecastScreenComponent plus(ForecastScreenModule forecastScreenModule);

    void inject(ForecastInteractor forecastInteractor);
    void inject(SettingsInteractor settingsInteractor);
    void inject(ForecastJobService forecastJobService);
}
