package com.colouredtulips.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.colouredtulips.ActionResolver;
import com.colouredtulips.Main;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class AndroidLauncher extends AndroidApplication implements ActionResolver{
    MyApplication myApplication;
    Tracker tracker;
    Tracker globalTracker;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new Main(this), config);

        myApplication = (MyApplication) getApplication();
        tracker = myApplication.getTracker(MyApplication.TrackerName.APP_TRACKER);
        globalTracker = myApplication.getTracker(MyApplication.TrackerName.GLOBAL_TRACKER);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    public void setTrackerScreenName(String path) {
        // Set screen name.
        // Where path is a String representing the screen name.
        globalTracker.setScreenName(path);
        globalTracker.send(new HitBuilders.AppViewBuilder().build());
    }
}
