package com.ferart.collaborativejunkebox.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by ferart on 10/28/16.
 */
@IgnoreExtraProperties
public class Party {


    private String name;
    private String hostToken;
    private double latitude;
    private double longitude;
    private String userUID;
    private Map<String, List<String>> playlist; //each streaming service is an entry in the map, each entry has a playlist

    public Party() {
    }

    public Party(String userUID, String name, String hostToken) {
        this.userUID = userUID;
        this.name = name;
        this.hostToken = hostToken;
    }

    @Exclude
    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostToken() {
        return hostToken;
    }

    public void setHostToken(String hostToken) {
        this.hostToken = hostToken;
    }

    @Exclude
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Exclude
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Exclude
    public Map<String, List<String>> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Map<String, List<String>> playlist) {
        this.playlist = playlist;
    }
}
