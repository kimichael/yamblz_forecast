package com.example.kimichael.yamblz_forecast;

import android.app.Application;

import com.example.kimichael.yamblz_forecast.presentation.di.component.AppComponent;
import com.example.kimichael.yamblz_forecast.presentation.di.component.DaggerAppComponent;
import com.example.kimichael.yamblz_forecast.presentation.di.module.AppModule;

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
