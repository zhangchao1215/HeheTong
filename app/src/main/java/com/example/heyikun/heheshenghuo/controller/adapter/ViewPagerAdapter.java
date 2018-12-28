package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hyk on 2017/10/27.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/27
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ViewPagerAdapter  extends PagerAdapter{
    // 界面列表
    private List<View> views;
    private Context activity;


    public ViewPagerAdapter(List<View> views, Context activity) {
        this.views = views;
        this.activity = activity;
    }

    //加载viewpager的每个item
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position),0);
        return views.get(position);
    }
    //删除ViewPager的item
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        container.removeView(views.get(position));
    }

    // 获得当前界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }
    //官方推荐这么写，没研究。。。。
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
