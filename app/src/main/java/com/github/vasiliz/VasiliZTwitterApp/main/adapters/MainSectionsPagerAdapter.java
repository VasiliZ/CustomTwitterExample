package com.github.vasiliz.VasiliZTwitterApp.main.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainSectionsPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private Fragment[] mFragments;

    public MainSectionsPagerAdapter(FragmentManager fm, String[] pTitles, Fragment[] pFragments) {
        super(fm);
        mTitles = pTitles;
        mFragments = pFragments;
    }

    @Override
    public Fragment getItem(int pI) {
        return this.mFragments[pI];
    }

    @Override
    public int getCount() {
        return this.mFragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTitles[position];
    }
}
