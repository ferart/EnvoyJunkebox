package com.ferart.collaborativejunkebox.scoop.controllers.parties;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.ferart.collaborativejunkebox.ApplicationManager;

import com.ferart.collaborativejunkebox.MainActivity;
import com.ferart.collaborativejunkebox.R;
import com.ferart.collaborativejunkebox.presenters.partiesscreen.PartiesPresenter;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by ferar on 31/10/2016.
 */

public class PartiesController extends ViewController implements PartiesInTownActionsController, OnMapReadyCallback {



    @Inject
    PartiesPresenter partiesPresenter;

    private MapFragment partiesMap;
    private PartiesListAdapter partiesListAdapter;
    @Bind(R.id.parties_found_list)
    RecyclerView partiesList;


    @Override
    protected int layoutId() {
        return R.layout.parties_list;
    }

    @Override
    public void onAttach() {
        super.onAttach();
        ((ApplicationManager)getView().getContext().getApplicationContext()).getPresentationComponent().inject(this);
        partiesMap=(MapFragment)((MainActivity)((ApplicationManager)getView().getContext().getApplicationContext()).getActivityContext()).getFragmentManager().findFragmentById(R.id.parties_map);
        partiesMap.getMapAsync(this);

        partiesListAdapter=new PartiesListAdapter();
        partiesList.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        partiesList.setAdapter(partiesListAdapter);

    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onMapReady(GoogleMap partyMap) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(33.6340053, -117.7375235), 10);
        partyMap.animateCamera(cameraUpdate);
    }



}
