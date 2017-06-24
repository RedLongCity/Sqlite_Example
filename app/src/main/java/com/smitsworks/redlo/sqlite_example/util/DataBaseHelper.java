package com.smitsworks.redlo.sqlite_example.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
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

    public DataBaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource,Country.class);
            TableUtils.createTable(connectionSource,City.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable(connectionSource, Country.class, true);
            TableUtils.dropTable(connectionSource, City.class, true);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<Country, Integer> getCountryDao() {
        return CountryDao;
    }

    public void setCountryDao(Dao<Country, Integer> countryDao) {
        CountryDao = countryDao;
    }

    public RuntimeExceptionDao<Country, Integer> getCountryRuntimeDao() {
        return CountryRuntimeDao;
    }

    public void setCountryRuntimeDao(RuntimeExceptionDao<Country, Integer> countryRuntimeDao) {
        CountryRuntimeDao = countryRuntimeDao;
    }

    public Dao<City, Integer> getCityDao() {
        return CityDao;
    }

    public void setCityDao(Dao<City, Integer> cityDao) {
        CityDao = cityDao;
    }

    public RuntimeExceptionDao<City, Integer> getCityRuntimeDao() {
        return CityRuntimeDao;
    }

    public void setCityRuntimeDao(RuntimeExceptionDao<City, Integer> cityRuntimeDao) {
        CityRuntimeDao = cityRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        CountryDao = null;
        CountryRuntimeDao = null;
        CityDao = null;
        CityRuntimeDao = null;
    }
}
