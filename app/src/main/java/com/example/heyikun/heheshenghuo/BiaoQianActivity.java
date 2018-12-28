package com.example.heyikun.heheshenghuo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;

import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hyk on 2017/9/16.
 */

public class BiaoQianActivity extends BaseActivity {
    @BindView(R.id.mGridLayout)
    MyGridLayout mGridLayout;
    private List<String> mList;

    @Override
    protected int layoutId() {
        return R.layout.gridlayout;
    }

    @Override
    protected void initView() {


        mList = new ArrayList<>();

        mList.add("大姨妈");
        mList.add("感冒");
        mList.add("吃药，打针");
        mList.add("这是什么情况");

        mGridLayout.setmList(mList);

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
