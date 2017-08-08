package com.example.kimichael.yamblz_forecast.data.database;


import com.example.kimichael.yamblz_forecast.data.common.ForecastInfo;
import com.example.kimichael.yamblz_forecast.data.common.PlaceData;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio.sqlite.queries.Query;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sinjvf on 08.08.2017.
 * Class for Db operations
 */


public class DbClientImpl implements DbClient {

    private StorIOSQLite storIOSQLite;

    @Inject
    public DbClientImpl(StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }


    @Override
    public void saveWeather(ForecastInfo forecast) {
        List<PlaceData> receivedCities = storIOSQLite
                .get()
                .listOfObjects(PlaceData.class)
                .withQuery(Query.builder() // Query builder
                        .table(CitiesTable.TABLE)
                        .where(CitiesTable.COLUMN_LAT + "= ?")
                        .whereArgs(forecast.getLat())
                        .where(CitiesTable.COLUMN_LNG + "= ?")
                        .whereArgs(forecast.getLng())
                        .build())
                .prepare()
                .executeAsBlocking();

        if (receivedCities.size() == 0) return;
        Integer id = receivedCities.get(0).getId();
        if (id != null) {
            forecast.setId(id);
            storIOSQLite
                    .put()
                    .object(forecast)
                    .prepare()
                    .executeAsBlocking();
        }
    }

    @Override
    public void saveForecast(List<ForecastInfo> forecastList) {
        if (forecastList == null || forecastList.size() == 0) return;
        ForecastInfo forecast = forecastList.get(0);
        List<PlaceData> receivedCities = storIOSQLite
                .get()
                .listOfObjects(PlaceData.class)
                .withQuery(Query.builder() // Query builder
                        .table(CitiesTable.TABLE)
                        .where(CitiesTable.COLUMN_LAT + "= ?")
                        .whereArgs(forecast.getLat())
                        .where(CitiesTable.COLUMN_LNG + "= ?")
                        .whereArgs(forecast.getLng())
                        .build())
                .prepare()
                .executeAsBlocking();

        if (receivedCities.size() == 0) return;
        Integer id = receivedCities.get(0).getId();
        if (id != null) {
            for (ForecastInfo info : forecastList)
                info.setId(id);
        }
        storIOSQLite
                .put()
                .objects(forecastList)
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public void deleteCity(PlaceData data) {
        Integer id = data.getId();
        if (id != null) {
            storIOSQLite
                    .delete()
                    .byQuery(DeleteQuery.builder()
                            .table(ForecastsTable.TABLE)
                            .where(ForecastsTable.COLUMN_CITY_ID + "= ?")
                            .whereArgs(id)
                            .build())
                    .prepare()
                    .executeAsBlocking();
        }
        storIOSQLite
                .delete()
                .object(data)
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public void addCity(PlaceData data) {
        storIOSQLite
                .put()
                .object(data)
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public List<PlaceData> getAllCities() {
        return storIOSQLite
                .get()
                .listOfObjects(PlaceData.class)
                .withQuery(Query.builder()
                        .table(CitiesTable.TABLE)
                        .orderBy(CitiesTable.COLUMN_ID)
                        .build())
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public void delOldForecasts(ForecastInfo forecast, long timeInterval) {
        //forecasts before this time are not actual
        long time = Calendar.getInstance().getTime().getTime()-timeInterval;
        storIOSQLite
                .delete()
                .byQuery(DeleteQuery.builder()
                        .table(ForecastsTable.TABLE)
                        .where(ForecastsTable.COLUMN_DATE + "< ?")
                        .whereArgs(time)
                        .build())
                .prepare()
                .executeAsBlocking();
    }


}
