package com.ferart.collaborativejunkebox.scoop.containers.common;

import android.content.Context;
import android.util.AttributeSet;

import com.ferart.collaborativejunkebox.scoop.routers.common.IRouter;
import com.ferart.collaborativejunkebox.scoop.routers.common.IRouterScoopChangedObserver;
import com.lyft.scoop.RouteChange;
import com.lyft.scoop.UiContainer;

/**
 * Created by root on 9/24/16.
 */
public class BaseContainer extends UiContainer {

    IRouter router;
    IRouterScoopChangedObserver observer;

    public BaseContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        router.stopObserving(observer);
    }

    public void setRouter(IRouter router) {
        this.router = router;
    }

    public void setObserver(IRouterScoopChangedObserver observer) {
        this.observer = observer;
    }

    public  void  attachObserver(IRouter router,IRouterScoopChangedObserver observer) {
      router.observe(observer);
    }
}
