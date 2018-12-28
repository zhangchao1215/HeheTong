package com.example.heyikun.heheshenghuo.controller.adapter.faxian;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.faxian.MasterDetailActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.BaseRecyclerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.YangShenPeopleBean;

import java.util.List;

/**
 * Created by hyk on 2017/12/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/11
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 热门达人
 */

public class HotMasterAdapter extends BaseRecyclerAdapter<YangShenPeopleBean.DataBean.HotMasterBean> {
    public HotMasterAdapter(List<YangShenPeopleBean.DataBean.HotMasterBean> mList, Context context) {
        super(mList, context, R.layout.activity_yangshen_host_master);
    }

    @Override
    protected void convert(ViewHolder holder, final YangShenPeopleBean.DataBean.HotMasterBean hotMasterBean, int Position) {

        final ImageView imageView = holder.getView(R.id.host_master_image);


        Glide.with(context)
                .load(hotMasterBean.getMaster_headimg())
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable BitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);

                        BitmapDrawable.setCircular(true);

                        imageView.setImageDrawable(BitmapDrawable);
                    }
                });

        holder.setText(R.id.host_master_name, hotMasterBean.getMaster_name());

        holder.setText(R.id.host_master_alias, hotMasterBean.getMaster_desc());


        holder.setOnclickListener(R.id.host_linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MasterDetailActivity.class);
                intent.putExtra("master_id",hotMasterBean.getMaster_id());
                intent.putExtra("master_image",hotMasterBean.getMaster_headimg());
                context.startActivity(intent);


            }
        });

    }
}
