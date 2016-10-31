package com.ferart.collaborativejunkebox.data.fcm.database;

import com.ferart.collaborativejunkebox.data.SimpleCallback;
import com.ferart.collaborativejunkebox.model.Party;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Envoy on 10/27/16.
 */

public interface JukeboxDBDAO {

    /**
     * JUKEBOX HOST
     * to save party data, becoming a jukebox
     * @param userUID
     * @param name
     * @param hostToken
     */
    void writeNewParty(final String userUID, String name, String hostToken,final  GeoLocation geoLocation, SimpleCallback simpleCallback);

    /**
     * JUKEBOX HOST
     * write party location in GeoFire when a a new Party is written
     * @param userUID
     * @param geoLocation
     */
    void writePartyLocation(String userUID, GeoLocation geoLocation, SimpleCallback simpleCallback);

    void getParty(String userUID, ValueEventListener valueEventListener);

    /**
     * JUKEBOX HOST
     * update fcm token in database, only for use by a jukebox host
     * @param userUID
     * @param hostToken
     */
    void updateHostToken(String userUID, String hostToken);

    /**
     * JUKEBOX HOST
     * Deletes party
     * @param userUID
     */
    void deleteParty(String userUID, SimpleCallback simpleCallback);

    /**
     * * JUKEBOX HOST
     * @param userUID
     */
    void deletePartyLocation(String userUID, SimpleCallback simpleCallback);

    GeoQuery createLocationQuery(GeoLocation geoLocation, float radius);

    void addLocationQueryListener(GeoQuery geoQuery, PartyLocationCallback partyLocationCallback);

    void removeLocationQueryListener(GeoQuery geoQuery);

    /**
     * follow any update to a party i belong
     * @param userUID
     */
    void followPartyUpdates(String userUID);

    void unfollowPartyUpdates(String userUID);

}
