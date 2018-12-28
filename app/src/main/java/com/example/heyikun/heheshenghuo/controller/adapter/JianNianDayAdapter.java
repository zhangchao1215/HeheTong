package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.MainBean;

import java.util.List;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/15 17:47
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

public class JianNianDayAdapter extends BaseRecyclerAdapter<MainBean.DataBean.DayBean> {

    private int width = 0;

    public JianNianDayAdapter(List<MainBean.DataBean.DayBean> mList, Context context, int width) {
        super(mList, context, R.layout.activity_shouye_birthday_item);

        this.width = width;
    }

    @Override
    public void convert(ViewHolder holder, MainBean.DataBean.DayBean dayBean, int Position) {

        RelativeLayout mRelative = holder.getView(R.id.Day_relative);

        String table = dayBean.getTable();

        TextView textView = holder.getView(R.id.mText_JieHun);

        if (textView == null) {
            return;
        }
        textView.setText(Html.fromHtml(table));
        if (width != 0) {

            ViewGroup.LayoutParams layoutParams = mRelative.getLayoutParams();

            layoutParams.width = width;

            mRelative.setLayoutParams(layoutParams);
        }

    }
}
