package com.example.heyikun.heheshenghuo.controller.adapter.faxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BaseRecyclerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.MasterDetailBean;

import java.util.List;

/**
 * Created by hyk on 2017/12/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/11
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class YangshengMasterArticleAdapter extends BaseRecyclerAdapter<MasterDetailBean.DataBean.ArticleBean> {
    private ArticleOnClick onClick;
    private List<String> article_pic;

    public void setOnClick(ArticleOnClick onClick) {
        this.onClick = onClick;
    }

    public YangshengMasterArticleAdapter(List<MasterDetailBean.DataBean.ArticleBean> mList, Context context) {
        super(mList, context, R.layout.activity_yangsheng_article_item1);
    }

    @Override
    protected void convert(ViewHolder holder, MasterDetailBean.DataBean.ArticleBean articleBean, final int Position) {

        holder.setText(R.id.master_article_Name, articleBean.getMaster_name());

        final ImageView imageView = holder.getView(R.id.master_article_imageName);

        final ImageView image = holder.getView(R.id.master_article_imageOne);

        ImageView deleteImage = holder.getView(R.id.master_delete_article);

        TextView editText = holder.getView(R.id.Master_edit_article);


        Glide.with(context)
                .load(articleBean.getMaster_headimg())
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(imageView) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);
                        imageView.setImageDrawable(drawable);

                    }
                });


        holder.setText(R.id.master_article_Time, articleBean.getArticle_addtime());

        holder.setText(R.id.master_article_title, articleBean.getArticle_title() + "");

        holder.setText(R.id.master_article_content, articleBean.getArticle_desc() + "");

        article_pic = articleBean.getArticle_pic();
        if (article_pic.size() > 0) {
            Glide.with(context)
                    .load(article_pic.get(0))
                    .placeholder(R.drawable.jcwz)
                    .centerCrop()
                    .into(image);
        }


        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.deleteOnitemClick(Position);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.editonItemClick(Position);
            }
        });



    }

    public interface ArticleOnClick {
        void deleteOnitemClick(int position);

        void editonItemClick(int position);


    }


    @Override
    public int getItemViewType(int position) {
        int size = article_pic.size();
        if (size == 0) {
            return 0;
        }
        if (size == 1) {

            return 1;

        } else if (size == 2) {

            return 2;
        } else if (size == 3) {

            return 3;
        } else if (size == 4) {

            return 4;
        } else if (size == 5) {

            return 5;
        } else if (size == 6) {

            return 6;
        } else if (size == 7) {

            return 7;
        } else if (size == 8) {

            return 8;
        } else if (size == 9) {

            return 9;
        }


        return article_pic.size();
    }


}
