/*
Copyright 2014 David Morrissey

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.ieatta.android.modules.view.photogallery;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.ieatta.android.R;

public class PhotoGalleryPagerActivity extends FragmentActivity {

    private static final String[] IMAGES = { "ness.jpg", "squirrel.jpg" };

    private ViewPager page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_controller_photo_gallery_page);
        getActionBar().setTitle("View pager gallery123");

        getActionBar().setDisplayHomeAsUpEnabled(true);

        PagerAdapter pagerAdapter = new PhotoGalleryPagerAdapter(getSupportFragmentManager(),IMAGES);
        page = (ViewPager)findViewById(R.id.pager);
        page.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (page.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            page.setCurrentItem(page.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }


}
