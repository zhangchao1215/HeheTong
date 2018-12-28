package com.example.heyikun.heheshenghuo.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/7 17:18
 * description：
 */

public class FragmentViewPageAdapter extends FragmentPagerAdapter {
	private List<String> strList;
	private List<Fragment> fragmentList;

	public FragmentViewPageAdapter(FragmentManager fm, List<String> strList, List<Fragment> fragmentList) {
		super(fm);
		this.strList = strList;
		this.fragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return strList.get(position);
	}
}
