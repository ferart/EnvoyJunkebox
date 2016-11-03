package com.ferart.collaborativejunkebox.domain.partymanager;

import android.text.TextUtils;
import android.util.Log;

import com.ferart.collaborativejunkebox.data.fcm.database.JukeboxDBDAO;
import com.ferart.collaborativejunkebox.data.fcm.database.PartyLocationCallback;
import com.ferart.collaborativejunkebox.domain.common.BaseInteractor;
import com.ferart.collaborativejunkebox.domain.thread.Executor;
import com.ferart.collaborativejunkebox.domain.thread.MainThread;
import com.ferart.collaborativejunkebox.domain.thread.ThreadExecutor;
import com.ferart.collaborativejunkebox.model.Party;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;

import javax.inject.Inject;

/**
 * Created by ferar on 30/10/2016.
 */

public class PartyLocationTrackerInteractor extends BaseInteractor {

    private static final String TAG = PartyLocationTrackerInteractor.class.getName();

    private ThreadExecutor threadExecutor;
    private JukeboxDBDAO jukeboxDBDAO;
    GeoQuery geoQuery;

    private PartyLocationCallback partyLocationCallback;
    private GeoLocation geoLocation;
    private float radius;

    @Inject
    public PartyLocationTrackerInteractor(Executor executor, MainThread mainThread, JukeboxDBDAO jukeboxDBDAO) {
        super(executor, mainThread);
        threadExecutor=((ThreadExecutor)executor);
        this.jukeboxDBDAO=jukeboxDBDAO;
    }

    @Override
    public void run() {
        if (geoLocation==null && radius==0f){
            throw  new IllegalArgumentException("location can no to be null and radius greater than 0");
        }
        startPartyLocationTracker();
    }

    public void startPartyLocationTracker(){
        geoQuery=jukeboxDBDAO.createLocationQuery(geoLocation, radius);
        jukeboxDBDAO.addLocationQueryListener(geoQuery, new PartyLocationCallback() {
            @Override
            public void partyLocationMoved(Party party, GeoLocation geoLocation) {
                if (partyLocationCallback== null)
                    return;
                postOnMainThread(() -> {
                    Log.e(TAG, "party location moved");
                    partyLocationCallback.partyLocationMoved(party,geoLocation);
                });
            }

            @Override
            public void partyFoundInLocation(Party party) {
                if (partyLocationCallback== null)
                    return;
                postOnMainThread(() -> {
                    Log.e(TAG, "party location moved");
                    partyLocationCallback.partyFoundInLocation(party);
                });
            }

            @Override
            public void partyNotFoundError() {
                if (partyLocationCallback== null)
                    return;

                postOnMainThread(() -> {
                    Log.e(TAG, "party location moved");
                    partyLocationCallback.partyNotFoundError();
                });

            }

            @Override
            public void partyExitedLocation(Party party) {
                if (partyLocationCallback== null)
                    return;
                postOnMainThread(() -> {
                    Log.e(TAG, "party location moved");
                    partyLocationCallback.partyExitedLocation(party);
                });
            }
        });
    }

    public void  stopPartyLocationTracker(){
        if (geoQuery!=null) {
            jukeboxDBDAO.removeLocationQueryListener(geoQuery);
        }
    }

    public void setPartyLocationCallback(PartyLocationCallback partyLocationCallback) {
        this.partyLocationCallback = partyLocationCallback;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
