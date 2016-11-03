package com.ferart.collaborativejunkebox.presenters.partiesscreen;

import com.ferart.collaborativejunkebox.data.SimpleCallback;
import com.ferart.collaborativejunkebox.data.fcm.database.PartyLocationCallback;
import com.ferart.collaborativejunkebox.domain.partymanager.CreatePartyInteractor;
import com.ferart.collaborativejunkebox.domain.partymanager.PartyLocationTrackerInteractor;
import com.ferart.collaborativejunkebox.model.Party;
import com.ferart.collaborativejunkebox.scoop.controllers.parties.PartiesInTownActionsController;
import com.firebase.geofire.GeoLocation;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by ferar on 31/10/2016.
 */

public class PartiesPresenterImpl implements PartiesPresenter {

    private PartiesInTownActionsController partiesViewController;

    private CreatePartyInteractor createPartyInteractor;
    private PartyLocationTrackerInteractor partyLocationTrackerInteractor;

    @Inject
    public PartiesPresenterImpl(CreatePartyInteractor createPartyInteractor, PartyLocationTrackerInteractor partyLocationTrackerInteractor) {
        this.createPartyInteractor = createPartyInteractor;
        this.partyLocationTrackerInteractor = partyLocationTrackerInteractor;
    }

    @Override
    public void setPartiesViewController(PartiesInTownActionsController partiesViewController) {
        this.partiesViewController = partiesViewController;
    }

    @Override
    public void startPartyHere(GeoLocation geoLocation) {
        //createParties();
    }


    int index=1;
    String userUID="User_";
    String partyName="Party_";
    private void createParties(){

        //todo: remove this from here
        if (index==6){
            return;
        }
        createPartyInteractor.setUserUID(userUID+index);
        createPartyInteractor.setHostToken("ddhXtpQfJ-w:APA91bH5CRXok7Aw0K...");
        createPartyInteractor.setPartyName(partyName+index);
        createPartyInteractor.setLatitude(33.6340053);
        createPartyInteractor.setLongitude(-117.7375235);
        createPartyInteractor.setSimpleCallback(new SimpleCallback() {
            @Override
            public void onSuccess() {
                index++;
                createParties();
            }

            @Override
            public void onError() {

            }
        });
        createPartyInteractor.execute();
    }

    @Override
    public void findPartiesInMyCurrentLocation(double latitude, double longitude, float radius) {
        partyLocationTrackerInteractor.setGeoLocation(new GeoLocation(latitude,longitude));
        partyLocationTrackerInteractor.setRadius(radius);
        partyLocationTrackerInteractor.setPartyLocationCallback(new PartyLocationCallback() {
            @Override
            public void partyLocationMoved(Party party, GeoLocation geoLocation) {
                party.setLatitude(geoLocation.latitude);
                party.setLongitude(geoLocation.longitude);
                partiesViewController.moveMarker(party);
            }

            @Override
            public void partyFoundInLocation(Party party) {
                //todo: check if this presenter is active
                partiesViewController.showMarker(party);
            }

            @Override
            public void partyNotFoundError() {

            }

            @Override
            public void partyExitedLocation(Party party) {
                partiesViewController.removeMarker(party.getUserUID());
            }
        });
        partyLocationTrackerInteractor.execute();
    }

    @Override
    public void onAttached(ViewController viewController) {

    }

    @Override
    public void onDetached() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onBackPress() {

    }

    @Override
    public void onDestroy() {

    }
}
