package com.github.vasiliz.VasiliZTwitterApp.hashtags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vasiliz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashTagsFragment extends Fragment {

    public HashTagsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hash_tags_fragment, container, false);
    }

}
