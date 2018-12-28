package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.SendArticleBean;

import java.util.List;

/**
 * Created by hyk on 2017/12/18.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/18
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class SendArticelAdaoter extends BaseRecyclerAdapter<SendArticleBean.DataBean> {
    private int mSelectedPos = -1;

    public SendArticelAdaoter(List<SendArticleBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.activity_sendarticle_item);
    }

    @Override
    protected void convert(ViewHolder holder, SendArticleBean.DataBean dataBean, final int Position) {

        holder.setText(R.id.sendArticle_text, dataBean.getCat_name());

        final TextView textView = holder.getView(R.id.sendArticle_text);

        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                mSelectedPos = Position;

                notifyDataSetChanged();

                Drawable drawable = context.getResources().getDrawable(R.drawable.fankui_man_4x);

                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);

                textView.setCompoundDrawablePadding(6);

            }
        });

    }
}
