package com.ferart.collaborativejunkebox.domain.partymanager;

import android.text.TextUtils;
import android.util.Log;

import com.ferart.collaborativejunkebox.data.SimpleCallback;
import com.ferart.collaborativejunkebox.data.fcm.database.JukeboxDBDAO;
import com.ferart.collaborativejunkebox.domain.common.BaseInteractor;
import com.ferart.collaborativejunkebox.domain.thread.Executor;
import com.ferart.collaborativejunkebox.domain.thread.MainThread;
import com.ferart.collaborativejunkebox.domain.thread.ThreadExecutor;
import com.firebase.geofire.GeoLocation;

import javax.inject.Inject;

/**
 * Created by ferar on 29/10/2016.
 */

public class CreatePartyInteractor extends BaseInteractor {

    private static final String TAG= CreatePartyInteractor.class.getName();

    private String userUID, partyName, hostToken;
    private double latitude, longitude;

    private JukeboxDBDAO jukeboxDBDAO;

    @Inject
    public CreatePartyInteractor(Executor executor, MainThread mainThread, JukeboxDBDAO jukeboxDBDAO) {
        super(executor, mainThread);
        this.jukeboxDBDAO=jukeboxDBDAO;
    }

    @Override
    public void run() {
        if (TextUtils.isEmpty(userUID) && TextUtils.isEmpty(hostToken) ){
            throw  new IllegalArgumentException("Token and Message can not be null or empty");
        }
        createNewParty();
    }

    private void createNewParty(){
        jukeboxDBDAO.writeNewParty(userUID, partyName, hostToken, new GeoLocation(latitude, longitude), new SimpleCallback() {
            @Override
            public void onSuccess() {
                postOnMainThread(() -> {
                    Log.i(TAG, "party created");
                });
            }

            @Override
            public void onError() {
                postOnMainThread(() -> {
                    Log.e(TAG, "party creation error");
                });
            }
        });
    }


    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setHostToken(String hostToken) {
        this.hostToken = hostToken;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
