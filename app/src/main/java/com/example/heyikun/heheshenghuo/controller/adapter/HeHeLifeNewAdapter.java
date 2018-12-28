package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.CircleProgressView;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeNewLifeBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

/**
 * Created by hyk on 2018/1/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class HeHeLifeNewAdapter extends BaseRecyclerAdapter<HeHeNewLifeBean.DataBean.FireBean> {
    public HeHeLifeNewAdapter(List<HeHeNewLifeBean.DataBean.FireBean> mList, Context context) {
        super(mList, context, R.layout.activity_new_life_result_item);
    }

    @Override
    protected void convert(ViewHolder holder, final HeHeNewLifeBean.DataBean.FireBean fireBean, int Position) {
        CircleProgressView progressView = holder.getView(R.id.newLife_progress);

        holder.setText(R.id.newLife_item_text, fireBean.getTest_name());//上面的button
        holder.setText(R.id.new_life_fen_text, fireBean.getTest_sore());//分数

        progressView.setMaxProgress(100);
        progressView.setProgress(Integer.parseInt(fireBean.getTest_sore()));

        holder.setText(R.id.new_life_result_text, fireBean.getTest_desc());


        holder.setOnclickListener(R.id.quan_Linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebViewUtils.publicWebView(context, fireBean.getVote_link(), fireBean.getTest_name());

            }
        });


    }
}
