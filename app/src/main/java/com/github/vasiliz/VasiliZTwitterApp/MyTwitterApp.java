package com.github.vasiliz.VasiliZTwitterApp;

import android.app.Application;
import android.util.Log;

import com.github.vasiliz.BuildConfig;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import io.fabric.sdk.android.Fabric;

public class MyTwitterApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterConfig authConfig = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(authConfig);
    }
}
