package com.ferart.collaborativejunkebox.scoop.controllers.parties;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ferart.collaborativejunkebox.ApplicationManager;

import com.ferart.collaborativejunkebox.MainActivity;
import com.ferart.collaborativejunkebox.R;
import com.ferart.collaborativejunkebox.model.Party;
import com.ferart.collaborativejunkebox.presenters.partiesscreen.PartiesPresenter;
import com.lyft.scoop.ViewController;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by ferar on 31/10/2016.
 */

public class PartiesController extends ViewController implements PartiesInTownActionsController, OnMapReadyCallback, GoogleMap.OnMarkerDragListener {



    @Inject
    PartiesPresenter partiesPresenter;
    @Bind(R.id.parties_found_list)
    RecyclerView partiesList;

    private MapFragment partiesMapFragment;
    private GoogleMap partiesMap;
    private PartiesListAdapter partiesListAdapter;

    private Map<String, Marker> user_postionsMap=new HashMap<String, Marker>();


    @Override
    protected int layoutId() {
        return R.layout.parties_list;
    }

    @Override
    public void onAttach() {
        super.onAttach();
        ((ApplicationManager)getView().getContext().getApplicationContext()).getPresentationComponent().inject(this);
        partiesPresenter.setPartiesViewController(this);

        partiesMapFragment =(MapFragment)((MainActivity)((ApplicationManager)getView().getContext().getApplicationContext()).getActivityContext()).getFragmentManager().findFragmentById(R.id.parties_map);
        partiesMapFragment.getMapAsync(this);

        partiesListAdapter=new PartiesListAdapter();
        partiesList.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        partiesList.setAdapter(partiesListAdapter);
    }




    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onMapReady(GoogleMap partiesMap) {
        this.partiesMap =partiesMap;
        double latitude = 33.6340053;
        double longitude = -117.7375235;
        float radius = 2; //km


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 11);
        this.partiesMap.animateCamera(cameraUpdate);
        Circle circle = this.partiesMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(radius*1000) //in meters
                .strokeWidth(10)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(128, 255, 0, 0))
                .clickable(false));


        partiesPresenter.findPartiesInMyCurrentLocation(latitude,longitude,radius); //starts GeoQuery
    }

    @Override
    public void showMarker(Party party) {
        if (partiesMap==null){
            return;
        }
        Marker marker=user_postionsMap.get(party.getUserUID());
        if (marker==null) {
            marker = partiesMap.addMarker(new MarkerOptions()
                    .position(new LatLng(party.getLatitude(), party.getLongitude()))
                    .draggable(true)
                    .title(party.getName()));
            user_postionsMap.put(party.getUserUID(), marker);
        }else{
            //marker already added
        }

    }

    @Override
    public void moveMarker(Party party) {
        if (partiesMap==null){
            return;
        }
        Marker marker=user_postionsMap.get(party.getUserUID());
        if (marker!=null){
            marker.setPosition(new LatLng(party.getLatitude(),party.getLongitude()));
        }

    }

    @Override
    public void removeMarker(String partyUserUID) {
        if (partiesMap==null){
            return;
        }
        Marker marker=user_postionsMap.get(partyUserUID);
        if (marker!=null){
            marker.remove();
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void addPartyToList(Party party) {

    }
}
