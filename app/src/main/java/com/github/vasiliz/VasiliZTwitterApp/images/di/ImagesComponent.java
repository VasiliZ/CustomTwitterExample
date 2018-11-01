package com.github.vasiliz.VasiliZTwitterApp.images.di;

import com.github.vasiliz.VasiliZTwitterApp.images.ImagesPresenter;
import com.github.vasiliz.VasiliZTwitterApp.images.ui.ImagesFragmett;
import com.github.vasiliz.VasiliZTwitterApp.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class,ImagesModule.class})
public interface ImagesComponent {
        void inject(ImagesFragmett pImagesFragmett);
        ImagesPresenter getPresenter();
}
