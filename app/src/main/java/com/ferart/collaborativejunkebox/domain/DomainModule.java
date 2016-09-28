package com.ferart.collaborativejunkebox.domain;

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
    public Executor provideExecutor() {
        return new ThreadExecutor();
    }

    @Provides @Singleton
    MainThread provideMainThread() {
        return new MainThreadImpl();
    }
}
