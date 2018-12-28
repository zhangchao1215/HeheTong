package com.example.heyikun.heheshenghuo.controller.adapter.faxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BaseRecyclerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.MasterDetailBean;

import java.util.List;

/**
 * Created by hyk on 2017/12/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class MasterFansAdapter extends BaseRecyclerAdapter<MasterDetailBean.DataBean.FansBean> {
    public MasterFansAdapter(List<MasterDetailBean.DataBean.FansBean> mList, Context context) {
        super(mList, context, R.layout.activity_master_people_item);
    }

    @Override
    protected void convert(ViewHolder holder, MasterDetailBean.DataBean.FansBean bigcastsBean, int Position) {

        final ImageView imageView = holder.getView(R.id.master_item_image);

        Glide.with(context)
                .load(bigcastsBean.getFans_pic())
                .asBitmap()
                .placeholder(R.drawable.touxiang_nan_man_4x)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);

                        imageView.setImageDrawable(drawable);
                    }
                });

        holder.setText(R.id.master_item_name, bigcastsBean.getFans_name());

    }
}
