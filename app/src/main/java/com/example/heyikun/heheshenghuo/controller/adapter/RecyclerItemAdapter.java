package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.TypeIdBean;

import java.util.List;

/**
 * Created by hyk on 2017/9/16.
 */

public class RecyclerItemAdapter extends BaseRecyclerAdapter<TypeIdBean.DataBean> {
    public RecyclerItemAdapter(List<TypeIdBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.activity_recycleritem_one);
    }

    @Override
    protected void convert(final ViewHolder holder, final TypeIdBean.DataBean dataBean, final int Position) {
        holder.setText(R.id.mRecycler_ItemRB_One, dataBean.getDiag_name());
        holder.setOnclickListener(R.id.mRecyclerView_Recycler, new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }


}
