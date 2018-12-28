package com.example.heyikun.heheshenghuo.newlife;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BaseRecyclerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.TikuImageBean;

import java.util.List;

/**
 * Created by hyk on 2018/1/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class HeHetiKuiResultAdapter extends BaseRecyclerAdapter<TikuImageBean.DataBean.TestsBean> {
    public HeHetiKuiResultAdapter(List<TikuImageBean.DataBean.TestsBean> mList, Context context) {
        super(mList, context, R.layout.activity_tiku_result_item);
    }

    public TikuOnClick tikuOnClick;

    public void setTikuOnClick(TikuOnClick tikuOnClick) {
        this.tikuOnClick = tikuOnClick;
    }

    @Override
    protected void convert(ViewHolder holder, TikuImageBean.DataBean.TestsBean imageBean, final int Position) {

        ImageView image = holder.getView(R.id.tiku_image);

        holder.setText(R.id.tiku_textOne, imageBean.getVote_name());

        TextView text = holder.getView(R.id.tiku_ceshi_text);


        holder.setText(R.id.tiku_textTwo, imageBean.getTotal_peo() + "人测过 . 准确率" + imageBean.getRight_num() + "%");

        int is_state = imageBean.getIs_state();
        switch (is_state) {
            case 0:
                text.setText("去测试");
                text.setTextColor(context.getResources().getColor(R.color.colorCeshi));

                break;

            case 1:
                text.setText("再测试");
                text.setTextColor(context.getResources().getColor(R.color.colorTextYangXIn));

                break;
        }


        Glide.with(context)
                .load(imageBean.getVote_pic())
                .centerCrop()
                .placeholder(R.drawable.jcwz)
                .into(image);

        //        holder.getText();

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tikuOnClick.onItemClick(Position);
            }
        });


    }

    public interface TikuOnClick {
        void onItemClick(int position);
    }

}
