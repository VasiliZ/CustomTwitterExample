package com.github.vasiliz.VasiliZTwitterApp.hashtags;

import com.github.vasiliz.VasiliZTwitterApp.hashtags.events.HashtagEvents;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.HashtagsView;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

public class HashtagPresenterImpl implements HashtagPresenter {

    private HashtagsView mHashtagsView;
    private EventBus mEventBus;
    private HashtagInteracror mHashtagInteracror;

    public HashtagPresenterImpl(HashtagsView pHashtagsView, EventBus pEventBus, HashtagInteracror pHashtagInteracror) {
        mHashtagsView = pHashtagsView;
        mEventBus = pEventBus;
        mHashtagInteracror = pHashtagInteracror;
    }

    @Override
    public void onResume() {
        mEventBus.register(this);
    }

    @Override
    public void onPause() {
        mEventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
            mHashtagsView = null;
    }

    @Override
    public void getHashtagTweets() {
        if (mHashtagsView != null){
            mHashtagsView.hideHashtags();
            mHashtagsView.showProgress();
        }
        mHashtagInteracror.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagEvents pHashtagEvents) {
            String errMsg = pHashtagEvents.getError();
            if (mHashtagsView != null){
                mHashtagsView.showHashtags();
                mHashtagsView.hideProgress();
                if (errMsg!=null){
                    mHashtagsView.onError(errMsg);
                }else {
                    mHashtagsView.setHashtags(pHashtagEvents.getHashTags());
                }
            }
    }
}
