package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.ServeBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/12/3 11:05
 * 修改人:  张超
 * 修改内容:
 * 修改时间: 和合服务适配器
 */

public class HeHeServeAdapter extends BaseRecyclerAdapter<ServeBean.DataBean.ShopsBean> {
    public HeHeServeAdapter(List<ServeBean.DataBean.ShopsBean> mList, Context context) {
        super(mList, context, R.layout.activity_hehe_serve_item);
    }

    @Override
    protected void convert(ViewHolder holder, final ServeBean.DataBean.ShopsBean shopsBean, int Position) {

        ImageView image = holder.getView(R.id.serve_item_imageOne);

        ImageView startOne = holder.getView(R.id.start_one);
        ImageView startTwo = holder.getView(R.id.start_two);
        ImageView startThree = holder.getView(R.id.start_three);
        ImageView startFour = holder.getView(R.id.start_four);
        ImageView startFive = holder.getView(R.id.start_five);


        Glide.with(context)
                .load(shopsBean.getShops_logo())
                .placeholder(R.drawable.jcwz)
                .into(image);

        holder.setText(R.id.serve_item_title, shopsBean.getShops_name());
        //距离
        holder.setText(R.id.serve_item_textJuli, shopsBean.getDistance());
        holder.setText(R.id.serve_item_textAddress, shopsBean.getShops_address());


        holder.setOnclickListener(R.id.linar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.publicWebView(context, shopsBean.getShops_link(), shopsBean.getShops_name());

            }
        });


        String rank = shopsBean.getShops_rank();
        if (rank.equals("1")) {

            startOne.setImageResource(R.drawable.star4x);
            startTwo.setImageResource(R.drawable.huistar4x);
            startThree.setImageResource(R.drawable.huistar4x);
            startFour.setImageResource(R.drawable.huistar4x);
            startFive.setImageResource(R.drawable.huistar4x);
        }
        if (rank.equals("2")) {
            startOne.setImageResource(R.drawable.star4x);
            startTwo.setImageResource(R.drawable.star4x);
            startThree.setImageResource(R.drawable.huistar4x);
            startFour.setImageResource(R.drawable.huistar4x);
            startFive.setImageResource(R.drawable.huistar4x);
        }
        if (rank.equals("3")) {
            startOne.setImageResource(R.drawable.star4x);
            startTwo.setImageResource(R.drawable.star4x);
            startThree.setImageResource(R.drawable.star4x);
            startFour.setImageResource(R.drawable.huistar4x);
            startFive.setImageResource(R.drawable.huistar4x);
        }
        if (rank.equals("4")) {
            startOne.setImageResource(R.drawable.star4x);
            startTwo.setImageResource(R.drawable.star4x);
            startThree.setImageResource(R.drawable.star4x);
            startFour.setImageResource(R.drawable.star4x);
            startFive.setImageResource(R.drawable.huistar4x);
        }
        if (rank.equals("5")) {
            startOne.setImageResource(R.drawable.star4x);
            startTwo.setImageResource(R.drawable.star4x);
            startThree.setImageResource(R.drawable.star4x);
            startFour.setImageResource(R.drawable.star4x);
            startFive.setImageResource(R.drawable.star4x);
        }


    }
}
