package com.smitsworks.redlo.sqlite_example.facades;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
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
    private CitiesHasCountries citiesHasCountries;

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

    private void daoCitiesHasCountries(int id) throws SQLException {
        citiesHasCountries = dataBaseHelper.getCitiesHasCountriesDao().queryForId(id);
    }

    public CitiesHasCountries getCitiesHasCountries() {
        return citiesHasCountries;
    }

    private PreparedQuery<City> makeCitiesForCountriesQuery() throws SQLException{
        QueryBuilder<CitiesHasCountries, Integer> citiesHasCountriesQb =
    }
}
