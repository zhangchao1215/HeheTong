package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.JingluoArticleBean;

import java.util.List;

/**
 * Created by hyk on 2017/10/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/28
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 和合生活页面文本内容的适配器
 */

public class HeHeLifeTextAdapter extends BaseAdapter {
    private Context context;
    private JingluoArticleBean beenList;

    public HeHeLifeTextAdapter(Context context, JingluoArticleBean beenList) {
        this.context = context;
        this.beenList = beenList;
    }

    @Override
    public int getCount() {
        return 1;
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
        MviewHodler hodler = null;
        if (hodler == null) {
            hodler = new MviewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_hehelife_text_item, null);

            hodler.Tv = (TextView) convertView.findViewById(R.id.life_textitem);


        }


        hodler.Tv.setText(Html.fromHtml(beenList.getChannels()));


        return convertView;
    }

    class MviewHodler {

        private TextView Tv;

    }

}
