package com.mv2studio.amarokontrol;

import android.app.Application;

import com.bumptech.glide.Glide;

/**
 * Created by matej on 22/10/2016.
 */

public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // do not rely on glide's lazy initialisation. Do it straight away.
        Glide.get(this);
    }

    public static App getAppContext() {
        return sInstance;
    }
}
