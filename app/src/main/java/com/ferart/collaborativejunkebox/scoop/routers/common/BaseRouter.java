package com.ferart.collaborativejunkebox.scoop.routers.common;

import com.lyft.scoop.RouteChange;
import com.lyft.scoop.Router;
import com.lyft.scoop.ScreenScooper;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseRouter
 * <p/>
 */
public class BaseRouter extends Router implements IRouter {
    private List<IRouterScoopChangedObserver> routerScoopChangedObservers;

    public BaseRouter(ScreenScooper screenScooper) {
        super(screenScooper);
        this.routerScoopChangedObservers = new ArrayList<>();
    }

    @Override
    protected void onScoopChanged(RouteChange routeChange) {
        for (IRouterScoopChangedObserver observer : routerScoopChangedObservers) {
            observer.onScoopChanged(routeChange);
        }
    }

    @Override
    public void observe(IRouterScoopChangedObserver observer) {
        routerScoopChangedObservers.add(observer);
    }

    @Override
    public void stopObserving(IRouterScoopChangedObserver observer) {
        routerScoopChangedObservers.remove(observer);
    }
}
