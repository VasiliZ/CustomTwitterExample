package com.github.vasiliz.VasiliZTwitterApp.entities;

public class Image {
    private String id;
    private String ImageUrl;
    private String tweetText;
    private int favoriteCount;
    private static final String BASE_TWEET_URL = "https://twitter.com/null/status/";

    public String getId() {
        return id;
    }

    public void setId(String pId) {
        id = pId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String pImageUrl) {
        ImageUrl = pImageUrl;
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

    public String getTweetUrl(){
        return BASE_TWEET_URL + this.id;
    }
}
