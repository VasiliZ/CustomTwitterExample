package com.github.vasiliz.VasiliZTwitterApp.images.events;

import com.github.vasiliz.VasiliZTwitterApp.entities.Image;

import java.util.List;

public class ImagesEvent {

    private String error;
    private List<Image> mImages;

    public String getError() {
        return error;
    }

    public void setError(String pError) {
        error = pError;
    }

    public List<Image> getImages() {
        return mImages;
    }

    public void setImages(List<Image> pImages) {
        mImages = pImages;
    }
}
