package com.ferart.collaborativejunkebox.presenters.splashscreen;

import com.ferart.collaborativejunkebox.scoop.controllers.splash.SplashActionsController;
import com.ferart.collaborativejunkebox.scoop.controllers.splash.SplashController;
import com.lyft.scoop.ViewController;

/**
 * Created by root on 9/25/16.
 */

public class SplashPresenterImpl implements SplashPresenter {
    private SplashActionsController viewController;

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

    @Override
    public void startCommFirebase() {

    }
}
