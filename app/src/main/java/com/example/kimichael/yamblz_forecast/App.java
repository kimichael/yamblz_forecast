package com.example.kimichael.yamblz_forecast;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.example.kimichael.yamblz_forecast.domain.service.forecast.ForecastJobService;
import com.example.kimichael.yamblz_forecast.presentation.di.component.AppComponent;
import com.example.kimichael.yamblz_forecast.presentation.di.component.DaggerAppComponent;
import com.example.kimichael.yamblz_forecast.presentation.di.module.AppModule;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by Kim Michael on 16.07.17
 */
public class App extends Application {

    private static App instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    private static void setInstance(App instance) {
        App.instance = instance;
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
