package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.LifeActicleBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

/**
 * Created by hyk on 2017/10/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/14
 * <p>
 * 3：类描述：  首页文章的适配器
 * <p>
 * 4:类功能：
 */

public class LifeActicleAdapter extends BaseRecyclerAdapter<LifeActicleBean.DataBean> {
    //    public LifeActicleAdapter(Context context, List<LifeActicleBean.DataBean> datas) {
    //        super(context, R.layout.activity_life_aticle_item, datas);
    //    }
    //
    //    @Override
    //    public void convert(ViewHolder holder, final LifeActicleBean.DataBean Bean) {
    //        holder.setText(R.id.Life_ActicleTitle, Bean.getTitle());
    //        holder.setText(R.id.Life_ActicleTime, Bean.getAdd_time());
    //        holder.setText(R.id.Life_ActicleDesc, Bean.getDesc());
    //        ImageView imageView = holder.getView(R.id.Life_ActicleImage);
    //        Glide.with(context)
    //                .load(Bean.getFile_url())
    //                .placeholder(R.drawable.jcwz)
    //                .into(imageView);
    //        holder.setOnclickListener(R.id.mLief_ItemClick, new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                Intent intent = new Intent(context, WebViewActivity.class);
    //                intent.putExtra("url", Bean.getLink());
    //                Log.d("LifeActicleAdapter", Bean.getLink());
    //                context.startActivity(intent);
    //            }
    //        });
    //    }
    public LifeActicleAdapter(List<LifeActicleBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.activity_life_aticle_item);
    }

    @Override
    public void convert(final ViewHolder holder, final LifeActicleBean.DataBean Bean, int Position) {

        holder.setText(R.id.Life_ActicleTitle, Bean.getTitle());
        holder.setText(R.id.Life_ActicleTime, Bean.getAdd_time());
        ImageView imageView = holder.getView(R.id.Life_ActicleImage);

        Glide.with(context)
                .load(Bean.getFile_url())
                .placeholder(R.drawable.jcwz)
                .into(imageView);


        holder.setOnclickListener(R.id.mLief_ItemClick, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //                WebViewUtils.ShopIntentWeb(context, Bean.getLink(), "文章");

                WebViewUtils.publicWebView(context, Bean.getLink(),"文章");

            }
        });


    }

}
