package com.ferart.collaborativejunkebox;

import android.*;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.ferart.collaborativejunkebox.presenters.mainscreen.MainScreenPresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.lyft.scoop.Scoop;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_FINE_LOCATION=1;

    private Scoop rootScoop;

    @Inject
    MainScreenPresenter mainScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ApplicationManager)getApplicationContext()).getPresentationComponent().inject(this);
        ((ApplicationManager)getApplicationContext()).setActivityContext(MainActivity.this);
        //adding first scoop as root, required by framework
        rootScoop = new Scoop.Builder("root").build();
        rootScoop.inflate(R.layout.root_main_containers, (ViewGroup) findViewById(R.id.main), true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPlayServicesAvailable()){
            if (isPermissionGrantedToFunction()) {
                mainScreenPresenter.onResume();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rootScoop.destroy(); // Good practice
        mainScreenPresenter.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private boolean isPermissionGrantedToFunction(){

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
//            mMap.setMyLocationEnabled(true);
        } else {
            // request permission.
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        mainScreenPresenter.onResume();
                } else {
                    //show dialog to user why the app needs this permission to function
                }
                return;
            }
        }
    }

    private boolean isPlayServicesAvailable(){
        GoogleApiAvailability googleApiAvailability=GoogleApiAvailability.getInstance();
        // Getting status
        int status = googleApiAvailability.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status== ConnectionResult.SUCCESS)
            return true;
        else{
            int requestCode = 10;
            Dialog dialog = googleApiAvailability.getErrorDialog(MainActivity.this,status, requestCode);
            dialog.show();
            return false;
        }
    }
}
