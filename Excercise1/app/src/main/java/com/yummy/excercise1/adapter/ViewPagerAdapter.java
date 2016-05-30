package com.yummy.excercise1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yummy.excercise1.model.Image;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Image> images;

    public ViewPagerAdapter(FragmentManager fm, List<Image> imagesList) {
        super(fm);
        this.images = imagesList;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.getInstance(images.get(position).getSrc());
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
