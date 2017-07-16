package com.example.kimichael.yamblz_forecast.presentation.view.forecast;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kimichael.yamblz_forecast.App;
import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.data.network.response.Forecast;
import com.example.kimichael.yamblz_forecast.presentation.di.module.ForecastModule;
import com.example.kimichael.yamblz_forecast.presentation.di.module.ForecastScreenModule;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.ForecastPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastFragment extends Fragment implements ForecastView {

    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.min_temp)
    TextView minTemp;
    @BindView(R.id.max_temp)
    TextView maxTemp;

    @Inject
    ForecastPresenter forecastPresenter;

    public ForecastFragment() {}

    public static ForecastFragment newInstance() {
        ForecastFragment fragment = new ForecastFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().getAppComponent()
                .plus(new ForecastModule()).plus(new ForecastScreenModule()).inject(this);

        forecastPresenter.onAttach(this);

        if (savedInstanceState == null) {
            forecastPresenter.getForecast();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void showForecast(Forecast forecast) {
        cityName.setText(forecast.getName());
        minTemp.setText(forecast.getTemp().getTempMin().toString());
        maxTemp.setText(forecast.getTemp().getTempMax().toString());
    }

    @Override
    public void showError() {

    }
}
