package com.example.heyikun.heheshenghuo.controller.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hyk on 2017/10/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/28
 * <p>
 * 3：类描述： 轮播图实现自动轮播
 * <p>
 * 4:类功能：
 */

public class SlideshowAdapter extends PagerAdapter {

    private List<View> mList;

    public SlideshowAdapter(List<View> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        //在适配中定义一个int的最大值
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (container != null) {
            container.removeView(mList.get(position % mList.size()));
        }
        container.addView(mList.get(position % mList.size()));
        return mList.get(position % mList.size());

    }
}
