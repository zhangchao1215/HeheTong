package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBSettringBean;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/25.
 * 类描述：账号安全，
 * 类功能：功能有去进行修改密码，绑定qq， 微信，微博，以及解除绑定。
 */

public class AccountSecurityActivity extends BaseActivity {
    @BindView(R.id.User_Account_Message)
    RelativeLayout UserAccountMessage;
    @BindView(R.id.User_ChangePwd)
    RelativeLayout UserChangePwd;
    @BindView(R.id.User_MiBaoMessage)
    RelativeLayout UserMiBaoMessage;
    @BindView(R.id.User_Account_BangDing)
    RelativeLayout UserAccountBangDing;
    @BindView(R.id.User_Change_PhoneNumber)
    TextView UserChangePhoneNumber;
    @BindView(R.id.User_BangDing_Mailbox)
    TextView UserBangDingMailbox;
    @BindView(R.id.User_Click_BangDing)
    TextView UserClickBangDing;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_MiBao_Noset)
    TextView mTextMiBaoNoset;
    @BindView(R.id.Apay_Pwd)
    RelativeLayout ApayPwd;
    @BindView(R.id.pass_text)
    TextView passText;
    @BindView(R.id.user_Phone)
    TextView userPhone;
    private String mEmail;
    private final String ANSWER_ONE = "1";
    private final String ANSWER_ZERO = "0";
    private String answer;
    private String pass;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_account_anquan;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        pass = AppUtils.get().getString("pass", "");

        mEmail = AppUtils.get().getString("Email", "");

        if ("".equals(mEmail)) {
            UserClickBangDing.setText("点击绑定");
            UserBangDingMailbox.setText("绑定邮箱");

        } else {
            UserClickBangDing.setText("以绑定");

            UserBangDingMailbox.setText("修改邮箱");
        }


        if ("0".equals(pass)) {
            passText.setText("未设置");
        } else if ("1".equals(pass)) {
            passText.setText("修改密码");
        }

        String phone = AppUtils.get().getString("phone", "");

        Log.d("AccountSecurityActivity", "手机号   " + phone);

        if ("".equals(phone)) {


        } else {
            StringBuilder sb = new StringBuilder(phone);

            //            取中间四位
            sb.replace(3, 7, "****");
            //            取后四位
            sb.substring(7, 11);

            userPhone.setText(sb.toString());
        }

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initLisenter() {

    }

    /**
     * 跳转回传
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {

            String answer = data.getStringExtra("SuccessAnswer");

            mTextMiBaoNoset.setText(answer);

            Intent MIBaointent = new Intent(this, UserMainMiBaoMessageActivity.class);
            startActivity(MIBaointent);

        }

    }

    @OnClick({R.id.User_ChangePwd, R.id.User_MiBaoMessage, R.id.User_Change_PhoneNumber,
            R.id.Image_Back, R.id.Apay_Pwd,
            R.id.User_BangDing_Mailbox, R.id.User_Click_BangDing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();
                break;

            //修改密码
            case R.id.User_ChangePwd:
                Intent intent = new Intent(this, UserChangePwdActivity.class);
                startActivity(intent);

                break;
            //设置密保问题
            case R.id.User_MiBaoMessage:
                //为1时就是设置了密保问题 , 0 就是没有设置
                if (answer.equals(ANSWER_ONE)) {
                    //设置完之后跳转到更换密保问题页面
                    Intent mIntent = new Intent(this, UserMainMiBaoMessageActivity.class);
                    startActivity(mIntent);
                } else if (answer.equals(ANSWER_ZERO)) {
                    //跳转到设置密保问题页面
                    Intent MIBaointent = new Intent(this, UserSettingMiBaoActivity.class);
                    startActivity(MIBaointent);
                }


                break;
            //更换手机号
            case R.id.User_Change_PhoneNumber:


                Intent PhoneIntent = new Intent(this, UserPhoneNumberChangeActivity.class);
                startActivity(PhoneIntent);


                break;

            //更换邮箱
            case R.id.User_BangDing_Mailbox:

                //如果不为空就更换绑定邮箱
                if ("".equals(mEmail)) {

                } else {
                    Intent mailboxIntent = new Intent(this, UserChangeEmailActivity.class);

                    startActivity(mailboxIntent);
                }


                break;

            //点击进行绑定邮箱
            case R.id.User_Click_BangDing:

                if ("".equals(mEmail)) {
                    Intent intent1 = new Intent(this, UserMailboxBingDingActivity.class);
                    startActivity(intent1);

                } else {

                }


                break;

            //支付密码
            case R.id.Apay_Pwd:

                if ("0".equals(pass)) {
                    Intent apayIntent = new Intent(this, SetApayPasswordActivity.class);
                    startActivity(apayIntent);
                } else if ("1".equals(pass)) {
                    Intent intent2 = new Intent(this, UserChangePwd_PayActivity.class);
                    startActivity(intent2);

                }


                break;
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void SettingMaThread(EBSettringBean bean) {
        answer = bean.getAnswer();

        Log.d("AccountSecurityActivity", answer);

        String email = bean.getEmail();
        if (email.equals("")) {
            UserClickBangDing.setVisibility(View.VISIBLE);
        } else {
            UserClickBangDing.setVisibility(View.GONE);
        }

        if (answer.equals(ANSWER_ONE)) {
            mTextMiBaoNoset.setText("已设置");
            //设置完之后跳转到更换密保问题页面

        } else if (answer.equals(ANSWER_ZERO)) {
            mTextMiBaoNoset.setText("未设置");
            //跳转到设置密保问题页面

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
