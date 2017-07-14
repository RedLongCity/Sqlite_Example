package com.smitsworks.redlo.sqlite_example.facades;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.smitsworks.redlo.sqlite_example.model.CitiesHasCountries;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.util.DataBaseHelper;
import com.smitsworks.redlo.sqlite_example.util.MyApp;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by redlongcity on 09.07.2017.
 */

public class CitiesHasCountriesFacadeSingleton {

    private DataBaseHelper dataBaseHelper;
    private PreparedQuery<City> citiesForCountriesQuery;
    private PreparedQuery<Country> countriesForCitiesQuerry;

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

    private List<City> lookupCitiesForCoutries(Country country) throws SQLException {
        if(citiesForCountriesQuery==null){
            citiesForCountriesQuery = makeCitiesForCountriesQuery();
        }
        citiesForCountriesQuery.setArgumentHolderValue(0,country);
        return dataBaseHelper.getCityDao().query(citiesForCountriesQuery);
    }

    private List<Country> lookupCountriesForCities(City city) throws SQLException{
        if(countriesForCitiesQuerry==null){
            countriesForCitiesQuerry = makeCountriesForCitiesQuery();
        }
        countriesForCitiesQuerry.setArgumentHolderValue(0,city);
        return dataBaseHelper.getCountryDao().query(countriesForCitiesQuerry);
    }

    private PreparedQuery<Country> makeCountriesForCitiesQuery() throws SQLException{
        QueryBuilder<CitiesHasCountries, Integer> citiesHasCountriesQb = dataBaseHelper.getCitiesHasCountriesDao().queryBuilder();
        citiesHasCountriesQb.selectColumns(CitiesHasCountries.COUNTRIES_ID_FIELD_NAME);
        SelectArg citiesSelectArg = new SelectArg();
        citiesHasCountriesQb.where().eq(CitiesHasCountries.CITIES_ID_FIELD_NAME, citiesSelectArg);
        QueryBuilder<Country,Integer> coutryQb = dataBaseHelper.getCountryDao().queryBuilder();
        coutryQb.where().in(Country.COLUMN_ID, citiesHasCountriesQb);
        return coutryQb.prepare();
    }

    private PreparedQuery<City> makeCitiesForCountriesQuery() throws SQLException{
        QueryBuilder<CitiesHasCountries, Integer> citiesHasCountriesQb = dataBaseHelper.getCitiesHasCountriesDao().queryBuilder();
        citiesHasCountriesQb.selectColumns(CitiesHasCountries.CITIES_ID_FIELD_NAME);
        SelectArg countiesSelectArg = new SelectArg();
        citiesHasCountriesQb.where().eq(CitiesHasCountries.COUNTRIES_ID_FIELD_NAME, countiesSelectArg);
        QueryBuilder<City,Integer> citiesQb = dataBaseHelper.getCityDao().queryBuilder();
        citiesQb.where().in(City.COLUMN_ID, citiesHasCountriesQb);
        return citiesQb.prepare();
    }
}
