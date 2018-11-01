package com.github.vasiliz.VasiliZTwitterApp.images;

import com.github.vasiliz.VasiliZTwitterApp.images.events.ImagesEvent;
import com.github.vasiliz.VasiliZTwitterApp.images.ui.ImageView;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

public class ImagesPresenterImpl implements ImagesPresenter {
    private EventBus mEventBus;
    private ImageView mView;
    private ImagesInteractor mImagesInteractor;

    public ImagesPresenterImpl(EventBus pEventBus, ImageView pView, ImagesInteractor pImagesInteractor) {
        mEventBus = pEventBus;
        mView = pView;
        mImagesInteractor = pImagesInteractor;
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
        mView = null;
    }

    @Override
    public void getImagesTweets() {
        if (mView !=null){
            mView.hideElements();
            mView.showProgress();
        }
        mImagesInteractor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent pImagesEvent) {
        String errorMsg = pImagesEvent.getError();
        if (mView!=null){
            mView.showElements();
            mView.hideProgress();
            if (errorMsg != null){
                mView.onError(errorMsg);
            }else{
                mView.setContent(pImagesEvent.getImages());
            }
        }

    }
}
