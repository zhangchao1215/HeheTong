package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.Second_RecyclerBean;

import java.util.List;

/**
 * Created by hyk on 2017/9/22.
 */

public class OneListViewAdapter extends BaseAdapter {
    private List<Second_RecyclerBean.DataBean> mList;
    private Context content;

    public OneListViewAdapter(List<Second_RecyclerBean.DataBean> mList, Context content) {
        this.mList = mList;
        this.content = content;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHodle hodle = null;
        hodle = new MyHodle();

        convertView = LayoutInflater.from(content).inflate(R.layout.activity_recycleritem_one, null);

        hodle.mText = (TextView) convertView.findViewById(R.id.mRecycler_ItemRB_One);
        convertView.setTag(hodle);



        Second_RecyclerBean.DataBean dataBean = mList.get(position);

        hodle.mText.setText(dataBean.getDiag_name());


        return convertView;
    }

    class MyHodle {

        private TextView mText;

    }


}
