package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeYangshenBean;

import java.util.List;

/**
 * Created by hyk on 2017/11/21.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/21
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class LifeGvWomenAdapter extends BaseAdapter {
    private List<HeHeYangshenBean.DataBean.BodyBean> mList;
    private Context context;


    public LifeGvWomenAdapter(List<HeHeYangshenBean.DataBean.BodyBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
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
        ViewHoder hoder = null;
        if (hoder == null) {
            hoder = new ViewHoder();

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_gridview_item, null);

            hoder.image = (ImageView) convertView.findViewById(R.id.life_new_image);

            hoder.text = (TextView) convertView.findViewById(R.id.life_new_text);

            convertView.setTag(hoder);


        } else {
            hoder = (ViewHoder) convertView.getTag();
        }

        HeHeYangshenBean.DataBean.BodyBean bodyBean = mList.get(position);

        Glide.with(context)
                .load(bodyBean.getVote_woman())
                .centerCrop()
                .placeholder(R.drawable.wt_woman_4x)
                .into(hoder.image);

        hoder.text.setText(bodyBean.getVote_name());


        return convertView;
    }


    public class ViewHoder {
        private ImageView image;
        private TextView text;


    }
}
