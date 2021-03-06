package com.ferart.collaborativejunkebox;

import android.app.Application;
import android.content.Context;

import com.ferart.collaborativejunkebox.diutils.ButterKnifeViewBinder;
import com.ferart.collaborativejunkebox.scoop.ApplicationModule;
//import com.ferart.collaborativejunkebox.scoop.DaggerPresentationComponent;
import com.lyft.scoop.Scoop;

/**
 * Created by root on 9/24/16.
 */
public class ApplicationManager extends Application {

    private PresentationComponent presentationComponent;

    private Context activityContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Scoop.setViewBinder(new ButterKnifeViewBinder());
        presentationComponent= DaggerPresentationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public PresentationComponent getPresentationComponent() {
        return presentationComponent;
    }

    public Context getActivityContext() {
        return activityContext;
    }

    public void setActivityContext(Context activityContext) {
        this.activityContext = activityContext;
    }
}
