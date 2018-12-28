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
 * Created by hyk on 2017/9/25.
 * 类描述： 重置密码，手机验证，邮箱以及密码验证，选择其中一种验证
 * <p>
 * 类功能： 选择其中一种方式去重置密码，去进行验证
 */

public class UserChongZhiPassWordActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Phone_verify)
    RelativeLayout PhoneVerify;
    @BindView(R.id.Mailbox_verify)
    RelativeLayout MailboxVerify;
    @BindView(R.id.MiBaoMessage_verify)
    RelativeLayout MiBaoMessageVerify;
    @BindView(R.id.Person_ShenSu)
    TextView PersonShenSu;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_chongzhi_pwd;
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


    @OnClick({R.id.Image_Back, R.id.Phone_verify, R.id.Mailbox_verify, R.id.MiBaoMessage_verify, R.id.Person_ShenSu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();
                break;
            //手机号验证
            case R.id.Phone_verify:
                Intent intent = new Intent(this, PhoneVerifyPwdActivity.class);
                startActivity(intent);

                break;
            //发送邮箱进行验证
            case R.id.Mailbox_verify:
                Intent EmailIntent = new Intent(this, UserMailboxBingDingActivity.class);
                startActivity(EmailIntent);

                break;
            case R.id.MiBaoMessage_verify:
                break;

            case R.id.Person_ShenSu:

                break;

        }
    }


}
