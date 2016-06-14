package com.ieatta.android.modules.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ieatta.android.R;
import com.ieatta.android.cache.IntentCache;

public class PhotoGalleryPagerAdapter extends FragmentStatePagerAdapter {
    private PhotoGalleryPagerAdapter self = this;

    public PhotoGalleryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ViewPagerFragment fragment = new ViewPagerFragment();
//        fragment.setAsset(position);
        return fragment;
    }

    @Override
    public int getCount() {
//        return IntentCache.sharedInstance.photoGalleryItem.size();
        return 0;
    }
}
