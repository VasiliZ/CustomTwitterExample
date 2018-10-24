package com.github.vasiliz.VasiliZTwitterApp.lib;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.ImageLoader;

public class GlideImageLoader implements ImageLoader {

    private RequestManager mRequestManager;

    public GlideImageLoader(RequestManager pRequestBuilder) {
        mRequestManager = pRequestBuilder;
    }

    @Override
    public void load(ImageView pImageView, String pUrl) {

        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(600, 400);

        mRequestManager
                .load(pUrl)
                .apply(requestOptions)
                .into(pImageView);

    }
}
