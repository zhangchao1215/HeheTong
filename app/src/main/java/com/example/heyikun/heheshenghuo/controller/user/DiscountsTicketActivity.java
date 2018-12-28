package com.example.heyikun.heheshenghuo.controller.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/12.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/12
 * <p>
 * 3：类描述： 我的优惠券
 * <p>
 * 4:类功能：
 */

public class DiscountsTicketActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.mText_price)
    TextView mTextPrice;
    @BindView(R.id.mText_immediate_use)
    TextView mTextImmediateUse;
    @BindView(R.id.mText_price_sj)
    TextView mTextPriceSj;
    @BindView(R.id.mText_immediate_use_sj)
    TextView mTextImmediateUseSj;

    @Override
    protected int layoutId() {
        return R.layout.activity_discounts_ticket;
    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("优惠券账户");
        mTextIncludeTitle.setTextSize(16);
        mTextIncludeTitle.setTextColor(R.color.Black);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.Image_Back, R.id.mText_immediate_use, R.id.mText_immediate_use_sj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;

            //立即使用
            case R.id.mText_immediate_use:
                break;
            case R.id.mText_immediate_use_sj:
                break;
        }
    }
}
