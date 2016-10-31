package com.ferart.collaborativejunkebox.presenters.splashscreen;

import com.ferart.collaborativejunkebox.domain.fcmtoken.FCMMessageInteractor;
import com.ferart.collaborativejunkebox.domain.partymanager.CreatePartyInteractor;
import com.ferart.collaborativejunkebox.domain.partymanager.PartyLocationTrackerInteractor;
import com.ferart.collaborativejunkebox.domain.splash.SplashInteractor;
import com.ferart.collaborativejunkebox.scoop.controllers.splash.SplashActionsController;
import com.firebase.geofire.GeoLocation;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by root on 9/25/16.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private SplashInteractor splashInteractor;

    /**
     * View to communicate with
     */
    private SplashActionsController viewController;

    /**
     * TODO delete every variable after this
     */
    private CreatePartyInteractor createPartyInteractor;
    private PartyLocationTrackerInteractor partyLocationTrackerInteractor;

    @Inject
    public SplashPresenterImpl(SplashInteractor splashInteractor, CreatePartyInteractor createPartyInteractor, PartyLocationTrackerInteractor partyLocationTrackerInteractor) {
        this.splashInteractor = splashInteractor;
        this.createPartyInteractor=createPartyInteractor;
        this.partyLocationTrackerInteractor=partyLocationTrackerInteractor;
    }



    @Override
    public void onAttached(ViewController viewController) {
        this.viewController=(SplashActionsController) viewController;
    }

    @Override
    public void startAnimation() {
        splashInteractor.execute();


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



//        fcmMessageInteractor.setTokenID("cfzyVYjMot4:APA91bHunoDwwB8fHeKJbDJTEJSBzBK1jX8LJEHTFfTP4yGs6L_gCnJIvwNFtDh2bGv_SwERqwhtJrjIWXAasWqviGDwGWnSp4YLGK6O6q1CphmpGL7jRPdRdDwfNsy9sHOixCyqoLTD");
//        fcmMessageInteractor.setMessage("Message sent by the app, fuck yeah!");
//        fcmMessageInteractor.execute();
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
