package com.github.vasiliz.VasiliZTwitterApp.hashtags.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.github.vasiliz.R;
import com.github.vasiliz.VasiliZTwitterApp.MyTwitterApp;
import com.github.vasiliz.VasiliZTwitterApp.entities.HashTag;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.HashtagPresenter;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.di.HashtagComponent;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.adapters.HashtagAdapter;
import com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.adapters.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashTagsFragment extends Fragment implements HashtagsView, OnItemClickListener {
    @BindView(R.id.hashtag_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.hashtags_reryrler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.hashtag_content)
    FrameLayout mFrameLayout;

    @Inject
    HashtagAdapter mHashtagAdapter;
    @Inject
    HashtagPresenter mHashtagPresenter;

    public HashTagsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.hash_tags_fragment, container, false);
        ButterKnife.bind(this, view);
        setupInjection();
        setupRecyclerView();
        mHashtagPresenter.getHashtagTweets();
        return view;
    }

    private void setupInjection() {
        MyTwitterApp app = (MyTwitterApp) getActivity().getApplication();
        HashtagComponent hashtagComponent = app.getHashtagComponent(this, this);
        hashtagComponent.inject(this);
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mHashtagAdapter);
    }

    @Override
    public void showHashtags() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHashtags() {
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String pError) {
        Snackbar.make(mFrameLayout, pError, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setHashtags(List<HashTag> pHashtags) {
        mHashtagAdapter.setItems(pHashtags);
    }

    @Override
    public void onItemClick(HashTag pHashTag) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pHashTag.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHashtagPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHashtagPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHashtagPresenter.onDestroy();
    }
}
