package com.github.vasiliz.VasiliZTwitterApp.entities;

import java.util.List;

public class HashTag {
    private String id;
    private String tweetText;
    private int favoriteCount;
    private List<String> hashtags;
    private final static String BASE_TWEET_URL = "https://twitter.com/null/status/";

    public String getId() {
        return id;
    }

    public void setId(String pId) {
        id = pId;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String pTweetText) {
        tweetText = pTweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int pFavoriteCount) {
        favoriteCount = pFavoriteCount;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> pHashtags) {
        hashtags = pHashtags;
    }

    public String getTweetUrl(){
        return BASE_TWEET_URL + this.id;
    }
}
