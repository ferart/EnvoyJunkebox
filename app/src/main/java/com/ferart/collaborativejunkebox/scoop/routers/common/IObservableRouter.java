package com.ferart.collaborativejunkebox.scoop.routers.common;

/**
 * IObservableRouter
 * <p/>
 */
public interface IObservableRouter {
    void observe(IRouterScoopChangedObserver observer);

    void stopObserving(IRouterScoopChangedObserver observer);
}
