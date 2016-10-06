package com.ferart.collaborativejunkebox.presenters.mainscreen;

import android.view.View;

import com.ferart.collaborativejunkebox.domain.sesion.SessionInteractor;
import com.ferart.collaborativejunkebox.scoop.routers.common.IRouter;
import com.ferart.collaborativejunkebox.scoop.screens.SplashScreen;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by root on 9/25/16.
 * Main presenter, is in charge of controlling lifecycle of the MainActivity
 * dispatch the UI flow to the controllers, each controller is related to its own presenter
 *
 */

public class MainScreenPresenterImpl implements MainScreenPresenter {
    //mainRouter
    private IRouter mainRouter;
    private SessionInteractor sessionInteractor;


    @Inject
    public MainScreenPresenterImpl(IRouter mainRouter, SessionInteractor sessionInteractor) {
        this.mainRouter=mainRouter;
        this.sessionInteractor=sessionInteractor;
        sessionInteractor.addAuthStateListener();
        sessionInteractor.siginAnonymously();
    }


    @Override
    public void onResume() {
        //todo: check for google play services
        if (!mainRouter.hasActiveScreen()){
            mainRouter.goTo(new SplashScreen());
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onBackPress() {

    }

    @Override
    public void onDestroy() {
        sessionInteractor.removeAuthStateListener();
    }

    @Override
    public void onAttached(ViewController viewController) {

    }

    @Override
    public void onDetached() {

    }

    @Override
    public void authFailure() {
        //handle auth failure
    }
}
