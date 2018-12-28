package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.ShoppingArticleBean;

import java.util.List;

/**
 * Created by hyk on 2017/12/20.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/20
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ShopTizhiAdapter extends BaseAdapter {
    private List<ShoppingArticleBean.DataBean> dataBeanList;
    private Context context;

    public ShopTizhiAdapter(List<ShoppingArticleBean.DataBean> dataBeanList, Context context) {
        this.dataBeanList = dataBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
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
        MyViewHodler hodler = null;

        if (hodler == null) {
            hodler = new MyViewHodler();

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_shop_tizhi_item, null);
            hodler.textView = (TextView) convertView.findViewById(R.id.shop_tizhi_item_Text);

            convertView.setTag(hodler);
        } else {
            hodler = (MyViewHodler) convertView.getTag();
        }


        List<String> physique = dataBeanList.get(position).getPhysique();
        //         physique.clear();
        try {
            hodler.textView.setText(physique.get(position) + "");
            hodler.textView.setBackgroundResource(R.drawable.shape_shop_tizhi);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }

    class MyViewHodler {
        private TextView textView;

    }
}
