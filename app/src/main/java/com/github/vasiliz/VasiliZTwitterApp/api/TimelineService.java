package com.github.vasiliz.VasiliZTwitterApp.api;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TimelineService {
    @GET("/1.1/statuses/home_timeline.json")
    Call<List<Tweet>> homeTimeLine(@Query("count") int count,
                                   @Query("trim_user") boolean trimUser,
                                   @Query("exclude_replies") boolean excludeReplies,
                                   @Query("include_entities") boolean includeEntities);

    @GET("/1.1/users/show.json")
    Call<User> show(@Query("user_id") long userId);

}
