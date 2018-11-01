package com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vasiliz.R;
import com.github.vasiliz.VasiliZTwitterApp.entities.HashTag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HashtagAdapter extends RecyclerView.Adapter<HashtagAdapter.ViewHolder> {

    private List<HashTag> mHashTags;
    private OnItemClickListener mOnItemClickListener;

    public HashtagAdapter(List<HashTag> pHashTags, OnItemClickListener pOnItemClickListener) {
        mHashTags = pHashTags;
        mOnItemClickListener = pOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View view = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.content_hashtags, pViewGroup, false);
        return new ViewHolder(view, pViewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
        HashTag tweet = mHashTags.get(pI);
        pViewHolder.setOnClickListenet(tweet, mOnItemClickListener);
        pViewHolder.mTextView.setText(tweet.getTweetText());
        pViewHolder.setItems(tweet.getHashtags());
    }

    @Override
    public int getItemCount() {
        return mHashTags.size();
    }

    public void setItems(List<HashTag> pItems){
        mHashTags.addAll(pItems);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_hashtags)
        TextView mTextView;
        @BindView(R.id.recycler_view_hashtags)
        RecyclerView mRecyclerView;

        private View mView;
        private HashTagsListAdapter mHashTagsListAdapter;
        private ArrayList<String> mItems;

        public ViewHolder(@NonNull View itemView, Context pContext) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;

            mItems = new ArrayList<>();
            mHashTagsListAdapter = new HashTagsListAdapter(mItems);

            CustomGridlayoutManager customGridlayoutManager = new CustomGridlayoutManager(pContext, 3);
            mRecyclerView.setLayoutManager(customGridlayoutManager);
            mRecyclerView.setAdapter(mHashTagsListAdapter);
        }

        public void setItems(List<String> newItems) {
            mItems.clear();
            mItems.addAll(newItems);
            mHashTagsListAdapter.notifyDataSetChanged();
        }

        public void setOnClickListenet(final HashTag pHashTag, final OnItemClickListener pOnItemClickListener) {
            mView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pOnItemClickListener.onItemClick(pHashTag);
                }
            });
        }
    }
}
