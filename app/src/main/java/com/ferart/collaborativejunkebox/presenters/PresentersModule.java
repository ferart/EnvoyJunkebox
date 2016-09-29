package com.ferart.collaborativejunkebox.presenters;

import com.ferart.collaborativejunkebox.domain.splash.SplashInteractor;
import com.ferart.collaborativejunkebox.presenters.mainscreen.MainScreenPresenterImpl;
import com.ferart.collaborativejunkebox.presenters.mainscreen.MainScreenPresenter;
import com.ferart.collaborativejunkebox.presenters.splashscreen.SplashPresenter;
import com.ferart.collaborativejunkebox.presenters.splashscreen.SplashPresenterImpl;
import com.ferart.collaborativejunkebox.scoop.routers.common.IRouter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by root on 9/25/16.
 */
@Module
public class PresentersModule {

    @Provides
    @Singleton
    public MainScreenPresenter providesFirebasePresenter(@Named("mainRouter") IRouter mainRouter){
        return new MainScreenPresenterImpl(mainRouter);
    }

    @Provides
    @Singleton
    public SplashPresenter providesSplashPresenter(SplashInteractor splashInteractor){
        return new SplashPresenterImpl(splashInteractor);
    }
}
