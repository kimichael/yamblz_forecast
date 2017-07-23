package com.example.kimichael.yamblz_forecast.presentation.view.settings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.kimichael.yamblz_forecast.R;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

/**
 * Created by Sinjvf on 23.07.2017.
 */

public class GooglePlaceFragment extends SupportPlaceAutocompleteFragment {
    public GooglePlaceFragment() {
        super();
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        this.setFilter(typeFilter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHint(getString(R.string.choose_city));
    }
}
