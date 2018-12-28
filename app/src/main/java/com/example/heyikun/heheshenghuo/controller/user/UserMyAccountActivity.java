package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/5 21:13
 * 修改人:  张超
 * 修改内容: 我的账户
 * 修改时间:
 */

public class UserMyAccountActivity extends BaseActivity {
    @BindView(R.id.mText_Recharge)
    TextView mTextRecharge;
    @BindView(R.id.mText_TiXian)
    TextView mTextTiXian;
    @BindView(R.id.mText_Time)
    TextView mTextTime;
    @BindView(R.id.mText_ShoppingTime)
    TextView mTextShoppingTime;
    @BindView(R.id.mText_TuiKuanTime)
    TextView mTextTuiKuanTime;
    @BindView(R.id.mText_account_detail)
    TextView mTextAccountDetail;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;

    @Override
    protected int layoutId() {
        return R.layout.user_hehe_myaccount;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.mText_Recharge, R.id.mText_TiXian, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mText_Recharge:
                Intent intent = new Intent(this, UserAccountRechargeActivity.class);
                startActivity(intent);

                break;

            //提现
            case R.id.mText_TiXian:
                Intent in = new Intent(this, UserAccountTiXianActivity.class);
                startActivity(in);


                break;

            //查看账户详情
            case R.id.mText_account_detail:

                break;

            case R.id.Image_Back:
                finish();
                break;
        }
    }



}
