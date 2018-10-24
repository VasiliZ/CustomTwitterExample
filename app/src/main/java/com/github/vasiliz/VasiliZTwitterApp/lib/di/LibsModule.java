package com.github.vasiliz.VasiliZTwitterApp.lib.di;

import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.github.vasiliz.VasiliZTwitterApp.lib.GlideImageLoader;
import com.github.vasiliz.VasiliZTwitterApp.lib.GreenRobotEventBus;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.EventBus;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LibsModule {

    private Fragment mFragment;

    public LibsModule(Fragment pFragment) {
        mFragment = pFragment;
    }

    @Provides
    @Singleton
    EventBus provideEventBus(org.greenrobot.eventbus.EventBus pEventBus) {
        return new GreenRobotEventBus(pEventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibryaryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager pRequestManager) {
        return new GlideImageLoader(pRequestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment pFragment) {
        return Glide.with(pFragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.mFragment;
    }
}
