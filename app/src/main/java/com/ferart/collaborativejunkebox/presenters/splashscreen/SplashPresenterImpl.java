package com.ferart.collaborativejunkebox.presenters.splashscreen;

import com.ferart.collaborativejunkebox.domain.splash.SplashInteractor;
import com.ferart.collaborativejunkebox.scoop.controllers.splash.SplashActionsController;
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

    @Inject
    public SplashPresenterImpl(SplashInteractor splashInteractor) {
        this.splashInteractor = splashInteractor;
    }



    @Override
    public void onAttached(ViewController viewController) {
        this.viewController=(SplashActionsController) viewController;
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

    @Override
    public void startAnimation() {

    }

    /**
     * Authenticate anonymously on firebase
     */
    @Override
    public void startCommFirebase() {

        splashInteractor.execute();
    }
}
