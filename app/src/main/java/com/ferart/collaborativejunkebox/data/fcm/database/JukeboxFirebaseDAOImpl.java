package com.ferart.collaborativejunkebox.data.fcm.database;

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

    private JukeboxFirebaseDAOImpl() {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        this.databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void writeNewParty(String userUID, String name, String hostToken) {
        Party party=new Party(userUID,name,hostToken);
        databaseReference.child("party").child(userUID).setValue(party);
    }

    @Override
    public void updateHostToken(String userUID, String hostToken) {
        databaseReference.child("party").child(userUID).child("hostToken").setValue(hostToken);
    }

    @Override
    public void deleteParty(String userUID) {
        databaseReference.child("party").child(userUID).removeValue();
    }

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
