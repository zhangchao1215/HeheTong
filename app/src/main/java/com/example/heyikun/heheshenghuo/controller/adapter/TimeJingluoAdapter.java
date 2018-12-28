package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.TimeJingluoBean;

import java.util.List;

/**
 * Created by hyk on 2018/4/20.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/4/20
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class TimeJingluoAdapter extends BaseAdapter {
    private Context context;
    private List<TimeJingluoBean.DataBean> dataBeanList;

    public TimeJingluoAdapter(Context context, List<TimeJingluoBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.activity_time_text, null);


        MyViewHodler viewHodler = new MyViewHodler();

        viewHodler.textView = (TextView) view.findViewById(R.id.mText);

        TimeJingluoBean.DataBean dataBean = dataBeanList.get(i);

        viewHodler.textView.setText(Html.fromHtml(dataBean.getJingluo()));

        return view;
    }

    class MyViewHodler {
        private TextView textView;
    }
}
