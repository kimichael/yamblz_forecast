package com.example.kimichael.yamblz_forecast.presentation.view;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kimichael.yamblz_forecast.R;
import com.example.kimichael.yamblz_forecast.presentation.view.fragment.AboutFragment;
import com.example.kimichael.yamblz_forecast.presentation.view.fragment.SettingsFragment;
import com.example.kimichael.yamblz_forecast.presentation.view.forecast.ForecastFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FRAGMENT_STATUS_WEATHER, FRAGMENT_STATUS_SETTINGS, FRAGMENT_STATUS_ABOUT, FRAGMENT_STATUS_NOT_CHOSEN})
    public @interface ChosenFragmentStatus {}
    public static final int FRAGMENT_STATUS_NOT_CHOSEN = -1;
    public static final int FRAGMENT_STATUS_WEATHER = 0;
    public static final int FRAGMENT_STATUS_SETTINGS = 1;
    public static final int FRAGMENT_STATUS_ABOUT = 2;


    private @MainActivity.ChosenFragmentStatus
    int chosenFragment;

    @Override
    protected void onResume() {
        super.onResume();
        changeFragment(chosenFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(getString(R.string.key_chosen_fragment), chosenFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    @SuppressWarnings("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null)
            changeFragment(savedInstanceState.getInt(getString(R.string.key_chosen_fragment), FRAGMENT_STATUS_WEATHER));
        else {
            chosenFragment = FRAGMENT_STATUS_NOT_CHOSEN;
            changeFragment(FRAGMENT_STATUS_WEATHER);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_settings:
                changeFragment(FRAGMENT_STATUS_SETTINGS);
                break;
            case R.id.nav_about:
                changeFragment(FRAGMENT_STATUS_ABOUT);
                break;
            case R.id.nav_weather:
            default:
                changeFragment(FRAGMENT_STATUS_WEATHER);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(@ChosenFragmentStatus int chosenFragment) {

        if (this.chosenFragment == chosenFragment)
            return;

        Fragment fragment;
        this.chosenFragment = chosenFragment;

        switch (chosenFragment) {
            case FRAGMENT_STATUS_SETTINGS:
                fragment = SettingsFragment.newInstance();
                break;
            case FRAGMENT_STATUS_ABOUT:
                fragment = AboutFragment.newInstance();
                break;
            case FRAGMENT_STATUS_WEATHER:
            default:
                fragment = ForecastFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content_container, fragment).commit();

    }
}
