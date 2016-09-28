package com.ferart.collaborativejunkebox.scoop.routers;

import com.ferart.collaborativejunkebox.scoop.routers.common.IRouter;
import com.lyft.scoop.ScreenScooper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by root on 9/24/16.
 */
@Module
public class RouterModule {

    @Provides
    @Singleton
    @Named("mainRouter")
    public IRouter providesMainRouter() {
        return new MainRouter(new ScreenScooper());
    }
}
