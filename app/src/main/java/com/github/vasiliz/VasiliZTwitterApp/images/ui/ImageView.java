package com.github.vasiliz.VasiliZTwitterApp.images.ui;

import com.github.vasiliz.VasiliZTwitterApp.entities.Image;

import java.util.List;

public interface ImageView {
    void showElements();
    void hideElements();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> pImageList);

}
