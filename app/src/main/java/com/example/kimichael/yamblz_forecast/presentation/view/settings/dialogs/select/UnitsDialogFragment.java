package com.example.kimichael.yamblz_forecast.presentation.view.settings.dialogs.select;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimichael.yamblz_forecast.App;
import com.example.kimichael.yamblz_forecast.presentation.presenter.settings.SettingsUnitDialogPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sinjvf on 05.08.2017.
 * dialog of temp unit choosing
 *
 */

public class UnitsDialogFragment extends SelectorDialogFragment {

    @Inject
    SettingsUnitDialogPresenter presenter;

    public static SelectorDialogFragment getInstance(int position) {
        UnitsDialogFragment fgm = new UnitsDialogFragment();
        fgm.setArguments(getBundle(position));
        return fgm;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        App.getInstance().getSettingsScreenComponent().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public List<String> getList() {
        return presenter.getUnits();
    }

    @Override
    public void savePosition(int position) {
        presenter.saveLastUnit(position);
    }
}
