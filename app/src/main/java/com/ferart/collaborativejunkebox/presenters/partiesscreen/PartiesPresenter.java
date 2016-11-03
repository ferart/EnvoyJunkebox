package com.ferart.collaborativejunkebox.presenters.partiesscreen;

import com.ferart.collaborativejunkebox.presenters.common.BasePresenter;
import com.ferart.collaborativejunkebox.scoop.controllers.parties.PartiesInTownActionsController;
import com.firebase.geofire.GeoLocation;

/**
 * Created by ferar on 31/10/2016.
 */

public interface PartiesPresenter extends BasePresenter {

    void startPartyHere(GeoLocation geoLocation);

    void findPartiesInMyCurrentLocation(double latitude, double longitude, float radius);

    void setPartiesViewController(PartiesInTownActionsController partiesViewController);
}
