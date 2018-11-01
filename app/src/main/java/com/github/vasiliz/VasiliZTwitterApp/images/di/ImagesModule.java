package com.github.vasiliz.VasiliZTwitterApp.images.di;

import com.github.vasiliz.VasiliZTwitterApp.api.CustomTwitterApiClient;
import com.github.vasiliz.VasiliZTwitterApp.entities.Image;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesInteractor;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesInteractorImpl;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesPresenter;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesPresenterImpl;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesRepository;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesRepositoryImpl;
import com.github.vasiliz.VasiliZTwitterApp.images.adapters.ImagesAdapter;
import com.github.vasiliz.VasiliZTwitterApp.images.adapters.OnItemClickListener;
import com.github.vasiliz.VasiliZTwitterApp.images.ui.ImageView;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.EventBus;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.ImageLoader;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImagesModule {

    private ImageView mImageView;
    private OnItemClickListener mOnItemClickListener;

    public ImagesModule(ImageView pImageView, OnItemClickListener pOnItemClickListener) {
        mImageView = pImageView;
        mOnItemClickListener = pOnItemClickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> pImages, ImageLoader pImageLoader, OnItemClickListener pOnItemClickListener) {
        return new ImagesAdapter(pImages, pImageLoader, pOnItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    @Provides
    @Singleton
    List<Image> providesListImages() {
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus pEventBus, ImageView pView, ImagesInteractor pImagesInteractor) {
        return new ImagesPresenterImpl(pEventBus, pView, pImagesInteractor);
    }

    @Provides
    @Singleton
    ImageView providesIImageView() {
        return this.mImageView;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository pImagesRepository) {
        return new ImagesInteractorImpl(pImagesRepository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus pEventBus, CustomTwitterApiClient pCustomTwitterApiClient) {
        return new ImagesRepositoryImpl(pEventBus, pCustomTwitterApiClient);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session pSession) {
        return new CustomTwitterApiClient(pSession);
    }

    @Provides
    @Singleton
    Session provideTwitterSession() {
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }

}
