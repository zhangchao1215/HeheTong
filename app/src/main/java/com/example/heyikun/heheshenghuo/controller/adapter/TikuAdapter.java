package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.TikuImageBean;

import java.util.List;

/**
 * Created by hyk on 2018/1/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/16
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class TikuAdapter extends BaseRecyclerAdapter<TikuImageBean.DataBean.LabelBean> {
    public TikuAdapter(List<TikuImageBean.DataBean.LabelBean> mList, Context context) {
        super(mList, context, R.layout.activity_tiku_result_top_item);
    }

    private OnitemClick onitemClick;

    public void setOnitemClick(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    @Override
    protected void convert(ViewHolder holder, TikuImageBean.DataBean.LabelBean labelBean, final int Position) {

        LinearLayout linearLayout = holder.getView(R.id.Linear);

        linearLayout.setBackgroundResource(R.color.white);

        ImageView image = holder.getView(R.id.tiku_image);

        TextView text = holder.getView(R.id.tiku_ceshi_text);


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemClick.onItemClick(Position);
            }
        });


        Glide.with(context)
                .load(labelBean.getLabel_pic())
                .placeholder(R.drawable.jcwz)
                .into(image);
        holder.setText(R.id.tiku_textOne, labelBean.getVote_name());


    }

    public interface OnitemClick {

        void onItemClick(int position);


    }
}
