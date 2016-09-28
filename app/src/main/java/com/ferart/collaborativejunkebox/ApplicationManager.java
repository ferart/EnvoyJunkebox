package com.ferart.collaborativejunkebox;

import android.app.Application;

import com.ferart.collaborativejunkebox.diutils.ButterKnifeViewBinder;
import com.ferart.collaborativejunkebox.scoop.DaggerPresentationComponent;
import com.ferart.collaborativejunkebox.scoop.PresentationComponent;
import com.lyft.scoop.Scoop;

/**
 * Created by root on 9/24/16.
 */
public class ApplicationManager extends Application {

    private PresentationComponent presentationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Scoop.setViewBinder(new ButterKnifeViewBinder());
        presentationComponent= DaggerPresentationComponent.builder().build();
    }

    public PresentationComponent getPresentationComponent() {
        return presentationComponent;
    }

}
