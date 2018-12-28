package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/26.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/26
 * <p>
 * 3：类描述： 客服页面用来拨打电话
 * <p>
 * 4:类功能：
 */

public class HeheServiceActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.mText1)
    TextView mText1;
    @BindView(R.id.Making_Call)
    Button MakingCall;
    @BindView(R.id.Lianxi_Services_QQ)
    Button LianxiServicesQQ;
    @BindView(R.id.ZaiXian_Services)
    Button ZaiXianServices;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_hehe_service;
    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("客户服务");

        mTextIncludeTitle.setTextColor(getResources().getColor(R.color.Black));

        mTextIncludeTitle.setTextSize(16);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.Image_Back, R.id.Making_Call, R.id.Lianxi_Services_QQ, R.id.ZaiXian_Services})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:

                onBackPressed();
                break;

            //拨打电话
            case R.id.Making_Call:

                String number = "400-100-2368";
                //用intent启动拨打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                startActivity(intent);
                break;

            //联系客服qq
            case R.id.Lianxi_Services_QQ:


                break;

            //在线客服
            case R.id.ZaiXian_Services:


                break;
        }
    }
}
