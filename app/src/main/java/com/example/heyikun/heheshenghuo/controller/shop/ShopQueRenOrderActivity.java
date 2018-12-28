package com.example.heyikun.heheshenghuo.controller.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.user.UserAddressGuanLiActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/11/29.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/29
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 确认订单，之后再到最终订单页面
 */

public class ShopQueRenOrderActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_change_address)
    TextView tvChangeAddress;
    @BindView(R.id.relative1)
    RelativeLayout relative1;
    @BindView(R.id.tv_shouhuo_address)
    TextView tvShouhuoAddress;
    @BindView(R.id.shop_image)
    ImageView shopImage;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.shop_tv_count)
    TextView shopTvCount;
    @BindView(R.id.heji)
    TextView heji;
    @BindView(R.id.shop_mbut_summit)
    Button shopMbutSummit;
    @BindView(R.id.relative2)
    RelativeLayout relative2;

    @Override
    protected int layoutId() {
        return R.layout.activity_shop_queren_order;
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



    @OnClick({R.id.Image_Back, R.id.tv_change_address, R.id.shop_mbut_summit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:

                finish();
                break;

            //更改支付地址
            case R.id.tv_change_address:

                Intent in = new Intent(this, UserAddressGuanLiActivity.class);
                startActivity(in);



                break;
            case R.id.shop_mbut_summit:

                Intent intent = new Intent(this,ShopYesPayActivity.class);
                startActivity(intent);

                break;
        }
    }
}
