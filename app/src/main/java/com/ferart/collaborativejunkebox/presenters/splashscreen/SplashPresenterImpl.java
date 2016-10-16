package com.ferart.collaborativejunkebox.presenters.splashscreen;

import com.ferart.collaborativejunkebox.domain.fcmtoken.FCMMessageInteractor;
import com.ferart.collaborativejunkebox.domain.splash.SplashInteractor;
import com.ferart.collaborativejunkebox.scoop.controllers.splash.SplashActionsController;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by root on 9/25/16.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private SplashInteractor splashInteractor;
    private FCMMessageInteractor fcmMessageInteractor;
    /**
     * View to communicate with
     */
    private SplashActionsController viewController;

    @Inject
    public SplashPresenterImpl(SplashInteractor splashInteractor, FCMMessageInteractor fcmMessageInteractor) {
        this.splashInteractor = splashInteractor;
        this.fcmMessageInteractor=fcmMessageInteractor;
    }



    @Override
    public void onAttached(ViewController viewController) {
        this.viewController=(SplashActionsController) viewController;
    }

    @Override
    public void startAnimation() {
        splashInteractor.execute();

        //todo: remove this from here
        fcmMessageInteractor.setTokenID("cfzyVYjMot4:APA91bHunoDwwB8fHeKJbDJTEJSBzBK1jX8LJEHTFfTP4yGs6L_gCnJIvwNFtDh2bGv_SwERqwhtJrjIWXAasWqviGDwGWnSp4YLGK6O6q1CphmpGL7jRPdRdDwfNsy9sHOixCyqoLTD");
        fcmMessageInteractor.setMessage("Message sent by the app");
        fcmMessageInteractor.execute();
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
