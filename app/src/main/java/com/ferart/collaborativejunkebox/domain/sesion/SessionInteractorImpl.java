package com.ferart.collaborativejunkebox.domain.sesion;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.ferart.collaborativejunkebox.domain.common.BaseInteractor;
import com.ferart.collaborativejunkebox.domain.thread.Executor;
import com.ferart.collaborativejunkebox.domain.thread.MainThread;
import com.ferart.collaborativejunkebox.domain.thread.ThreadExecutor;
import com.ferart.collaborativejunkebox.presenters.mainscreen.MainScreenPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ferar on 01/10/2016.
 */

public class SessionInteractorImpl extends BaseInteractor implements SessionInteractor {

    private static final String TAG=SessionInteractorImpl.class.getName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private MainScreenPresenter mainScreenPresenter;


    public SessionInteractorImpl(Executor executor, MainThread mainThread) {
        super(executor,mainThread);
        this.mAuth = FirebaseAuth.getInstance();
        mAuthListener=sessionAuthListener;
    }

    @Override
    public void addAuthStateListener() {
        if (mAuthListener != null) {
            Log.d(TAG, "Aut listener added");
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void removeAuthStateListener() {
        if (mAuthListener != null) {
            Log.d(TAG, "Aut listener removed");
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    @Override
    public void signinAnonymously() {
        //Task task = RemoteConfig.getInstance().fetch();
        mAuth.signInAnonymously().addOnCompleteListener(((ThreadExecutor)executor).getThreadPoolExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInAnonymously", task.getException());
                            postOnMainThread(()-> mainScreenPresenter.authFailure());
                        }


                    }
                });
    }

    @Override
    public void injectMainPresenter(MainScreenPresenter mainScreenPresenter) {
        this.mainScreenPresenter=mainScreenPresenter;
    }

    private FirebaseAuth.AuthStateListener sessionAuthListener=new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }

        }
    };

    @Override
    public void run() {

    }
}
