package com.example.heyikun.heheshenghuo.controller.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.heyikun.heheshenghuo.R;

import java.util.List;

/**
 * Created by hyk on 2017/9/15.
 */

public class SheTai_ViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private int code = -1;

    public SheTai_ViewPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = viewList.get(position);
        CheckBox check = (CheckBox) view.findViewById(R.id.XuanZe_Image);
        check.setChecked(position == code);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code == position) {
                    code = -1;
                    notifyDataSetChanged();
                } else {
                    code = position;
                    notifyDataSetChanged();
                }
            }
        });
        container.addView(view);
        return viewList.get(position);
    }
}
