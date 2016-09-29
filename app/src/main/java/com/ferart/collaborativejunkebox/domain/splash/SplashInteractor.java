package com.ferart.collaborativejunkebox.domain.splash;

import android.util.Log;

import com.ferart.collaborativejunkebox.domain.common.BaseInteractor;
import com.ferart.collaborativejunkebox.domain.thread.Executor;
import com.ferart.collaborativejunkebox.domain.thread.MainThread;

/**
 * Created by ferar on 27/09/2016.
 */

public class SplashInteractor extends BaseInteractor {

    private static final String TAG="SplashInteractorImpl";

    public SplashInteractor(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    @Override
    public void run() {
        Log.i(TAG,"executing anonymus auth to firebase");
    }

}
