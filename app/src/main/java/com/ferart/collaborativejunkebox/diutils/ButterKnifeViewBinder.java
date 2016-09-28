package com.ferart.collaborativejunkebox.diutils;

import android.view.View;

import com.lyft.scoop.ViewBinder;

import butterknife.ButterKnife;

/**
 * Created by root on 9/24/16.
 */
public class ButterKnifeViewBinder implements ViewBinder {
    @Override
    public void bind(Object object, View view) {
        ButterKnife.bind(object, view);
    }

    @Override
    public void unbind(Object object) {
        ButterKnife.unbind(object);
    }
}
