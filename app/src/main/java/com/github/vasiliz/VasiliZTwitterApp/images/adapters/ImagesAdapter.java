package com.github.vasiliz.VasiliZTwitterApp.images.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.vasiliz.R;
import com.github.vasiliz.VasiliZTwitterApp.entities.Image;
import com.github.vasiliz.VasiliZTwitterApp.lib.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<Image> mImages;
    private ImageLoader mImageLoader;
    private OnItemClickListener mOnItemClickListener;

    public ImagesAdapter(List<Image> pImages, ImageLoader pImageLoader, OnItemClickListener pOnItemClickListener) {
        mImages = pImages;
        mImageLoader = pImageLoader;
        mOnItemClickListener = pOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View view = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.content_images, pViewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        Image imageTweet = mImages.get(pI);
        pViewHolder.setOnClickListener(imageTweet, mOnItemClickListener);
        pViewHolder.mTextView.setText(imageTweet.getTweetText());
        mImageLoader.load(pViewHolder.mImageView, imageTweet.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setItems(List<Image> pItems){
        mImages.addAll(pItems);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.content_images)
        ImageView mImageView;
        @BindView(R.id.content_text)
        TextView mTextView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mView = itemView;
            ButterKnife.bind(this, mView);
        }

        public void setOnClickListener(final Image pImage, final OnItemClickListener pOnItemClickListener){
            mView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pOnItemClickListener.onItemClick(pImage);
                }
            });
        }
    }

}
