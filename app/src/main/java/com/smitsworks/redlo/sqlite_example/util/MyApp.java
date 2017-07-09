package com.smitsworks.redlo.sqlite_example.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by redlongcity on 09.07.2017.
 */

public class MyApp extends Application {
    private static Context context;

    public static Context getContext(){
        return MyApp.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.context =  getApplicationContext();
    }
}
