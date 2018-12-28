package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeShoppingBean;
import com.example.heyikun.heheshenghuo.modle.util.GlideRoundTransform;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

import static android.R.id.empty;

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

public class ShopYouHuiRecyclerAdapter extends BaseRecyclerAdapter<HeHeShoppingBean.DataBean.NewGoodsBean> {
    public ShopYouHuiRecyclerAdapter(List<HeHeShoppingBean.DataBean.NewGoodsBean> mList, Context context) {
        super(mList, context, R.layout.activity_shop_youhui_item);
    }

    @Override
    public void convert(ViewHolder holder, final HeHeShoppingBean.DataBean.NewGoodsBean Bean, int Position) {

        ImageView imageView = holder.getView(R.id.Shop_Youhui_Image);

        holder.setText(R.id.Shop_Youhui_Desc, Bean.getGoods_name());

        holder.setText(R.id.Shop_Youhui_price, Bean.getGoods_price());

        Glide.with(context)
                .load(Bean.getGoods_pic())
                .into(imageView);
        holder.setOnclickListener(R.id.Shop_Linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.publicWebView(context, Bean.getGoods_link(), "商品");

            }
        });


    }
}
