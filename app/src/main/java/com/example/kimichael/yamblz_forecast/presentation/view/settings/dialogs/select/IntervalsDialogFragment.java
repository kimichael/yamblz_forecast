package com.example.kimichael.yamblz_forecast.presentation.view.settings.dialogs.select;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimichael.yamblz_forecast.App;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SettingsIntervalDialogPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sinjvf on 05.08.2017.
 *
 * dialog of interval choosing
 */

public class IntervalsDialogFragment extends SelectorDialogFragment implements SelectableDialogView {

    @Inject
    SettingsIntervalDialogPresenter presenter;


    public static SelectorDialogFragment getInstance(int position) {
        IntervalsDialogFragment fgm = new IntervalsDialogFragment();
        fgm.setArguments(getBundle(position));
        return fgm;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        App.getInstance().getSettingsScreenComponent().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public List<String> getList() {
        return presenter.getIntervals();
    }

    @Override
    public void savePosition(int position) {
        presenter.saveLastInterval(position);
    }

    @Override
    public void checkPosition(int position) {

    }
}
