package com.github.vasiliz.VasiliZTwitterApp.hashtags.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vasiliz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class HashTagsListAdapter extends RecyclerView.Adapter<HashTagsListAdapter.ViewHolder> {
    private List<String> items;

    public HashTagsListAdapter(ArrayList<String> pItems) {
        items=pItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        View view = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.row_hashtag_text, pViewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder pViewHolder, int pI) {
            pViewHolder.mTextViewHashTag.setText(items.get(pI));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_hashtag)
        TextView mTextViewHashTag;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
