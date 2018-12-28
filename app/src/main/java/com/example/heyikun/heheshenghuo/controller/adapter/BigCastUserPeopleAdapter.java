package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastPeopleDetailBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

/**
 * Created by hyk on 2017/11/25.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/25
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class BigCastUserPeopleAdapter extends BaseRecyclerAdapter<BigCastPeopleDetailBean.DataBean.ArticleBean> {

    private View view;

    public BigCastUserPeopleAdapter(List<BigCastPeopleDetailBean.DataBean.ArticleBean> mList, Context context) {
        super(mList, context, R.layout.activity_bigcast_userpeople_item);
    }

    @Override
    protected void convert(ViewHolder holder, final BigCastPeopleDetailBean.DataBean.ArticleBean articleBean, int Position) {

        ImageView imageView = holder.getView(R.id.Bigcast_Image);

        LinearLayout mLinear = holder.getView(R.id.Bigcast_Add_Linear);

        Glide.with(context)
                .load(articleBean.getArticle_pic())
                .placeholder(R.drawable.jcwz)
                .centerCrop()
                .into(imageView);


        holder.setText(R.id.Bigcast_Content, articleBean.getArticle_title() + "");
        holder.setText(R.id.Bigcast_Zan, articleBean.getPraise_count() + "赞 . " + articleBean.getComment_count() + "评论");

        int size = articleBean.getLabel_name().size();
        for (int i = 0; i < size; i++) {

            view = LayoutInflater.from(context).inflate(R.layout.activity_bigcast_biaoqian, null);

            TextView mText = (TextView) view.findViewById(R.id.BigCast_Biaoqian);

            mText.setText(articleBean.getLabel_name().get(i));

            mLinear.addView(view);

        }



        holder.setOnclickListener(R.id.Title_Relative, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.bigCastWebView(context, articleBean.getArticle_link(), articleBean.getArticle_title());

            }


        });


    }

}
