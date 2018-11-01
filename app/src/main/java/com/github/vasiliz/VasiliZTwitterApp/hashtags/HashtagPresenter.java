package com.github.vasiliz.VasiliZTwitterApp.hashtags;

import com.github.vasiliz.VasiliZTwitterApp.hashtags.events.HashtagEvents;
import com.github.vasiliz.VasiliZTwitterApp.images.events.ImagesEvent;

public interface HashtagPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagEvents pHashtagEvents);

}
