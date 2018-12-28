package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastBean;

import java.util.List;

/**
 * Created by hyk on 2017/11/24.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/24
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class BigCastLunboRecyclerAdaoter extends BaseRecyclerAdapter<BigCastBean.DataBean.RecommendBean> {
    public BigCastLunboRecyclerAdaoter(List<BigCastBean.DataBean.RecommendBean> mList, Context context) {
        super(mList, context, R.layout.activity_bigcast_recycler_lunbo_item);
    }

    @Override
    protected void convert(ViewHolder holder, BigCastBean.DataBean.RecommendBean recommendBean, int Position) {

        ImageView imageView = holder.getView(R.id.img);


        Glide.with(context)
                .load(recommendBean.getPeople_pic())
                .into(imageView);


    }
}
