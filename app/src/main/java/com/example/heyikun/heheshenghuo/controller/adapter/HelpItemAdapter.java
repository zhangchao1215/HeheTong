package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.HelpBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

/**
 * Created by hyk on 2017/12/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class HelpItemAdapter extends BaseRecyclerAdapter<HelpBean.DataBean.UseHelpBean> {
    public HelpItemAdapter(List<HelpBean.DataBean.UseHelpBean> mList, Context context) {
        super(mList, context, R.layout.user_hehe_userhelp_item_two);
    }

    @Override
    protected void convert(ViewHolder holder, final HelpBean.DataBean.UseHelpBean useHelpBean, int Position) {

        holder.setText(R.id.help_text_Two, useHelpBean.getHelp_title() + "");
        holder.setOnclickListener(R.id.help_linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewUtils.publicWebView(context, useHelpBean.getHelp_link(), useHelpBean.getHelp_title());

            }
        });

    }
}
