package com.example.kimichael.yamblz_forecast.presentation.view.main.phone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kimichael.yamblz_forecast.presentation.view.forecast.BaseForecastFragment;

import java.util.List;

/**
 * Created by Sinjvf on 08.08.2017.
 * adapter for viewPager in main Screen
 */
 class PagerAdapter extends FragmentPagerAdapter {
    private List< BaseForecastFragment> pagersData;

     PagerAdapter(FragmentManager fm, List<BaseForecastFragment> pagersData) {
        super(fm);
        this.pagersData = pagersData;
    }

    @Override
    public Fragment getItem(int position) {
        return pagersData.get(position);
    }

    @Override
    public int getCount() {
        return pagersData.size();
    }


}