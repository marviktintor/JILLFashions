package com.marvik.apps.jillfashions.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.marvik.apps.jillfashions.R;

/**
 * Created by victor on 7/29/2015.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(getApplicationContext(),Orders.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();;
    }



  private void test(){

  }
}
