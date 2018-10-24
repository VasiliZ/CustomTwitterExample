package com.github.vasiliz.VasiliZTwitterApp.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TimelineService {
    @GET("/1.1/statuses/home_line.json")
    void homeTimeLine(@Query("coutr") Integer pCount,
                      @Query("trim_user") boolean pTrim_user,
                      @Query("exclude_recipients") boolean pExclude_recipients,
                      @Query("contributor_details") boolean pContributor_details,
                      @Query("include_entities") boolean pInclude_entities,
                      Callback<List<Tweet>> pCallback);

}
