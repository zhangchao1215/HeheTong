package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

import static com.example.heyikun.heheshenghuo.R.id.mText;

/**
 * Created by hyk on 2017/11/24.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/24
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  大咖说首页的文章适配器
 */

public class BigCastAdapter extends BaseRecyclerAdapter<BigCastBean.DataBean.ArticlesBean.ArticleBean> {
    private BigcastOnClick onClick;

    public void setOnClick(BigcastOnClick onClick) {
        this.onClick = onClick;
    }

    public BigCastAdapter(List<BigCastBean.DataBean.ArticlesBean.ArticleBean> mList, Context context) {
        super(mList, context, R.layout.activity_bigcast_item);
    }

    @Override
    protected void convert(ViewHolder holder, final BigCastBean.DataBean.ArticlesBean.ArticleBean articlesBean, final int Position) {
        ImageView imageView = holder.getView(R.id.Bigcast_Image);

        LinearLayout mLinear = holder.getView(R.id.Bigcast_Add_Linear);
        Glide.with(context)
                .load(articlesBean.getArticle_pic())
                .placeholder(R.drawable.jcwz)
                .centerCrop()
                .into(imageView);

        holder.setText(R.id.Bigcast_Zan, articlesBean.getPraise_count() + "赞" + " . " + articlesBean.getComment_count() + "评论" + " . 已有" + articlesBean.getAtten_count() + "人关注");

        holder.setText(R.id.Bigcast_Content, articlesBean.getArticle_title() + "");

        holder.setText(R.id.Bigcast_Name, articlesBean.getBig_name() + "");


        TextView textView = holder.getView(R.id.Bigcast_Name);

        for (int i = 0; i < articlesBean.getLabel_name().size(); i++) {

            View view = LayoutInflater.from(context).inflate(R.layout.activity_bigcast_biaoqian, null);




            TextView mText = (TextView) view.findViewById(R.id.BigCast_Biaoqian);

            mText.setPadding(3,3,3,3);

            mText.setText(articlesBean.getLabel_name().get(i));

            mLinear.addView(view);

        }


        holder.setOnclickListener(R.id.Title_Relative, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.ShareWebView(context, articlesBean.getArticle_link(), articlesBean.getArticle_title());

            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.OnClickBigcast(Position);
            }
        });

    }


    public interface BigcastOnClick {

        void OnClickBigcast(int position);

    }


}
