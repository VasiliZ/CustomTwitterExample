package com.github.vasiliz.VasiliZTwitterApp.hashtags.di;

import com.github.vasiliz.VasiliZTwitterApp.api.CustomTwitterApiClient;
import com.github.vasiliz.VasiliZTwitterApp.entities.HashTag;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagInteracror;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagInteractorImpl;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagPresenter;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagPresenterImpl;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagRepository;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagrepositoryImpl;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.HashtagsView;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.adapters.HashtagAdapter;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.adapters.OnItemClickListener;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.EventBus;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HashtagModule {

    private HashtagsView mHashtagsView;
    private OnItemClickListener mOnItemClickListener;

    public HashtagModule(HashtagsView pHashtagsView, OnItemClickListener pOnItemClickListener) {
        mHashtagsView = pHashtagsView;
        mOnItemClickListener = pOnItemClickListener;
    }

    @Provides
    @Singleton
    HashtagAdapter provideHashtagAdapter(List<HashTag> pHashTags, OnItemClickListener pOnItemClickListener) {
        return new HashtagAdapter(pHashTags, pOnItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    @Provides
    @Singleton
    List<HashTag> providesItemList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    HashtagPresenter providesHashtagPresenter(HashtagsView pHashtagsView, EventBus pEventBus, HashtagInteracror pHashtagInteracror) {
        return new HashtagPresenterImpl(pHashtagsView, pEventBus, pHashtagInteracror);
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return this.mHashtagsView;
    }

    @Provides
    @Singleton
    HashtagInteracror providesHashtagInteracror(HashtagRepository pHashtagRepository) {
        return new HashtagInteractorImpl(pHashtagRepository);
    }

    @Provides
    @Singleton
    HashtagRepository providesHashtagRepository(EventBus pEventBus, CustomTwitterApiClient pCustomTwitterApiClient) {
        return new HashtagrepositoryImpl(pEventBus, pCustomTwitterApiClient);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session pSession) {
        return new CustomTwitterApiClient(pSession);
    }

    @Provides
    @Singleton
    Session providesSession() {
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }

}
