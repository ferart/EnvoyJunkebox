package com.ferart.collaborativejunkebox.scoop;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ferar on 15/10/2016.
 */
@Module
public class ApplicationModule {

    private Application context;

    public ApplicationModule(Application context) {
        this.context = context;
    }

    @Provides
    SharedPreferences providesSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
