package com.marvik.apps.jillfashions.models.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by victor on 7/29/2015.
 */
public abstract class ActivityWrapper extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActivityCreated(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onActivityResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        onActivityPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onActivityDestroy();
    }




    protected abstract void onActivityCreated(Bundle savedInstanceState);
    protected abstract void onActivityResume();
    protected abstract void onActivityPause();
    protected abstract void onActivityDestroy();
}
