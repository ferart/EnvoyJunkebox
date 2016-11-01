package com.ferart.collaborativejunkebox.presenters.partiesscreen;

import com.ferart.collaborativejunkebox.presenters.common.BasePresenter;
import com.firebase.geofire.GeoLocation;

/**
 * Created by ferar on 31/10/2016.
 */

public interface PartiesPresenter extends BasePresenter {

    void startPartyHere(GeoLocation geoLocation);
}
