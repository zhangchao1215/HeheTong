package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastAllLookBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyk on 2017/11/18.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/18
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 查看所有大咖的适配器
 */

public class BigCastLookAllAdapter extends BaseRecyclerAdapter<BigCastAllLookBean.DataBean> {
    private List<BigCastAllLookBean.DataBean.BigMessBean> messBeanList;

    private BigCastPeopleItemAdapter adapter;

    public BigCastLookAllAdapter(List<BigCastAllLookBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.activity_all_bigcast_item);
        messBeanList = new ArrayList<>();

    }

    @Override
    protected void convert(ViewHolder holder, final BigCastAllLookBean.DataBean dataBean, int Position) {

        RecyclerView recyclerView = holder.getView(R.id.bigcast_item_recycler);

        holder.setText(R.id.bigcast_yangsheng, dataBean.getBig_type());


        /**
         * 内部RecyclerView
         */
        messBeanList.clear();
        LinearLayoutManager manager = new LinearLayoutManager(context);

        recyclerView.addItemDecoration(new DividerItemDecoration(context, RecyclerView.HORIZONTAL));

        recyclerView.setLayoutManager(manager);

        messBeanList.addAll(dataBean.getBig_mess());


        adapter = new BigCastPeopleItemAdapter(messBeanList, context);

        recyclerView.setAdapter(adapter);

    }
}
