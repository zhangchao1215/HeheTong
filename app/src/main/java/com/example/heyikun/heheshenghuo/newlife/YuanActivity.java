package com.example.heyikun.heheshenghuo.newlife;

import android.os.Bundle;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.util.CriceView;

import butterknife.BindView;
import butterknife.ButterKnife;

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

public class YuanActivity extends BaseActivity {
    @BindView(R.id.yuan)
    CriceView yuan;

    @Override
    protected int layoutId() {
        return R.layout.yuan;
    }

    @Override
    protected void initView() {
        yuan.setPosition(75/100);

        yuan.setCriceWidth(1);
        yuan.setStokeWidth(8);
        yuan.setCriceColor(getResources().getColor(R.color.colorTextYangXIn));

        yuan.setStokeColor(getResources().getColor(R.color.quan_xinzang));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
