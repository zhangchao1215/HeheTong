package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeShoppingBean;
import com.example.heyikun.heheshenghuo.modle.bean.ShoppingArticleBean;
import com.example.heyikun.heheshenghuo.modle.util.GlideRoundTransform;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyk on 2017/10/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/28
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ShopTuiJianRecyclerAdapter extends BaseRecyclerAdapter<ShoppingArticleBean.DataBean> {

    private ShopTizhiAdapter adapter;
    private List<ShoppingArticleBean.DataBean> dataBeanList;

    public ShopTuiJianRecyclerAdapter(List<ShoppingArticleBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.activity_shop_tuijian_item);
        dataBeanList = new ArrayList<>();
    }

    @Override
    public void convert(ViewHolder holder, final ShoppingArticleBean.DataBean Bean, int Position) {

        ImageView image = holder.getView(R.id.Shop_Tuijian_image);

        GridView gridView = holder.getView(R.id.shop_gridview);

        holder.setText(R.id.Shop_Tuijian_Name, Bean.getRecommend_name());

        holder.setText(R.id.Shop_Tuijian_price, "￥"+Bean.getRecommend_price());

        holder.setText(R.id.SHop_Tuijian_oldPrice, "￥"+Bean.getOld_price());

        Glide.with(context)
                .load(Bean.getRecommend_pic())
                .centerCrop()
                .into(image);

        holder.setOnclickListener(R.id.Shop_Tuijian_Linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewUtils.publicWebView(context, Bean.getRecommend_link(), "商品");


            }
        });


        dataBeanList.add(Bean);
        adapter = new ShopTizhiAdapter(dataBeanList, context);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}
