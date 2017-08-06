package com.example.kimichael.yamblz_forecast.presentation.di.module;

import com.example.kimichael.yamblz_forecast.domain.interactor.settings.SettingsInteractor;
import com.example.kimichael.yamblz_forecast.presentation.di.scope.SettingsScreenScope;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SettingsIntervalDialogPresenter;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SettingsPresenter;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SettingsUnitDialogPresenter;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SuggestsPresenter;
import com.example.kimichael.yamblz_forecast.utils.PreferencesManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 22.07.2017.
 */
@Module
public class SettingsScreenModule {

    @Provides
    @SettingsScreenScope
    SettingsPresenter provideSettingsPresenter(SettingsInteractor settingsInteractor) {
        return new SettingsPresenter(settingsInteractor);
    }

    @Provides
    @SettingsScreenScope
    SuggestsPresenter provideSuggestsPresenter(SettingsInteractor settingsInteractor, PreferencesManager manager) {
        return new SuggestsPresenter(settingsInteractor, manager);
    }

    @Provides
    @SettingsScreenScope
    SettingsIntervalDialogPresenter provideSettingsIntervalPresenter() {
        return new SettingsIntervalDialogPresenter();
    }

    @Provides
    @SettingsScreenScope
    SettingsUnitDialogPresenter provideSettingsUnitPresenter() {
        return new SettingsUnitDialogPresenter();
    }
}
