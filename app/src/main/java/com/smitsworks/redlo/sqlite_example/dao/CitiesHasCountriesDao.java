package com.smitsworks.redlo.sqlite_example.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.smitsworks.redlo.sqlite_example.model.CitiesHasCountries;

import java.sql.SQLException;

/**
 * Created by redlongcity on 09.07.2017.
 */

public class CitiesHasCountriesDao extends BaseDaoImpl<CitiesHasCountries, Integer>{
    public CitiesHasCountriesDao(ConnectionSource connectionSource, Class<CitiesHasCountries> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        setConnectionSource(connectionSource);
        initialize();
    }
}
