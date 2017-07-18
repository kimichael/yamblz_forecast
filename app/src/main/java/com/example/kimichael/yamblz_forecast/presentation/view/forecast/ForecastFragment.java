package com.example.kimichael.yamblz_forecast.presentation.view.forecast;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimichael.yamblz_forecast.App;
import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.data.network.forecast.response.Forecast;
import com.example.kimichael.yamblz_forecast.domain.interactor.forecast.ForecastInfo;
import com.example.kimichael.yamblz_forecast.presentation.di.module.ForecastModule;
import com.example.kimichael.yamblz_forecast.presentation.di.module.ForecastScreenModule;
import com.example.kimichael.yamblz_forecast.presentation.presenter.forecast.ForecastPresenter;
import com.example.kimichael.yamblz_forecast.utils.Utility;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

public class ForecastFragment extends Fragment implements ForecastView {

    @BindView(R.id.no_internet_block)
    LinearLayout noInternetBlock;
    @BindView(R.id.no_internet_message)
    TextView noInternetMessage;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.temp)
    TextView temperature;
    @BindView(R.id.min_max_temp)
    TextView minMaxTemp;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.pressure)
    TextView pressure;
    @BindView(R.id.wind_speed)
    TextView windSpeed;
    @BindView(R.id.description)
    TextView description;

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
            forecastPresenter.getForecast(true);
        } else {
            forecastPresenter.getForecast(false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            forecastPresenter.getForecast(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        forecastPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecast_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showForecast(ForecastInfo forecast) {
        cityName.setText(forecast.getCity());
        temperature.setText(Utility.formatTemperature(getContext(), forecast.getTemp()));
        minMaxTemp.setText(getString(R.string.format_min_max_temp,
                Utility.formatTemperature(getContext(), forecast.getMinTemp()),
                Utility.formatTemperature(getContext(), forecast.getMaxTemp())));
        weatherIcon.setImageDrawable(getResources().getDrawable(
                Utility.getImageForWeatherCondition(forecast.getWeatherId())));
        windSpeed.setText(getString(R.string.format_wind, forecast.getWindSpeed()));
        pressure.setText(getString(R.string.format_pressure, forecast.getPressure()));
        humidity.setText(getString(R.string.format_humidity, forecast.getHumidity()));
        description.setText(forecast.getDescription());
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
    }
}
