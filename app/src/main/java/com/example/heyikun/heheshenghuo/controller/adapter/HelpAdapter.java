package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.HelpBean;
import com.example.heyikun.heheshenghuo.modle.util.DividerItemDecorationRecy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyk on 2017/12/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：帮助的适配器
 */

public class HelpAdapter extends BaseRecyclerAdapter<HelpBean.DataBean> {
    private List<HelpBean.DataBean.UseHelpBean> useHelpBeanList;
    private HelpItemAdapter itemAdapter;

    public HelpAdapter(List<HelpBean.DataBean> mList, Context context) {
        super(mList, context, R.layout.user_hehe_userhelp_item);
        useHelpBeanList = new ArrayList<>();
    }

    @Override
    protected void convert(ViewHolder holder, HelpBean.DataBean helpBean, int Position) {

        holder.setText(R.id.help_text_One, helpBean.getHelp_name() + "");

        RecyclerView recyclerView = holder.getView(R.id.help_recycler_item);


        LinearLayoutManager manager = new LinearLayoutManager(context);

        recyclerView.addItemDecoration(new DividerItemDecorationRecy(context, DividerItemDecorationRecy.VERTICAL_LIST));

        recyclerView.setLayoutManager(manager);

        useHelpBeanList.addAll(helpBean.getUse_help());

        itemAdapter = new HelpItemAdapter(useHelpBeanList, context);
        recyclerView.setAdapter(itemAdapter);


    }
}
