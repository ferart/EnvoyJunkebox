package com.ferart.collaborativejunkebox.domain.thread;

import com.ferart.collaborativejunkebox.domain.common.Interactor;

/**
 * Created by root on 9/25/16.
 */

public interface Executor {
    void run(final Interactor interactor);
}
