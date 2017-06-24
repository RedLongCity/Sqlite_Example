package com.smitsworks.redlo.sqlite_example.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.smitsworks.redlo.sqlite_example.model.Country;

import java.sql.SQLException;

/**
 * Created by redlongcity on 24.06.2017.
 */

public class CountryDao extends BaseDaoImpl<Country, Integer>{
    public CountryDao(ConnectionSource connectionSource, Class<Country> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        setConnectionSource(connectionSource);
        initialize();
    }
}
