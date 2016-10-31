package com.ferart.collaborativejunkebox.domain.session;

import com.ferart.collaborativejunkebox.presenters.mainscreen.MainScreenPresenter;

/**
 * Created by ferar on 01/10/2016.
 * not worker thread for this interactor
 */

public interface SessionInteractor {
    void addAuthStateListener();
    void removeAuthStateListener();
    void signinAnonymously();
    void injectMainPresenter(MainScreenPresenter mainScreenPresenter);
}
