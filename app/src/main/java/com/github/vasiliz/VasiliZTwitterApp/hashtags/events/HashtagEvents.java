package com.github.vasiliz.VasiliZTwitterApp.hashtags.events;

import com.github.vasiliz.VasiliZTwitterApp.entities.HashTag;

import java.util.List;

public class HashtagEvents {
    private String mError;
    private List<HashTag> mHashTags;

    public String getError() {
        return mError;
    }

    public void setError(String pError) {
        mError = pError;
    }

    public List<HashTag> getHashTags() {
        return mHashTags;
    }

    public void setHashTags(List<HashTag> pHashTags) {
        mHashTags = pHashTags;
    }
}
