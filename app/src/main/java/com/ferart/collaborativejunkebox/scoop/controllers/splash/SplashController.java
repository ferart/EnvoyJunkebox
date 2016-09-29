package com.ferart.collaborativejunkebox.scoop.controllers.splash;

import com.ferart.collaborativejunkebox.ApplicationManager;
import com.ferart.collaborativejunkebox.R;
import com.ferart.collaborativejunkebox.presenters.splashscreen.SplashPresenter;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by root on 9/24/16.
 */
public class SplashController extends ViewController implements SplashActionsController {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected int layoutId() {
        return R.layout.splash_screen_junkebox;
    }

    @Override
    public void onAttach() {
        super.onAttach();
        ((ApplicationManager)getView().getContext().getApplicationContext()).getPresentationComponent().inject(this);
        splashPresenter.onAttached(this);
        startSplash();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void startSplash() {
        splashPresenter.startAnimation();
        splashPresenter.startCommFirebase();
    }

    @Override
    public void endSplash() {

    }
}
