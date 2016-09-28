package com.ferart.collaborativejunkebox.presenters.common;


import android.view.View;

import com.lyft.scoop.ViewController;

/**
 * Created by root on 9/25/16.
 * Base presenter to implement on all presenters,
 * this simplified lifecycle can be used by the MainActivity and/or Controllers
 */

public  interface BasePresenter {
    /*Simplified lifecycle of the activity or fragment
     */
    public  void onAttached(ViewController viewController);
    public  void onDetached();
    public  void onResume();
    public  void onPause();
    public  void onBackPress();
    public  void onDestroy();

}
