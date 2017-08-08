package com.example.kimichael.yamblz_forecast.presentation.view.main.phone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimichael.yamblz_forecast.App;
import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.PhoneWeatherPresenter;
import com.example.kimichael.yamblz_forecast.presentation.view.ToolbarOwner;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.BaseForecastFragment;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.ForecastFragment;
import com.example.kimichael.yamblz_forecast.presentation.view.places.SuggestsFragment;
import com.jakewharton.rxbinding2.support.v4.view.RxViewPager;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sinjvf on 08.08.2017.
 * main screen fragment
 */

public class PhoneWeatherFragment extends Fragment implements PhoneWeatherView {
    @BindView(R.id.viewPager)
    ViewPager pager;
    @Inject
    PhoneWeatherPresenter presenter;

    public static PhoneWeatherFragment newInstance() {
        return new PhoneWeatherFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getSettingsScreenComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phone_weather, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateCitiesList();
    }

    public void updateCitiesList() {
        List<PlaceData> dataList = presenter.getCities();
        List<BaseForecastFragment> fragments = new ArrayList<>();
        BaseForecastFragment fgm;
        for (PlaceData data : dataList) {
            fgm = ForecastFragment.newInstance(data);
            fragments.add(fgm);
        }
        fragments.add(SuggestsFragment.getInstance());
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), fragments);
        pager.setAdapter(adapter);
        //show city name on the toolbar
        RxViewPager.pageSelections(pager).map(i ->
                (i < dataList.size())
                        ? dataList.get(i).getName()
                        : getString(R.string.new_city))
                .subscribe(this::changeTitle);
    }

    private void changeTitle(String title) {
        ((ToolbarOwner) getActivity()).setToolbarText(title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void showCityByPosition(int pos) {
        pager.setCurrentItem(pos);
    }

    @Override
    public void addCity() {

    }
}
