package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.faxian.BigCastPeopleDetailActivity;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastAllLookBean;

import java.util.List;

/**
 * Created by hyk on 2017/11/18.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/18
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  查看所有大咖的详情适配器 ，内部类适配器
 */

public class BigCastPeopleItemAdapter extends BaseRecyclerAdapter<BigCastAllLookBean.DataBean.BigMessBean> {
    public BigCastPeopleItemAdapter(List<BigCastAllLookBean.DataBean.BigMessBean> mList, Context context) {
        super(mList, context, R.layout.activity_bigcast_people_item);
        Log.d("BigCastPeopleItemAdapte", "mList.size():" + mList.size());
    }

    @Override
    protected void convert(ViewHolder holder, final BigCastAllLookBean.DataBean.BigMessBean bigMessBean, int Position) {
       final ImageView imageView = holder.getView(R.id.Bigcast_peopleImage);

        holder.setText(R.id.Bigcast_peopleName, bigMessBean.getBig_name());
        holder.setText(R.id.Bigcast_peopleContent, bigMessBean.getBig_desc());

        Glide.with(context)
                .load(bigMessBean.getBig_pic())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.touxiang_nan_man_4x)
                .into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);

            }

        });

        holder.setOnclickListener(R.id.title_linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BigCastPeopleDetailActivity.class);
                intent.putExtra("image", bigMessBean.getBig_pic());
                intent.putExtra("desc", bigMessBean.getBig_desc());
                intent.putExtra("name", bigMessBean.getBig_name());
                intent.putExtra("id", bigMessBean.getBig_id());
                context.startActivity(intent);

            }
        });

    }
}
