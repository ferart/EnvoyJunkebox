package com.ferart.collaborativejunkebox.data.fcm.messaging;

import android.util.Log;

import com.ferart.collaborativejunkebox.ApplicationManager;
import com.ferart.collaborativejunkebox.data.preferences.AccessPreferencesDAO;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ferart on 03/10/2016.
 * tokens management
 */

public class MessagingInstanceIDService extends FirebaseInstanceIdService {
    private static final String  TAG =MessagingInstanceIDService.class.getName();

    @Inject
    AccessPreferencesDAO accessPreferences;
    @Inject
    @Named("preferenceToken")
    String preferenceToken;


    @Override
    public void onCreate() {
        super.onCreate();
        ((ApplicationManager)getApplicationContext()).getPresentationComponent().inject(this);
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        //saving token in preferences, for future use
       accessPreferences.putString(preferenceToken,refreshedToken);
    }
}
