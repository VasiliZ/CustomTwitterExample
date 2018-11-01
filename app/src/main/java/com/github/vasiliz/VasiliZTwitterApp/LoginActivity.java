package com.github.vasiliz.VasiliZTwitterApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.github.vasiliz.R;
import com.github.vasiliz.VasiliZTwitterApp.main.ui.MainActivity;
import com.twitter.sdk.android.core.AuthToken;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.twitter_loginButton)
    TwitterLoginButton mTwitterLoginButton;
    @BindView(R.id.loginContainer)
    RelativeLayout mLayout;
    private Session mSession;
    private AuthToken authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {
            mSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
            if (mSession != null) {
                authToken = mSession.getAuthToken();

            }
            navigateToMailScreen();
        } else {

            mTwitterLoginButton.setCallback(new Callback<TwitterSession>() {

                @Override
                public void success(Result<TwitterSession> result) {
                    navigateToMailScreen();
                }

                @Override
                public void failure(TwitterException exception) {
                    String msg = String.format(getString(R.string.error_login)
                            , exception.getLocalizedMessage());
                    Snackbar.make(mLayout, msg, Snackbar.LENGTH_SHORT).show();
                }
            });
        }
        // TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig()
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTwitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void navigateToMailScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
