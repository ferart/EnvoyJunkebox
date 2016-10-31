package com.ferart.collaborativejunkebox.data.fcm.database;

import com.ferart.collaborativejunkebox.model.Party;
import com.firebase.geofire.GeoLocation;

/**
 * Created by ferar on 30/10/2016.
 */

public interface PartyLocationCallback {

    void partyLocationMoved(Party party, GeoLocation geoLocation);
    void partyFoundInLocation(Party party);
    void partyNotFoundError();
    void partyExitedLocation(Party party);
}
