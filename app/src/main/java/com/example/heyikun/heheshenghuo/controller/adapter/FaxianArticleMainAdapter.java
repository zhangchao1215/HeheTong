package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.FaxianShopBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/11/6 21:29
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

public class FaxianArticleMainAdapter extends BaseRecyclerAdapter<FaxianShopBean.DataBean> {
    private FaXianShopRecyclerAdapter adapter;

    private List<FaxianShopBean.DataBean.GoodsBean> beanList;


    public void setBeanList(List<FaxianShopBean.DataBean.GoodsBean> beanList) {
        this.beanList = beanList;

    }

    public List<FaxianShopBean.DataBean.GoodsBean> getBeanList() {
        return beanList;
    }

    public FaxianArticleMainAdapter(List<FaxianShopBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.activity_faxian_article_item);
        beanList = new ArrayList<>();
    }


    @Override
    protected void convert(ViewHolder holder, FaxianShopBean.DataBean dataBean, final int Position) {

        RecyclerView recycler = holder.getView(R.id.faxian_recyclerItem);

        ImageView image = holder.getView(R.id.faxian_imageItem);

        holder.setText(R.id.faxian_shoppingName, dataBean.getArticle_title());

        Glide.with(context)
                .load(dataBean.getArticle_pic())
                .centerCrop()
                .placeholder(R.drawable.jfjz24x)
                .into(image);


        List<FaxianShopBean.DataBean.GoodsBean> goods = dataBean.getGoods();
        if (goods == null) {
            return;
        }
        LinearLayoutManager man = new LinearLayoutManager(context);
        man.setOrientation(LinearLayoutManager.HORIZONTAL);

        recycler.setLayoutManager(man);
        beanList.addAll(goods);
        adapter = new FaXianShopRecyclerAdapter(beanList, context);
        recycler.setAdapter(adapter);


    }



}
