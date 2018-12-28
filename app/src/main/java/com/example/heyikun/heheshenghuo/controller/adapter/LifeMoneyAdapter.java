package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;

import com.example.heyikun.heheshenghuo.R;

import java.util.List;

/**
 * Created by hyk on 2017/10/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/14
 * <p>
 * 3：类描述：首页财富升级的适配器
 * <p>
 * 4:类功能：
 */

public class LifeMoneyAdapter extends BaseRecyclerAdapter<LifeMoneyBean> {

    public LifeMoneyAdapter(List<LifeMoneyBean> mList, Context context) {
        super(mList, context, R.layout.activity_life_money_textitem);
    }

    @Override
    public void convert(ViewHolder holder, LifeMoneyBean Bean, int Position) {


    }
}
