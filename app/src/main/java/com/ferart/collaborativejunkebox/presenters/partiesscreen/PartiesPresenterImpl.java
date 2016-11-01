package com.ferart.collaborativejunkebox.presenters.partiesscreen;

import com.ferart.collaborativejunkebox.domain.partymanager.CreatePartyInteractor;
import com.ferart.collaborativejunkebox.domain.partymanager.PartyLocationTrackerInteractor;
import com.firebase.geofire.GeoLocation;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by ferar on 31/10/2016.
 */

public class PartiesPresenterImpl implements PartiesPresenter {

    private CreatePartyInteractor createPartyInteractor;
    private PartyLocationTrackerInteractor partyLocationTrackerInteractor;

    @Inject
    public PartiesPresenterImpl(CreatePartyInteractor createPartyInteractor, PartyLocationTrackerInteractor partyLocationTrackerInteractor) {
        this.createPartyInteractor = createPartyInteractor;
        this.partyLocationTrackerInteractor = partyLocationTrackerInteractor;
    }

    @Override
    public void startPartyHere(GeoLocation geoLocation) {
        //todo: remove this from here
        createPartyInteractor.setUserUID("TabletVizio");
        createPartyInteractor.setHostToken("ddhXtpQfJ-w:APA91bH5CRXok7Aw0K_rqsKLbOX87Pd3bPJXXlmRCsC0a51gGbtdmTRwGqxlnFVgNwffSZE_6mhR-XRk7SvFvnyPhCr7Q97ong3ZyIn2NnudXShTinfZ17wUbANgT_V8INaIn3G_JEWt");
        createPartyInteractor.setPartyName("Test Party");
        createPartyInteractor.setLatitude(33.702332);
        createPartyInteractor.setLongitude(-118.004604);
        createPartyInteractor.execute();

        partyLocationTrackerInteractor.setGeoLocation(new GeoLocation(33.702332,-118.004604));
        partyLocationTrackerInteractor.setRadius(5);
        partyLocationTrackerInteractor.startPartyLocationTracker();
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
