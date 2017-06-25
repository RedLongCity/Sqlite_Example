package com.smitsworks.redlo.sqlite_example.util;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by redlongcity on 24.06.2017.
 */

public class DataBaseConfigUtil extends OrmLiteConfigUtil {
    public static void main(String[] args) throws IOException, SQLException{
        writeConfigFile("ormlite_config.txt");//save to raw/ormlite_config.txt
    }
}
