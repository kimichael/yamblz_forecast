package com.example.kimichael.yamblz_forecast.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.example.kimichael.yamblz_forecast.R;

/**
 * Created by Kim Michael on 16.07.17
 */
public class Utility {

    public static String formatTemperature(Context context, double temperature) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        double temp;
        if (sp.getString(context.getString(R.string.pref_key_temp_units),
                context.getString(R.string.celcius)).equals(context.getString(R.string.celcius))) {
            temp = temperature - 273.15d;
//            temp = 9 * temperature / 5 + 32;
        } else {
            temp = temperature * 9 / 5 - 459.67d;
        }
        return context.getString(R.string.format_temperature, temp);
    }

    public static int getImageForWeatherCondition(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_rain;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        }
        return -1;
    }

}
