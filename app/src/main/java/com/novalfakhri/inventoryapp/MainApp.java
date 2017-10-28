package com.novalfakhri.inventoryapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.novalfakhri.inventoryapp.database.DaoMaster;
import com.novalfakhri.inventoryapp.database.DaoSession;

/**
 * Created by Toshiba on 10/28/2017.
 */

public class MainApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        daoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "inventory_database.db").getWritableDb()
        ).newSession();

        Stetho.initializeWithDefaults(this);
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
