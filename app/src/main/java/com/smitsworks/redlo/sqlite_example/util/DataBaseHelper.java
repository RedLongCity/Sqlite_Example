package com.smitsworks.redlo.sqlite_example.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.smitsworks.redlo.sqlite_example.model.CitiesHasCountries;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;

import java.sql.SQLException;

/**
 * Created by redlongcity on 24.06.2017.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String databaseName = "tours.db";
    private static final int databaseVersion = 1;

    private Dao<Country, Integer> CountryDao = null;
    private RuntimeExceptionDao<Country, Integer> CountryRuntimeDao = null;

    private Dao<City, Integer> CityDao = null;
    private RuntimeExceptionDao<City, Integer> CityRuntimeDao = null;

    private Dao<CitiesHasCountries, Integer> CitiesHasCountriesDao = null;
    private RuntimeExceptionDao<CitiesHasCountries, Integer> CitiesHasCountriesRuntimeDao = null;

    public DataBaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            Log.i(DataBaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource,Country.class);
            TableUtils.createTable(connectionSource,City.class);
            TableUtils.createTable(connectionSource, CitiesHasCountries.class);

            Country country = new Country();
            country.setTitle("England");
            City city = new City();
            city.setTitle("London");
            CitiesHasCountries citiesHasCountries = new CitiesHasCountries(city,country);
            getCountryDao().create(country);
            getCityDao().create(city);
            getCitiesHasCountriesDao().create(citiesHasCountries);
            Log.d("DataBase test",getCountryDao().queryForAll().toString());
            Log.d("DataBase test",getCityDao().queryForAll().toString());
            Log.d("DataBase test",getCitiesHasCountriesDao().queryForAll().toString());

        }catch(SQLException e){
            Log.e(DataBaseHelper.class.getName(), "Can't create database", e);
            e.printStackTrace();
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            Log.i(DataBaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Country.class, true);
            TableUtils.dropTable(connectionSource, City.class, true);
            TableUtils.dropTable(connectionSource, CitiesHasCountries.class, true);
        }catch(SQLException e){
            Log.e(DataBaseHelper.class.getName(), "Can't drop databases", e);
            e.printStackTrace();
        }
    }

    public Dao<Country, Integer> getCountryDao() throws SQLException {
        if(CountryDao==null)
            CountryDao = getDao(Country.class);
        return CountryDao;
    }

    public RuntimeExceptionDao<Country, Integer> getCountryRuntimeDao() throws SQLException {
        if(CountryRuntimeDao==null)
            CountryRuntimeDao = getRuntimeExceptionDao(Country.class);
        return CountryRuntimeDao;
    }

    public Dao<City, Integer> getCityDao() throws SQLException {
        if(CityDao==null)
            CityDao = getDao(City.class);
        return CityDao;
    }

    public RuntimeExceptionDao<City, Integer> getCityRuntimeDao() throws SQLException {
        if(CityRuntimeDao==null)
            CityRuntimeDao = getRuntimeExceptionDao(City.class);
        return CityRuntimeDao;
    }

    public Dao<CitiesHasCountries, Integer> getCitiesHasCountriesDao() throws SQLException{
        if(CitiesHasCountriesDao ==null)
            CitiesHasCountriesDao = getDao(CitiesHasCountries.class);
        return CitiesHasCountriesDao;
    }

    public RuntimeExceptionDao<CitiesHasCountries, Integer> getCitiesHasCountriesRuntimeDao() throws SQLException {
        if(CitiesHasCountriesRuntimeDao==null)
            CitiesHasCountriesRuntimeDao = getRuntimeExceptionDao(CitiesHasCountries.class);
        return CitiesHasCountriesRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        CountryDao = null;
        CountryRuntimeDao = null;
        CityDao = null;
        CityRuntimeDao = null;
        CitiesHasCountriesDao = null;
        CitiesHasCountriesRuntimeDao = null;
    }
}
