package com.example.kimichael.yamblz_forecast.presentation.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kim Michael on 16.07.17
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(@NonNull Context appContext) {
        this.context = appContext;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

}
