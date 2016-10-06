package com.ferart.collaborativejunkebox.domain.common;

import com.ferart.collaborativejunkebox.domain.thread.Executor;
import com.ferart.collaborativejunkebox.domain.thread.MainThread;

import javax.inject.Inject;

/**
 * Created by root on 9/25/16.
 */

public abstract  class BaseInteractor implements Interactor {
    protected final Executor executor;
    private final MainThread mainThread;


    public BaseInteractor(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }

    public abstract void run();

    public void execute() {
        this.executor.run(this);
    }

    protected final void postOnMainThread(final Runnable runnable) {
        mainThread.post(runnable);
    }


}
