package com.ferart.collaborativejunkebox.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by ferart on 10/28/16.
 */
@IgnoreExtraProperties
public class Party {

    private String userUID;
    private String name;
    private String hostToken;
    private float latitude;
    private float longitude;
    private Map<String, List<String>> playlist; //each streaming service is an entry in the map, each entry has a playlist

    public Party() {
    }

    public Party(String userUID, String name, String hostToken) {
        this.userUID = userUID;
        this.name = name;
        this.hostToken = hostToken;
    }


    private String getUserUID() {
        return userUID;
    }

    private void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getHostToken() {
        return hostToken;
    }

    private void setHostToken(String hostToken) {
        this.hostToken = hostToken;
    }

    private float getLatitude() {
        return latitude;
    }

    private void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    private float getLongitude() {
        return longitude;
    }

    private void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    private Map<String, List<String>> getPlaylist() {
        return playlist;
    }

    private void setPlaylist(Map<String, List<String>> playlist) {
        this.playlist = playlist;
    }
}
