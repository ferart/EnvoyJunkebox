package com.ferart.collaborativejunkebox.data.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by ferar on 15/10/2016.
 */

public class AccessPreferencesDAOImpl implements AccessPreferencesDAO {



    SharedPreferences sharedPreferences;

    @Inject
    public AccessPreferencesDAOImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences=sharedPreferences;
    }

    @Override
    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    @Override
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }
}
