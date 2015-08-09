package com.marvik.apps.jillfashions.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.marvik.apps.jillfashions.R;
import com.marvik.apps.jillfashions.fragments.FragmentClientOrders;
import com.marvik.apps.jillfashions.models.ActivityWrapper;

/**
 * Created by victor on 8/9/2015.
 */
public class Orders extends ActivityWrapper{
    private FrameLayout parentContainer;
    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        setContentView(R.layout.activity_orders);
        init();
    }

    @Override
    protected void onActivityResume() {

    }

    @Override
    protected void onActivityPause() {

    }

    @Override
    protected void onActivityDestroy() {

    }



    private void init(){
        parentContainer = (FrameLayout)findViewById(R.id.activity_orders_frameLayout_parent_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_orders_frameLayout_parent_container,new FragmentClientOrders()).commit();
    }
}
