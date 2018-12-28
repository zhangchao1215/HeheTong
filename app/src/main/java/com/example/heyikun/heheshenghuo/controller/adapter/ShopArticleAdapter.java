package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;

import com.example.heyikun.heheshenghuo.modle.bean.ShoppingArticleBean;

import java.util.List;

/**
 * Created by hyk on 2017/11/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/13
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ShopArticleAdapter extends BaseRecyclerAdapter<ShoppingArticleBean.DataBean> {
    public ShopArticleAdapter(List<ShoppingArticleBean.DataBean> mList, Context context, int layoutId) {
        super(mList, context, layoutId);
    }

    @Override
    protected void convert(ViewHolder holder, ShoppingArticleBean.DataBean dataBean, int Position) {

    }
}
