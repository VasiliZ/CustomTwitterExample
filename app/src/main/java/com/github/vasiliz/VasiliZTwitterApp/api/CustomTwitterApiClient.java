package com.github.vasiliz.VasiliZTwitterApp.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(Session pSession) {
        super((TwitterSession) pSession);
    }

    public TimelineService getTimelineService() {
        return getService(TimelineService.class);
    }
}
