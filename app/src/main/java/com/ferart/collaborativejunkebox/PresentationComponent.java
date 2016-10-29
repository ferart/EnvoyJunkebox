package com.ferart.collaborativejunkebox;

import com.ferart.collaborativejunkebox.data.DataModule;
import com.ferart.collaborativejunkebox.data.fcm.messaging.FCMMessageDAOImpl;
import com.ferart.collaborativejunkebox.data.fcm.messaging.MessagingInstanceIDService;
import com.ferart.collaborativejunkebox.data.preferences.AccessPreferencesDAO;
import com.ferart.collaborativejunkebox.domain.DomainModule;
import com.ferart.collaborativejunkebox.domain.fcmtoken.FCMMessageInteractor;
import com.ferart.collaborativejunkebox.presenters.PresentersModule;
import com.ferart.collaborativejunkebox.presenters.mainscreen.MainScreenPresenter;
import com.ferart.collaborativejunkebox.presenters.splashscreen.SplashPresenter;
import com.ferart.collaborativejunkebox.scoop.ApplicationModule;
import com.ferart.collaborativejunkebox.scoop.containers.MainContainer;
import com.ferart.collaborativejunkebox.scoop.controllers.splash.SplashController;
import com.ferart.collaborativejunkebox.scoop.routers.RouterModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by root on 9/24/16.
 */
@Singleton
@Component(modules={ApplicationModule.class, RouterModule.class, PresentersModule.class, DomainModule.class, DataModule.class})
public interface PresentationComponent {
    /*Scoop injections
     */
    void inject(MainActivity mainActivity);
    void inject(MainContainer mainContainer);

    /*
     * MVP injections
     */

    /**
     *Presentation Layer
     */
    void inject(SplashController splashController);
    void inject(SplashPresenter splashPresenter);
    void inject(MainScreenPresenter mainScreenPresenter);

    /**
     * Domain Layer
     */
    void inject(FCMMessageInteractor fcmMessageInteractor);

    /**
     * Data Layer
     */
    void inject(AccessPreferencesDAO accessPreferences);
    void inject(MessagingInstanceIDService messagingInstanceIDService);
    void inject(FCMMessageDAOImpl fcmMessageDAOImpl);
}
