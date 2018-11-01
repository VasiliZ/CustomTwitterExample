package com.github.vasiliz.VasiliZTwitterApp.hashtags.di;

import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagPresenter;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.HashTagsFragment;
import com.github.vasiliz.VasiliZTwitterApp.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, HashtagModule.class})
public interface HashtagComponent {
    void inject(HashTagsFragment pHashTagsFragment);
    HashtagPresenter getPresenter();

}
