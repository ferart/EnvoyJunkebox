package com.ferart.collaborativejunkebox.scoop;

import com.ferart.collaborativejunkebox.MainActivity;
import com.ferart.collaborativejunkebox.presenters.PresentersModule;
import com.ferart.collaborativejunkebox.scoop.containers.MainContainer;
import com.ferart.collaborativejunkebox.scoop.controllers.splash.SplashController;
import com.ferart.collaborativejunkebox.scoop.routers.RouterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by root on 9/24/16.
 */
@Singleton
@Component(modules={RouterModule.class, PresentersModule.class})
public interface PresentationComponent {
    /*Scoop injections
     */
    void inject(MainActivity mainActivity);
    void inject(MainContainer mainContainer);

    /*
     * MVP injections
     */
    void inject(SplashController splashController);
}
