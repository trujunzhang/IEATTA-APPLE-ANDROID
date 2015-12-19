package com.ieatta.android.modules.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ieatta.android.R;

/**
 * Created by djzhang on 12/19/15.
 */
public class PhotoGalleryPagerAdapter extends FragmentStatePagerAdapter {
    private static final int[] IMAGES = {R.drawable.rest01, R.drawable.review_stars_0_inline, R.drawable.logo_yelp};

    public PhotoGalleryPagerAdapter(FragmentManager fm) {
        super(fm);
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
