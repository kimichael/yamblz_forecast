package com.example.kimichael.yamblz_forecast.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.TimeUtils;
import android.support.v7.preference.PreferenceManager;

import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;

/**
 * Created by Sinjvf on 30.07.2017.
 * work with shared preferences
 */

public class PreferencesManager {

    private static final String TEMP_DEFAULT_UNITS = "Celsius";

    private static final String KEY_TEMP_UNITS = "temp_units";
    private static final String KEY_INTERVAL = "sync_interval";

    public static final int DEFAULT_INTERVAL = 3600;
    private  final int[] intervals;

    private SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Resources res = context.getResources();
        intervals = res.getIntArray(R.array.interval_values);
    }

    public boolean isTempCelsius() {
        return sharedPreferences.getInt(KEY_TEMP_UNITS, 0) == 0;
    }

    public void saveTempUnit(int position) {
        sharedPreferences.edit().putInt(KEY_TEMP_UNITS, position).apply();
    }

    public int getTempPosition(){
        return sharedPreferences.getInt(KEY_TEMP_UNITS, 0);
    }

    public boolean containInterval() {
        return sharedPreferences.contains(KEY_INTERVAL);
    }

    public void saveInterval(int intervalPos) {
        sharedPreferences.edit().putInt(KEY_INTERVAL, intervals[intervalPos]).apply();
    }

    public void saveInterval() {
        sharedPreferences.edit().putInt(KEY_INTERVAL, DEFAULT_INTERVAL).apply();
    }

    public boolean isIntervalChanged(String key) {
        return key.equals(KEY_INTERVAL);
    }

    public int getInterval() {
        return sharedPreferences.getInt(KEY_INTERVAL, DEFAULT_INTERVAL);
    }

    public String getUnit() {
        return isTempCelsius() ? "C" : "F";
    }

}
