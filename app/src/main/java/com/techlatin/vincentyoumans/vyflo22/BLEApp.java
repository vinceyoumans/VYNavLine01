package com.techlatin.vincentyoumans.vyflo22;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * BLE Application Delegate
 *
 * Created by vincentyoumans on 3/27/16.
 */
public class BLEApp extends Application {

    private Gson mGson;
    private DataStore mDataStore;


    public static BLEApp get(Context context)
    {
        return (BLEApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // first called when application is opened as a new process
        mGson = new GsonBuilder().setPrettyPrinting().create();
        mDataStore = new DataStore(this, mGson);
    }

    public Gson getGson() {
        return mGson;
    }

    public DataStore getDataStore() {
        return mDataStore;
    }
}
