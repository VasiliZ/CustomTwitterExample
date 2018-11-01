package com.github.vasiliz.VasiliZTwitterApp.images.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.github.vasiliz.R;
import com.github.vasiliz.VasiliZTwitterApp.MyTwitterApp;
import com.github.vasiliz.VasiliZTwitterApp.entities.Image;
import com.github.vasiliz.VasiliZTwitterApp.images.ImagesPresenter;
import com.github.vasiliz.VasiliZTwitterApp.images.adapters.ImagesAdapter;
import com.github.vasiliz.VasiliZTwitterApp.images.adapters.OnItemClickListener;
import com.github.vasiliz.VasiliZTwitterApp.images.di.ImagesComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragmett extends Fragment implements ImageView, OnItemClickListener {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.images_container)
    FrameLayout mImagesConteiner;
    @Inject
    ImagesPresenter mImagesPresenter;
    @Inject
    ImagesAdapter mImagesAdapter;

    public ImagesFragmett() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        setUpInjection();
        setUpRecyclerView();
        mImagesPresenter.getImagesTweets();
        return view;
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mImagesAdapter);
    }

    private void setUpInjection() {
        MyTwitterApp app = (MyTwitterApp) getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this,this,this);
        //mImagesPresenter = imagesComponent.getPresenter();
        imagesComponent.inject(this);
    }

    @Override
    public void showElements() {
        mRecyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideElements() {
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
    public void onError(String error) {
        Snackbar.make(mImagesConteiner, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> pImageList) {
        mImagesAdapter.setItems(pImageList);
    }

    @Override
    public void onItemClick(Image pImage) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pImage.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mImagesPresenter.onResume();
    }

    @Override
    public void onPause() {
        mImagesPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mImagesPresenter.onDestroy();
        super.onDestroy();
    }
}
