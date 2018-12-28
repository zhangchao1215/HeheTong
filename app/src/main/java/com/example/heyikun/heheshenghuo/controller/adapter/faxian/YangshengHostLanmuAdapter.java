package com.example.heyikun.heheshenghuo.controller.adapter.faxian;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BaseRecyclerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.YangShenPeopleBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

/**
 * Created by hyk on 2017/12/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/11
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 热门栏目是适配器，水平滑动的
 */

public class YangshengHostLanmuAdapter extends BaseRecyclerAdapter<YangShenPeopleBean.DataBean.ColumnBean> {
    public YangshengHostLanmuAdapter(List<YangShenPeopleBean.DataBean.ColumnBean> mList, Context context) {
        super(mList, context, R.layout.activity_yangshen_people_oneitem);
    }

    @Override
    protected void convert(ViewHolder holder, final YangShenPeopleBean.DataBean.ColumnBean columnBean, int Position) {

        ImageView image = holder.getView(R.id.yangsheng_lanmu_image);

        Glide.with(context)
                .load(columnBean.getColumn_pic())
                .placeholder(R.drawable.jcwz)
                .into(image);


        holder.setOnclickListener(R.id.yangshen_lanmu_linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.bigCastWebView(context, columnBean.getColumn_link(), columnBean.getColumn_name());
            }
        });


    }
}
