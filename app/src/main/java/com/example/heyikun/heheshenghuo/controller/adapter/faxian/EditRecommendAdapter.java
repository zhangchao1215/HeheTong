package com.example.heyikun.heheshenghuo.controller.adapter.faxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BaseRecyclerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.YangShenPeopleBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

import static com.alipay.sdk.app.statistic.c.G;

/**
 * Created by hyk on 2017/12/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/11
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 养生达人的编辑推荐
 */

public class EditRecommendAdapter extends BaseRecyclerAdapter<YangShenPeopleBean.DataBean.RecommendationBean> {
    private ImageOnItemClick onItemClick;

    public void setOnItemClick(ImageOnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public EditRecommendAdapter(List<YangShenPeopleBean.DataBean.RecommendationBean> mList, Context context) {
        super(mList, context, R.layout.activity_yangshen_artivle_item);
    }

    @Override
    protected void convert(ViewHolder holder, final YangShenPeopleBean.DataBean.RecommendationBean Bean, final int Position) {

        final ImageView imageHead = holder.getView(R.id.yangshen_imageOne);

        ImageView imageView = holder.getView(R.id.yangshen_imageTwo);


        Glide.with(context)
                .load(Bean.getMaster_headimg())
                .asBitmap()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(imageHead) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);
                        imageHead.setImageDrawable(drawable);

                    }
                });

        Glide.with(context)
                .load(Bean.getArticle_pic())
                .placeholder(R.drawable.jcwz)
                .centerCrop()
                .into(imageView);


        holder.setText(R.id.yangshen_name, Bean.getMaster_name());

        holder.setText(R.id.yangshen_textTitle, Bean.getArticle_title());

        holder.setText(R.id.yangshen_textContent, Bean.getArticle_content());


        holder.setText(R.id.yangshen_textdesc, Bean.getMaster_desc() + "");


        holder.setOnclickListener(R.id.recommend_linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.bigCastWebView(context, Bean.getArticle_link(), Bean.getArticle_title());

            }
        });


        imageHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onImageClick(Position);

            }
        });
    }


    public interface ImageOnItemClick {

        void onImageClick(int position);


    }
}
