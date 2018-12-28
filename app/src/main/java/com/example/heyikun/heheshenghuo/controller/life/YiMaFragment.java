package com.example.heyikun.heheshenghuo.controller.life;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/12.
 */

public class YiMaFragment extends BaseFragment {
    @BindView(R.id.mText)
    TextView mText;
    @BindView(R.id.Base_RecyclerView)
    RecyclerView BaseRecyclerView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_recyclerview;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        mText.setVisibility(View.VISIBLE);

        mText.setText("暂时没有数据");
    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.mText)
    public void onViewClicked() {
    }
}
