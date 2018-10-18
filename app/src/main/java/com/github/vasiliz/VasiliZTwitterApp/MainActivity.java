package com.github.vasiliz.VasiliZTwitterApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.vasiliz.R;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {

    private TwitterLoginButton mTwitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig()
    }
}
