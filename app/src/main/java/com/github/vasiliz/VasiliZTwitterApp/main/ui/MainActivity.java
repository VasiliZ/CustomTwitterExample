package com.github.vasiliz.VasiliZTwitterApp.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.vasiliz.R;
import com.github.vasiliz.VasiliZTwitterApp.LoginActivity;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashTagsFragment;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesFragmett;
import com.github.vasiliz.VasiliZTwitterApp.main.adapters.MainSectionsPagerAdapter;
import com.twitter.sdk.android.core.TwitterCore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.content_container)
    ViewPager mViewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setUpAdapter();
        setSupportActionBar(mToolbar);
    }

    private void setUpAdapter() {
        Fragment[] fragments = new Fragment[]{new ImagesFragmett(), new HashTagsFragment()};
        String header_images = getString(R.string.main_header_images);
        String hashtags = getString(R.string.main_header_hashtags);
        String[] titles = {header_images, hashtags};
        MainSectionsPagerAdapter mainSectionsPagerAdapter = new MainSectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);
        mViewPager.setAdapter(mainSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
        |Intent.FLAG_ACTIVITY_NEW_TASK
        |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}