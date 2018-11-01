package com.github.vasiliz.VasiliZTwitterApp;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.github.vasiliz.BuildConfig;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.di.DaggerHashtagComponent;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.di.HashtagComponent;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.di.HashtagModule;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.HashtagsView;
import com.github.vasiliz.VasiliZTwitterApp.images.adapters.OnItemClickListener;
import com.github.vasiliz.VasiliZTwitterApp.images.di.DaggerImagesComponent;
import com.github.vasiliz.VasiliZTwitterApp.images.di.ImagesComponent;
import com.github.vasiliz.VasiliZTwitterApp.images.di.ImagesModule;
import com.github.vasiliz.VasiliZTwitterApp.images.ui.ImageView;
import com.github.vasiliz.VasiliZTwitterApp.lib.di.LibsModule;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

public class MyTwitterApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        TwitterConfig authConfig = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(authConfig);
    }

    public ImagesComponent getImagesComponent(Fragment pFragment, ImageView pImageView, OnItemClickListener pOnItemClickListener){
        return DaggerImagesComponent.builder()
                .libsModule(new LibsModule(pFragment))
                .imagesModule(new ImagesModule(pImageView, pOnItemClickListener))
                .build();
    }

    public HashtagComponent getHashtagComponent(HashtagsView pHashtagsView, com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.adapters.OnItemClickListener pOnItemClickListener){
        return DaggerHashtagComponent.builder()
                .libsModule(new LibsModule(null))
                .hashtagModule(new HashtagModule(pHashtagsView, pOnItemClickListener))
                .build();
    }
}
