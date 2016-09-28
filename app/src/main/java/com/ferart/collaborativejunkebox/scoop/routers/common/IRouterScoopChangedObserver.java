package com.ferart.collaborativejunkebox.scoop.routers.common;

import com.lyft.scoop.RouteChange;

/**
 * IRouterScoopChangedObserver
 * <p/>
 */
public interface IRouterScoopChangedObserver {
    void onScoopChanged(RouteChange routeChange);
}
