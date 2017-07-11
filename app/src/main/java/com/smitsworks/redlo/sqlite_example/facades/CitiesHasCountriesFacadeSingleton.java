package com.smitsworks.redlo.sqlite_example.facades;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.smitsworks.redlo.sqlite_example.dao.CitiesHasCountriesDao;
import com.smitsworks.redlo.sqlite_example.model.CitiesHasCountries;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.util.DataBaseHelper;
import com.smitsworks.redlo.sqlite_example.util.MyApp;

import java.sql.SQLException;

/**
 * Created by redlongcity on 09.07.2017.
 */

public class CitiesHasCountriesFacadeSingleton {

    private City city;
    private Country country;
    private DataBaseHelper dataBaseHelper;
    private CitiesHasCountriesDao citiesHasCountriesDao;

    private static final CitiesHasCountriesFacadeSingleton ourInstance = new CitiesHasCountriesFacadeSingleton();

    static CitiesHasCountriesFacadeSingleton getInstance(){
        return ourInstance;
    }

    public static CitiesHasCountriesFacadeSingleton getOurInstance(){
        return ourInstance;
    }

    private CitiesHasCountriesFacadeSingleton(){
        dataBaseHelper = new DataBaseHelper(MyApp.getContext());

    }

    private void daoCitiesHasCountries() throws SQLException {
        citiesHasCountriesDao = (CitiesHasCountriesDao) dataBaseHelper.getCitiesHasCountriesDao();//!!!
    }

    public CitiesHasCountriesDao getCitiesHasCountriesDao() {
        return citiesHasCountriesDao;
    }

    private PreparedQuery<City> makeCitiesForCountriesQuery() throws SQLException{
        QueryBuilder<CitiesHasCountries, Integer> citiesHasCountriesQb = citiesHasCountriesDao.queryBuilder();
        citiesHasCountriesQb.selectColumns(CitiesHasCountries.CITIES_ID_FIELD_NAME);
        SelectArg citiesSelectArg = new SelectArg();

    }
}
