package com.ferart.collaborativejunkebox.data.fcm.database;

import com.ferart.collaborativejunkebox.data.SimpleCallback;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ferart.collaborativejunkebox.model.Party;

import android.util.Log;

/**
 * Created by Envoy on 10/27/16.
 */

public class JukeboxFirebaseDAOImpl implements JukeboxDBDAO {

    private static final String TAG=JukeboxFirebaseDAOImpl.class.getName();

    private DatabaseReference databaseReference;
    private GeoFire geoFire;

    public JukeboxFirebaseDAOImpl() {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        this.databaseReference = firebaseDatabase.getReference();
        this.geoFire= new GeoFire(firebaseDatabase.getReference("/party_location/"));
    }

    @Override
    public void writeNewParty(final String userUID, String name, String hostToken,final GeoLocation geoLocation,  SimpleCallback simpleCallback) {
        Party party=new Party(userUID,name,hostToken);
        databaseReference.child("party").child(userUID).setValue(party, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                writePartyLocation(userUID,geoLocation, simpleCallback);
            }
        });
    }

    @Override
    public void writePartyLocation(String userUID,GeoLocation geoLocation, SimpleCallback simpleCallback) {
        geoFire.setLocation(userUID, geoLocation, new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (simpleCallback==null){
                    return;
                }

                if (error!=null){
                   simpleCallback.onError();
                    return;
                }
                simpleCallback.onSuccess();
            }
        });
    }

    @Override
    public void getParty(String userUID, ValueEventListener valueEventListener) {
        databaseReference.child("party").child(userUID).addListenerForSingleValueEvent(valueEventListener);
    }

    @Override
    public void updateHostToken(String userUID, String hostToken) {
        databaseReference.child("party").child(userUID).child("hostToken").setValue(hostToken);
    }

    @Override
    public void deleteParty(final String userUID, SimpleCallback simpleCallback) {
        databaseReference.child("party").child(userUID).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                deletePartyLocation(userUID, simpleCallback);
            }
        });
    }

    @Override
    public void deletePartyLocation(String userUID, SimpleCallback simpleCallback) {
        geoFire.removeLocation(userUID, new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error!=null){
                    simpleCallback.onError();
                    return;
                }
                simpleCallback.onSuccess();
            }
        });
    }

    @Override
    public GeoQuery createLocationQuery(GeoLocation geoLocation, float radius) {
        return geoFire.queryAtLocation(geoLocation, radius);
    }

    @Override
    public void addLocationQueryListener(GeoQuery geoQuery, PartyLocationCallback partyLocationCallback) {
            geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                @Override
                public void onKeyEntered(String key, GeoLocation location) {
                    getParty(key, new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                Party party = dataSnapshot.getValue(Party.class);
                                party.setUserUID(key);
                                party.setLatitude(location.latitude);
                                party.setLongitude(location.longitude);
                                partyLocationCallback.partyFoundInLocation(party);
                            }else{
                                partyLocationCallback.partyNotFoundError();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            partyLocationCallback.partyNotFoundError();
                        }
                    });
                }

                @Override
                public void onKeyExited(String key) {
                    getParty(key, new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Party party =dataSnapshot.getValue(Party.class);
                            party.setUserUID(key);
                            partyLocationCallback.partyExitedLocation(party);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            partyLocationCallback.partyNotFoundError();
                        }
                    });
                }

                @Override
                public void onKeyMoved(String key, GeoLocation location) {
                    getParty(key, new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Party party =dataSnapshot.getValue(Party.class);
                            partyLocationCallback.partyLocationMoved(party, location);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            partyLocationCallback.partyNotFoundError();
                        }
                    });
                }

                @Override
                public void onGeoQueryReady() {

                }

                @Override
                public void onGeoQueryError(DatabaseError error) {

                }
            });
    }

    @Override
    public void removeLocationQueryListener(GeoQuery geoQuery) {
        geoQuery.removeAllListeners();
    }

    /**
     * some data changed for this userUID
     * @param userUID
     */
    @Override
    public void followPartyUpdates(String userUID) {
        databaseReference.child("party").child(userUID).addValueEventListener(partyIBelongListener);
    }

    @Override
    public void unfollowPartyUpdates(String userUID) {
        databaseReference.child("party").child(userUID).removeEventListener(partyIBelongListener);
    }

    ValueEventListener partyIBelongListener=new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.i(TAG,"party i belong has change");
            Party party=dataSnapshot.getValue(Party.class);
            //todo save party data internally
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.i(TAG,"party i belong changed but there was an erro getting the info");
        }
    };
}
