package com.example.heyikun.heheshenghuo.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hyk on 2017/9/12.
 */

public class ShouYeFragmentAdapter extends FragmentPagerAdapter {
    private List<String> strList;
    private List<Fragment> mList;

    public ShouYeFragmentAdapter(FragmentManager fm, List<String> strList, List<Fragment> mList) {
        super(fm);
        this.strList = strList;
        this.mList = mList;
    }

    public ShouYeFragmentAdapter(FragmentManager fm, List<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strList.get(position);
    }
}
