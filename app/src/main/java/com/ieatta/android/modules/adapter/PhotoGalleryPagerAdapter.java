package com.ieatta.android.modules.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by djzhang on 12/19/15.
 */
public class PhotoGalleryPagerAdapter extends FragmentStatePagerAdapter {
    private final int[] IMAGES;

    public PhotoGalleryPagerAdapter(FragmentManager fm, int[] images) {
        super(fm);
        this.IMAGES = images;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setAsset(this.IMAGES[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return this.IMAGES.length;
    }
}
