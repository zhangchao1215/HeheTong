package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/11
 * <p>
 * 3：类描述：提现
 * <p>
 * 4:类功能： 支付宝提现，银行卡提现，以及提现记录
 */

public class UserAccountTiXianActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.TiXian_toaliPay)
    RelativeLayout TiXianToaliPay;
    @BindView(R.id.Tixian_bankCard)
    TextView TixianBankCard;
    @BindView(R.id.Tixian_record)
    RelativeLayout TixianRecord;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_account_tixian;
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


    @OnClick({R.id.Image_Back, R.id.TiXian_toaliPay, R.id.Tixian_bankCard, R.id.Tixian_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;

            //提现到支付宝
            case R.id.TiXian_toaliPay:

                Intent intent = new Intent(this, AliPayTiXianActivity.class);
                startActivity(intent);

                break;

            //提现到银行卡
            case R.id.Tixian_bankCard:
                Intent bankIntent = new Intent(this, BankCardWithdrawActivity.class);
                startActivity(bankIntent);

                break;

            //提现记录
            case R.id.Tixian_record:

                Intent WithIntent = new Intent(this, WithdrawalrecordActivity.class);
                startActivity(WithIntent);

                break;
        }
    }
}
