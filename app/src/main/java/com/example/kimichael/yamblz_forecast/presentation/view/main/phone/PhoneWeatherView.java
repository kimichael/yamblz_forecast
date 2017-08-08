package com.example.kimichael.yamblz_forecast.presentation.view.main.phone;

import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.example.kimichael.yamblz_forecast.presentation.view.BaseView;

/**
 * Created by Sinjvf on 08.08.2017.
 * main screen view
 */

public interface PhoneWeatherView extends BaseView{
    void showCityByPosition(int pos);
    void addCity();
}
