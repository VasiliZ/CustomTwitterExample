package com.github.vasiliz.VasiliZTwitterApp.images;

public class ImagesInteractorImpl implements ImagesInteractor {
    private ImagesRepository mImagesRepository;

    public ImagesInteractorImpl(ImagesRepository pImagesRepository) {
        mImagesRepository = pImagesRepository;
    }

    @Override
    public void execute() {
        mImagesRepository.getImages();
    }
}
