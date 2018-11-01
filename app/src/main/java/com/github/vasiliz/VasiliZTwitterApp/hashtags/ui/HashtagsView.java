package com.github.vasiliz.VasiliZTwitterApp.hashtags.ui;

import com.github.vasiliz.VasiliZTwitterApp.entities.HashTag;

import java.util.List;

public interface HashtagsView {
    void showHashtags();
    void hideHashtags();
    void showProgress();
    void hideProgress();

    void onError(String pError);
    void setHashtags(List<HashTag> pHashtags);


}
