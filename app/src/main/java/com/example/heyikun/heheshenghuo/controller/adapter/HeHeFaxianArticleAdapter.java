package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.FaxianShopBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyk on 2017/12/1.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/1
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 健康发现金木水火土 文章适配器
 */

public class HeHeFaxianArticleAdapter extends BaseRecyclerAdapter<FaxianShopBean.DataBean> {
    private List<FaxianShopBean.DataBean.GoodsBean> goodsBeanList;

    private FaXianShopRecyclerAdapter adapter;

    private FaxianArticleClick articleClick;

    public void setArticleClick(FaxianArticleClick articleClick) {
        this.articleClick = articleClick;
    }

    public HeHeFaxianArticleAdapter(List<FaxianShopBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.activity_faxian_article_item);
    }

    @Override
    protected void convert(ViewHolder holder, FaxianShopBean.DataBean dataBean, final int Position) {
        goodsBeanList = new ArrayList<>();

        RecyclerView recyclerView = holder.getView(R.id.faxian_recyclerItem);


        ImageView imageView = holder.getView(R.id.faxian_imageItem);

        Glide.with(context)
                .load(dataBean.getArticle_pic())
                .placeholder(R.drawable.jfjz24x)
                .into(imageView);

        List<FaxianShopBean.DataBean.GoodsBean> goods = dataBean.getGoods();

        holder.setText(R.id.faxian_shoppingName, dataBean.getArticle_title());

        Log.d("HeHeFaxianArticleAdapte", "dataBean.getGoods().size():" + dataBean.getGoods().size());

        LinearLayoutManager manager = new LinearLayoutManager(context);

        manager.setOrientation(RecyclerView.HORIZONTAL);

        recyclerView.setLayoutManager(manager);

        goodsBeanList.addAll(goods);

        adapter = new FaXianShopRecyclerAdapter(goodsBeanList, context);

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleClick.onClick(Position);
            }
        });


    }

    public interface FaxianArticleClick {

        void onClick(int position);

    }
}
