package com.ferart.collaborativejunkebox.scoop.containers;

import android.content.Context;
import android.util.AttributeSet;

import com.ferart.collaborativejunkebox.ApplicationManager;
import com.ferart.collaborativejunkebox.scoop.containers.common.BaseContainer;
import com.ferart.collaborativejunkebox.scoop.routers.common.IRouter;
import com.ferart.collaborativejunkebox.scoop.routers.common.IRouterScoopChangedObserver;
import com.lyft.scoop.RouteChange;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by root on 9/24/16.
 */
public class MainContainer extends BaseContainer {


    @Inject
    @Named("mainRouter")
    IRouter mainRouter;

    public MainContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((ApplicationManager)context.getApplicationContext()).getPresentationComponent().inject(this);
        attachObserver(mainRouter,observer);
    }

    IRouterScoopChangedObserver observer=(RouteChange routeChange)-> goTo(routeChange);


}
