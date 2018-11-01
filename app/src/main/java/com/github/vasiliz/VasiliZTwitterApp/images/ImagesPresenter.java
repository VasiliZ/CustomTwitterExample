package com.github.vasiliz.VasiliZTwitterApp.images;

import com.github.vasiliz.VasiliZTwitterApp.images.events.ImagesEvent;

public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImagesTweets();
    void onEventMainThread(ImagesEvent pImagesEvent);

}
