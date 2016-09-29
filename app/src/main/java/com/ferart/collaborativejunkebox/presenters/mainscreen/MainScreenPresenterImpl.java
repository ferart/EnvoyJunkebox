package com.ferart.collaborativejunkebox.presenters.mainscreen;

import android.view.View;

import com.ferart.collaborativejunkebox.scoop.routers.common.IRouter;
import com.ferart.collaborativejunkebox.scoop.screens.SplashScreen;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by root on 9/25/16.
 */

public class MainScreenPresenterImpl implements MainScreenPresenter {
    //mainRouter
    private IRouter mainRouter;

    @Inject
    public MainScreenPresenterImpl(IRouter mainRouter) {
        this.mainRouter=mainRouter;
    }


    @Override
    public void onResume() {
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

    }

    @Override
    public void onAttached(ViewController viewController) {

    }

    @Override
    public void onDetached() {

    }

}
