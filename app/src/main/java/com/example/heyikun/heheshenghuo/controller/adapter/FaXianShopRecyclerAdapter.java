package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.FaxianShopBean;
import com.example.heyikun.heheshenghuo.modle.util.GlideRoundTransform;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

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

public class FaXianShopRecyclerAdapter extends BaseRecyclerAdapter<FaxianShopBean.DataBean.GoodsBean> {
    public FaXianShopRecyclerAdapter(List<FaxianShopBean.DataBean.GoodsBean> mList, Context context) {
        super(mList, context, R.layout.activity_faxian_shop_item);
    }

    @Override
    public void convert(ViewHolder holder, final FaxianShopBean.DataBean.GoodsBean Bean, int Position) {

        ImageView image = holder.getView(R.id.Faxian_shopImage);

        holder.setText(R.id.Faxian_shopName, Bean.getGoods_name());

        holder.setText(R.id.Faxian_shopNumber, Bean.getGoods_price());


        Glide.with(context)
                .load(Bean.getGoods_pic())
                .centerCrop()
                .into(image);


        holder.setOnclickListener(R.id.Faxian_shopLinear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.publicWebView(context, Bean.getGoods_link(), "商品");
                Log.d("FaXianShopRecyclerAdapt", Bean.getGoods_link());

            }
        });

    }
}
