package com.example.kimichael.yamblz_forecast.presentation.presenter.settings;

import com.example.kimichael.yamblz_forecast.presentation.BasePresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.settings.SettingsView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Sinjvf on 06.08.2017.
 * dialog for interval choosing
 */

public class SettingsIntervalDialogPresenter extends BasePresenter<SettingsView> {
    public List<String> getIntervals(){
        List<String> list = new ArrayList<>();
        list.add("title");
        list.add("title");
        list.add("value");
        list.add("value");
        return list;
    }

    public void saveLastInterval(int position){
        Timber.d("saveLastInterval: "+position);
    }
}
