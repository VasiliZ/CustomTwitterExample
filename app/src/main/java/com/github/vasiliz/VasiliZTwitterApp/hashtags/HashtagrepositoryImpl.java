package com.github.vasiliz.VasiliZTwitterApp.hashtags;

import android.util.Log;

import com.github.vasiliz.VasiliZTwitterApp.api.CustomTwitterApiClient;
import com.github.vasiliz.VasiliZTwitterApp.entities.HashTag;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.events.HashtagEvents;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HashtagrepositoryImpl implements HashtagRepository {

    private EventBus mEventBus;
    private CustomTwitterApiClient mCustomTwitterApiClient;
    private final static int TWEET_COUNT = 100;

    public HashtagrepositoryImpl(EventBus pEventBus, CustomTwitterApiClient pCustomTwitterApiClient) {
        mEventBus = pEventBus;
        mCustomTwitterApiClient = pCustomTwitterApiClient;
    }

    @Override
    public void getHashtags() {
        mCustomTwitterApiClient.getTimelineService().homeTimeLine(TWEET_COUNT, true, true, true)
                .enqueue(new Callback<List<Tweet>>() {

                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<HashTag> hashtag = new ArrayList<>();
                        for (Tweet tweet : result.data) {
                            if (containsTweets(tweet)) {

                                HashTag hashtagModel = new HashTag();
                                hashtagModel.setId(tweet.idStr);
                                hashtagModel.setFavoriteCount(tweet.favoriteCount);
                                hashtagModel.setTweetText(tweet.text);

                                List<String> strings = new ArrayList<>();
                                for (HashtagEntity hashtagEntity : tweet.entities.hashtags) {
                                    strings.add("lol");
                                    Log.d("tags", hashtagEntity.text);
                                }
                                hashtagModel.setHashtags(strings);
                                hashtag.add(hashtagModel);
                            }
                        }
                        Collections.sort(hashtag, new Comparator<HashTag>() {

                            @Override
                            public int compare(HashTag o1, HashTag o2) {
                                return o2.getFavoriteCount() - o1.getFavoriteCount();
                            }
                        });
                        post(hashtag);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.d("failure", exception.getLocalizedMessage());
                    }
                });
    }

    private boolean containsTweets(Tweet pTweet) {
        return pTweet.entities != null &&
                pTweet.entities.media != null &&
                !pTweet.entities.media.isEmpty();
    }

    private void post(List<HashTag> pImages) {
        post(null, pImages);
    }

    private void post(String error) {
        post(error, null);
    }

    private void post(String error, List<HashTag> hashtag) {
        HashtagEvents hashtagEventsEvent = new HashtagEvents();
        hashtagEventsEvent.setError(error);
        hashtagEventsEvent.setHashTags(hashtag);
        mEventBus.post(hashtagEventsEvent);
    }
}

