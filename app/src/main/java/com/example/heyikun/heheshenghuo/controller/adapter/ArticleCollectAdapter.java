package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.ArticleCollectBean;

import java.util.List;

/**
 * Created by hyk on 2017/11/1.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/1
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ArticleCollectAdapter extends RecyclerView.Adapter<ArticleCollectAdapter.MviewHodler> {

    private CheckView checkView;

    private List<ArticleCollectBean.DataBean.ArticleBean> mList;

    private Context context;

    public ArticleCollectAdapter(List<ArticleCollectBean.DataBean.ArticleBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public void setCheckView(CheckView checkView) {
        this.checkView = checkView;
    }

    @Override
    public MviewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_user_collect_artice, null);
        MviewHodler hodler = new MviewHodler(view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MviewHodler holder, int position) {

        ArticleCollectBean.DataBean.ArticleBean articleBean = mList.get(position);
        holder.mTextTime.setText(articleBean.getAdd_time());
        holder.mTextType.setText(articleBean.getTitle());
        holder.mTextContent.setText(articleBean.getTitle());
        holder.mTextauthor.setText(articleBean.getAuthor());
        Glide.with(context)
                .load(articleBean.getPic_url())
                .placeholder(R.drawable.jcwz)
                .into(holder.mImage);

        checkView.checkBoxView(holder.checkBox);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MviewHodler extends RecyclerView.ViewHolder {
        private TextView mTextDelete, mTextTitle, mTextType, mTextContent, mTextTime, mTextauthor;
        private ImageView mImage;
        private CheckBox checkBox;

        public MviewHodler(View convertView) {
            super(convertView);

            mTextauthor = (TextView) convertView.findViewById(R.id.Collect_Name);
            mImage = (ImageView) convertView.findViewById(R.id.Collect_Image);
            mTextType = (TextView) convertView.findViewById(R.id.TextTitle);
            mTextTime = (TextView) convertView.findViewById(R.id.Collect_Time);
            mTextContent = (TextView) convertView.findViewById(R.id.Collect_TextTitle);
            mTextDelete = (TextView) convertView.findViewById(R.id.mText_cancel);
            checkBox = (CheckBox) convertView.findViewById(R.id.mImage_Duigou);
        }

    }

    public interface CheckView {

        void checkBoxView(View view);

    }


}
