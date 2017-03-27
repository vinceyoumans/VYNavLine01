package com.techlatin.vincentyoumans.vyflo22;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * DataStore
 *
 * Created by vincentyoumans on 3/27/16.
 */
public class DataStore {

    private static final String PREF_SELECTED_DEVICES = "prefSelectedDevices";

    private final SharedPreferences mPrefs;
    private final Gson mGson;
    private int mJson;


    public DataStore(Context context, Gson gson)
    {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mGson = gson;
    }

    public void save(ArrayList<BLE01Device> selectedDevices)
    {
        String json = mGson.toJson(selectedDevices);
        mPrefs.edit().putString(PREF_SELECTED_DEVICES, json).apply();
    }

    @Nullable
    public ArrayList<BLE01Device> load()
    {
        String json = getJson();
        if(json != null) {
            Type type = new TypeToken<ArrayList<BLE01Device>>() {}.getType();
            return mGson.fromJson(json, type);
        }
        else{
            return null;
        }
    }

    public String getJson() {
        return mPrefs.getString(PREF_SELECTED_DEVICES, null);
    }
}
