package com.example.zhai.sqlitedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Meiji on 2016/12/12.
 */

public class InitApp extends Application {

    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = this.getApplicationContext();
    }
}
