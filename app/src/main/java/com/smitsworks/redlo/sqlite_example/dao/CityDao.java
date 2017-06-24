package com.smitsworks.redlo.sqlite_example.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.smitsworks.redlo.sqlite_example.model.City;

import java.sql.SQLException;

/**
 * Created by redlongcity on 24.06.2017.
 */

public class CityDao extends BaseDaoImpl<City, Integer> {
    public CityDao(ConnectionSource connectionSource, Class<City> dataClass) throws SQLException {
        super(connectionSource, dataClass);
        setConnectionSource(connectionSource);
        initialize();
    }
}
