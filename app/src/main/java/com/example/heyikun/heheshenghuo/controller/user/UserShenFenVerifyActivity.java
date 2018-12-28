package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 进行身份验证，邮箱验证与密保问题验证
 */
public class UserShenFenVerifyActivity extends BaseActivity {

    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Email_YanZheng)
    RelativeLayout EmailYanZheng;
    @BindView(R.id.MiBao_YanZheng)
    RelativeLayout MiBaoYanZheng;


    @Override
    protected int layoutId() {
        return R.layout.activity_user_shenfen_verify;
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


    @OnClick({R.id.Image_Back, R.id.Email_YanZheng, R.id.MiBao_YanZheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;
            //进行邮箱验证
            case R.id.Email_YanZheng:
                Intent intent = new Intent(this, UserEmailYanZhengActivity.class);
                startActivity(intent);

                break;
            //进行密保问题验证
            case R.id.MiBao_YanZheng:
                Intent miBaoIntent = new Intent(this, UserMiBaoYanZhengActivity.class);
                startActivity(miBaoIntent);


                break;
        }
    }
}
