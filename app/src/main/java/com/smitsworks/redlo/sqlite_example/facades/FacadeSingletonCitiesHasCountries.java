package com.smitsworks.redlo.sqlite_example.facades;

import android.support.v4.util.LogWriter;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.smitsworks.redlo.sqlite_example.model.CitiesHasCountries;
import com.smitsworks.redlo.sqlite_example.model.City;
import com.smitsworks.redlo.sqlite_example.model.Country;
import com.smitsworks.redlo.sqlite_example.model.DataPoint;
import com.smitsworks.redlo.sqlite_example.util.DataBaseHelper;
import com.smitsworks.redlo.sqlite_example.util.MyApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by redlongcity on 09.07.2017.
 */

public class FacadeSingletonCitiesHasCountries {

    private DataBaseHelper dataBaseHelper;
    private PreparedQuery<City> citiesForCountriesQuery;
    private PreparedQuery<Country> countriesForCitiesQuerry;
    private PreparedQuery<CitiesHasCountries> citiesHasCountriesForCitiesQuerry;
    private PreparedQuery<CitiesHasCountries> citiesHasCountriesForCountriesQuerry;

    private static final FacadeSingletonCitiesHasCountries ourInstance = new FacadeSingletonCitiesHasCountries();

    static FacadeSingletonCitiesHasCountries getInstance(){
        return ourInstance;
    }

    public static FacadeSingletonCitiesHasCountries getOurInstance(){
        return ourInstance;
    }

    private FacadeSingletonCitiesHasCountries(){
        dataBaseHelper = new DataBaseHelper(MyApp.getContext());
        try{

//            Country country = new Country();
//            country.setTitle("England");
//            City city = new City();
//            city.setTitle("London");
//            CitiesHasCountries citiesHasCountries = new CitiesHasCountries(city,country);
//            dataBaseHelper.getCountryDao().create(country);
//            dataBaseHelper.getCityDao().create(city);
//            dataBaseHelper.getCitiesHasCountriesDao().create(citiesHasCountries);
            Log.d("DataBase test",dataBaseHelper.getCountryDao().queryForAll().toString());
            Log.d("DataBase test",dataBaseHelper.getCityDao().queryForAll().toString());
            Log.d("DataBase test",dataBaseHelper.getCitiesHasCountriesDao().queryForAll().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void persisitData(List<City> citiesList, List<Country> countriesList) throws SQLException, IOException {
        if(citiesList.isEmpty()||countriesList.isEmpty()){
            LogWriter logWriter = new LogWriter("Persist Error");
            logWriter.write("citiesList or countriesList is empty");
            return;
        }
        Iterator<City> cityIterator = citiesList.iterator();
        while(cityIterator.hasNext()){
            City city = new City();
            city.setTitle(cityIterator.next().getTitle());
            Iterator<Country> countriesIterator = countriesList.iterator();
            while(countriesIterator.hasNext()){
                Country country = new Country();
                country.setTitle(countriesIterator.next().getTitle());
                CitiesHasCountries citiesHasCountries = new CitiesHasCountries(city,country);
                dataBaseHelper.getCityDao().create(city);
                dataBaseHelper.getCountryDao().create(country);
                dataBaseHelper.getCitiesHasCountriesDao().create(citiesHasCountries);
            }
        }
    }

    public List<Country> getAllCountries() throws SQLException {
        return dataBaseHelper.getCountryDao().queryForAll();
    }

    public List<City> getAllCities() throws SQLException{
        return dataBaseHelper.getCityDao().queryForAll();
    }

    public Boolean delete(DataPoint dataPoint) {
        if (dataPoint instanceof Country) {
            Country country = (Country) dataPoint;
            try {
                List<City> citiesList = lookupCitiesForCoutries(country);
                List<CitiesHasCountries> citiesHasCountriesList = lookupCitiesHasCountriesForDataPoint(dataPoint);
                dataBaseHelper.getCitiesHasCountriesDao().delete(citiesHasCountriesList);
                dataBaseHelper.getCityDao().delete(citiesList);
                dataBaseHelper.getCountryDao().delete(country);
                return true;
            }catch(SQLException e){
                Log.e("delete", "delete: finished by exception"+e.toString());
                return false;
            }
        }else{
            City city = (City) dataPoint;
            try {
                List<Country> countriesList = lookupCountriesForCities(city);
                List<CitiesHasCountries> citiesHasCountriesList = lookupCitiesHasCountriesForDataPoint(dataPoint);
                dataBaseHelper.getCitiesHasCountriesDao().delete(citiesHasCountriesList);
                dataBaseHelper.getCountryDao().delete(countriesList);
                dataBaseHelper.getCityDao().delete(city);
                return true;
            }catch (SQLException e){
                Log.e("delete", "delete: finished by exception"+e.toString());
                return false;
            }
        }

    }


    public List<City> lookupCitiesForCoutries(Country country) throws SQLException {
        if(citiesForCountriesQuery==null){
            citiesForCountriesQuery = makeCitiesForCountriesQuery();
        }
        citiesForCountriesQuery.setArgumentHolderValue(0,country);
        return dataBaseHelper.getCityDao().query(citiesForCountriesQuery);
    }

    public List<Country> lookupCountriesForCities(City city) throws SQLException{
        if(countriesForCitiesQuerry==null){
            countriesForCitiesQuerry = makeCountriesForCitiesQuery();
        }
        countriesForCitiesQuerry.setArgumentHolderValue(0,city);
        return dataBaseHelper.getCountryDao().query(countriesForCitiesQuerry);
    }

    public List<CitiesHasCountries> lookupCitiesHasCountriesForDataPoint(DataPoint dataPoint) throws SQLException {
        if (dataPoint instanceof Country) {
            Country country = (Country) dataPoint;
            if (citiesHasCountriesForCountriesQuerry == null) {
                citiesHasCountriesForCountriesQuerry = makeCitiesHasCountriesForCountryQuerry();
            }
            citiesHasCountriesForCountriesQuerry.setArgumentHolderValue(0,country);
            return dataBaseHelper.getCitiesHasCountriesDao().query(citiesHasCountriesForCountriesQuerry);
        }else{
            City city = (City) dataPoint;
            if (citiesHasCountriesForCitiesQuerry == null) {
                citiesHasCountriesForCitiesQuerry = makeCitiesHasCountriesForCityQuerry();
            }
            citiesHasCountriesForCitiesQuerry.setArgumentHolderValue(0,city);
            return dataBaseHelper.getCitiesHasCountriesDao().query(citiesHasCountriesForCitiesQuerry);
        }
    }

    public void clearAllTables() throws SQLException {
        dataBaseHelper.clearCityTable();
        dataBaseHelper.clearCountryTable();
        dataBaseHelper.clearCitiesHasCountriesTable();
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

    private PreparedQuery<CitiesHasCountries> makeCitiesHasCountriesForCountryQuerry() throws SQLException {
        QueryBuilder<CitiesHasCountries, Integer> citiesHasCountriesQb = dataBaseHelper.getCitiesHasCountriesDao().queryBuilder();
        citiesHasCountriesQb.selectColumns(CitiesHasCountries.COUNTRIES_ID_FIELD_NAME);
        SelectArg countiesSelectArg = new SelectArg();
        citiesHasCountriesQb.where().eq(CitiesHasCountries.COUNTRIES_ID_FIELD_NAME,countiesSelectArg);
        return citiesHasCountriesQb.prepare();
    }

    private PreparedQuery<CitiesHasCountries> makeCitiesHasCountriesForCityQuerry() throws SQLException{
        QueryBuilder<CitiesHasCountries, Integer> citiesHasCountriesQb = dataBaseHelper.getCitiesHasCountriesDao().queryBuilder();
        citiesHasCountriesQb.selectColumns(CitiesHasCountries.CITIES_ID_FIELD_NAME);
        SelectArg citiesSelectArg = new SelectArg();
        citiesHasCountriesQb.where().eq(CitiesHasCountries.CITIES_ID_FIELD_NAME,citiesSelectArg);
        return citiesHasCountriesQb.prepare();
    }
}
