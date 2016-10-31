package com.ferart.collaborativejunkebox.domain;

import com.ferart.collaborativejunkebox.data.fcm.database.JukeboxDBDAO;
import com.ferart.collaborativejunkebox.data.fcm.messaging.FCMMessageDAO;
import com.ferart.collaborativejunkebox.domain.fcmtoken.FCMMessageInteractor;
import com.ferart.collaborativejunkebox.domain.partymanager.CreatePartyInteractor;
import com.ferart.collaborativejunkebox.domain.partymanager.PartyLocationTrackerInteractor;
import com.ferart.collaborativejunkebox.domain.session.SessionInteractor;
import com.ferart.collaborativejunkebox.domain.session.SessionInteractorImpl;
import com.ferart.collaborativejunkebox.domain.splash.SplashInteractor;
import com.ferart.collaborativejunkebox.domain.thread.Executor;
import com.ferart.collaborativejunkebox.domain.thread.MainThread;
import com.ferart.collaborativejunkebox.domain.thread.MainThreadImpl;
import com.ferart.collaborativejunkebox.domain.thread.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by root on 9/25/16.
 */
@Module
public class DomainModule {
    @Provides
    @Singleton
    public Executor providesExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    MainThread providesMainThread() {
        return new MainThreadImpl();
    }

    @Provides
    SplashInteractor providesSplashInteractor(Executor executor, MainThread mainThread){
        return new SplashInteractor(executor,mainThread);
    }
    @Provides
    SessionInteractor providesSessionInteractor(Executor executor, MainThread mainThread){
        return new SessionInteractorImpl(executor,mainThread);
    }

    @Provides
    FCMMessageInteractor providesFcmMessageInteractor(Executor executor, MainThread mainThread, FCMMessageDAO fcmMessageDAO){
        return new FCMMessageInteractor(executor,mainThread,fcmMessageDAO);
    }

    @Provides
    CreatePartyInteractor providesCreatePartyInteractor(Executor executor, MainThread mainThread, JukeboxDBDAO jukeboxDBDAO){
        return new CreatePartyInteractor(executor,mainThread, jukeboxDBDAO);
    }

    @Provides
    @Singleton
    PartyLocationTrackerInteractor providesPartyLocationTrackerInteractor(Executor executor, MainThread mainThread, JukeboxDBDAO jukeboxDBDAO){
        return new PartyLocationTrackerInteractor(executor, mainThread, jukeboxDBDAO);
    }
}
