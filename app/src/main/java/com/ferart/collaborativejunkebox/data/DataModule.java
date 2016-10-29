package com.ferart.collaborativejunkebox.data;

import com.google.firebase.database.DatabaseReference;

import com.ferart.collaborativejunkebox.data.fcm.messaging.FCMMessageDAO;
import com.ferart.collaborativejunkebox.data.fcm.messaging.FCMMessageDAOImpl;
import com.ferart.collaborativejunkebox.data.preferences.AccessPreferencesDAO;
import com.ferart.collaborativejunkebox.data.preferences.AccessPreferencesDAOImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Created by ferar on 15/10/2016.
 */
@Module
public class DataModule {

    @Provides
    @Named("appServerURL")
    String providesAppServerURL(){
        return "http://192.168.1.69:8080/suggestion";
    }

    @Provides
    @Named("JSONMediaType")
    MediaType providesMediaType(){
        return MediaType.parse("application/json; charset=utf-8");
    }

    @Provides
    @Named("preferenceToken")
    String providesPreferenceToken(){
        return "token_id_preference";
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(){
        return new OkHttpClient();
    }


    @Provides
    @Singleton
    AccessPreferencesDAO providesAccessPreferences(){
        return new AccessPreferencesDAOImpl();
    }

    @Provides
    @Singleton
    FCMMessageDAO providesFcmMessageDAO(OkHttpClient okHttpClient, @Named("appServerURL") String appServerURL,@Named("JSONMediaType") MediaType jsonMediaType){
        return new FCMMessageDAOImpl(okHttpClient,appServerURL,jsonMediaType);
    }






}
