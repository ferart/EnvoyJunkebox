package com.ferart.collaborativejunkebox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.ferart.collaborativejunkebox.presenters.mainscreen.MainScreenPresenter;
import com.lyft.scoop.Scoop;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private Scoop rootScoop;

    @Inject
    MainScreenPresenter mainScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ApplicationManager)getApplicationContext()).getPresentationComponent().inject(this);
        //adding first scoop as root, required by framework
        rootScoop = new Scoop.Builder("root").build();
        rootScoop.inflate(R.layout.root_main_containers, (ViewGroup) findViewById(R.id.main), true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainScreenPresenter.onResume();
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
}
