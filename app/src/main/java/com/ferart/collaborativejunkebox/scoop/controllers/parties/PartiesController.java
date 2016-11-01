package com.ferart.collaborativejunkebox.scoop.controllers.parties;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.ferart.collaborativejunkebox.ApplicationManager;

import com.ferart.collaborativejunkebox.presenters.partiesscreen.PartiesPresenter;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

/**
 * Created by ferar on 31/10/2016.
 */

public class PartiesController extends ViewController implements PartiesInTownActionsController {



    @Inject
    PartiesPresenter partiesPresenter;

    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    public void onAttach() {
        super.onAttach();
        ((ApplicationManager)getView().getContext().getApplicationContext()).getPresentationComponent().inject(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
