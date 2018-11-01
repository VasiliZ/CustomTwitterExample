package com.github.vasiliz.VasiliZTwitterApp.images;

import android.util.Log;
import android.widget.ListView;

import com.github.vasiliz.VasiliZTwitterApp.api.CustomTwitterApiClient;
import com.github.vasiliz.VasiliZTwitterApp.api.TimelineService;
import com.github.vasiliz.VasiliZTwitterApp.entities.Image;
import com.github.vasiliz.VasiliZTwitterApp.images.events.ImagesEvent;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ImagesRepositoryImpl implements ImagesRepository {

    private EventBus mEventBus;
    private CustomTwitterApiClient mCustomTwitterApiClient;
    public static final int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(EventBus pEventBus, CustomTwitterApiClient pCustomTwitterApiClient) {
        mEventBus = pEventBus;
        mCustomTwitterApiClient = pCustomTwitterApiClient;
    }

    @Override
    public void getImages() {
        mCustomTwitterApiClient.getTimelineService().homeTimeLine(TWEET_COUNT, true, true, true)
                .enqueue(new Callback<List<Tweet>>() {

                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Image> images = new ArrayList<>();
                        for (Tweet tweet:result.data){
                            if(containsTweets(tweet)){
                                Image tweetModel = new Image();
                                tweetModel.setId(tweet.idStr);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                String s = tweet.text;
                                int index = s.indexOf("http");
                                if (index>0){
                                    s = s.substring(0, index);
                                }
                                tweetModel.setTweetText(s);
                                MediaEntity mediaEntity = tweet.entities.media.get(0);
                                String imageUrl = mediaEntity.mediaUrl;
                                tweetModel.setImageUrl(imageUrl);

                                images.add(tweetModel);
                            }
                        }
                        Collections.sort(images, new Comparator<Image>() {

                            @Override
                            public int compare(Image o1, Image o2) {
                                return o2.getFavoriteCount() - o1.getFavoriteCount();
                            }
                        });
                        post(images);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.d("failure" , "no data");
                    }
                });
            }

    private boolean containsTweets(Tweet pTweet) {
        return pTweet.entities != null &&
                pTweet.entities.media != null &&
                !pTweet.entities.media.isEmpty();
    }

    private void post(List<Image> pImages) {
        post(null, pImages);
    }

    private void post(String error) {
        post(error, null);
    }

    private void post(String error, List<Image> pImages) {
        ImagesEvent imagesEvent = new ImagesEvent();
        imagesEvent.setError(error);
        imagesEvent.setImages(pImages);
        mEventBus.post(imagesEvent);
    }
}
