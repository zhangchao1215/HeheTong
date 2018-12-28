package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBPhoneBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/25.
 * 类描述： 验证手机号
 * 类功能： 验证手机号，发送验证码进行验证 ，验证后跳转到设置支付密码
 */

public class SetApayPasswordActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_Number)
    TextView mTextNumber;
    @BindView(R.id.PhoneNumber_Edit)
    EditText PhoneNumberEdit;
    @BindView(R.id.Remove_PhoneNumberImage)
    ImageView RemovePhoneNumberImage;
    @BindView(R.id.mBut_getAuthCode)
    Button mButGetAuthCode;
    @BindView(R.id.Phone_Code)
    EditText PhoneCode;
    @BindView(R.id.mBut_Next)
    Button mButNext;
    @BindView(R.id.mText_ShenFen_Verify)
    TextView mTextShenFenVerify;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.UserPhone_mTextTitle)
    TextView UserPhoneMTextTitle;
    @BindView(R.id.NewPhoneNumber_Linear)
    RelativeLayout NewPhoneNumberLinear;
    private String code;
    private String phoneNumber;
    private TimeCount time;
    private String encryptPhone;
    private EBPhoneBean phonebean;
    private String encryptCode;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_phone_verify;
    }

    @Override
    protected void initView() {
        time = new TimeCount(60000, 1000);

        mTextIncludeTitle.setText("设置支付密码");

        mTextIncludeTitle.setTextColor(getResources().getColor(R.color.Black));

        getEditMessage();

        phonebean = new EBPhoneBean();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {


    }

    private void getEditMessage() {

        phoneNumber = PhoneNumberEdit.getText().toString().trim();
        code = PhoneCode.getText().toString().trim();
    }


    @OnClick({R.id.mBut_getAuthCode, R.id.mBut_Next, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();


                break;
            case R.id.mBut_getAuthCode:
                phoneNumber = PhoneNumberEdit.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNumber.length() > 11 || phoneNumber.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mButNext.setBackground(getResources().getDrawable(R.drawable.queding_but));
                    getPhoneCode(phoneNumber);

                }


                break;
            case R.id.mBut_Next:

                String Phone = PhoneNumberEdit.getText().toString().trim();
                String PhoneCode = this.PhoneCode.getText().toString().trim();
                if (TextUtils.isEmpty(Phone) || TextUtils.isEmpty(PhoneCode)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (Phone.length() > 11 || Phone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(PhoneCode)) {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    MobileCode(PhoneCode);

                }

                break;
        }
    }


    //倒计时发送验证码
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            mButGetAuthCode.setClickable(false);
            mButGetAuthCode.setText("(" + millisUntilFinished / 1000 + ")秒后重新发送");
            mButGetAuthCode.setBackground(getResources().getDrawable(R.drawable.next_but));
        }

        @Override
        public void onFinish() {
            mButGetAuthCode.setText("点击获取验证码");
            mButGetAuthCode.setClickable(true);
            mButGetAuthCode.setBackground(getResources().getDrawable(R.drawable.queding_but));
        }
    }

    /**
     * 发送手机短信验证码
     */
    private void getPhoneCode(final String phone) {

        Map<String, String> params = new HashMap<>();
        try {
            encryptPhone = AESUtils.Encrypt(phone, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("UserPhoneNumberChangeAc", "加密的手机号" + encryptPhone);

        params.put("action", "ObtainCode");
        params.put("user_name", encryptPhone);

        //            OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
        //                @Override
        //                public void onSuccess(String data) {
        //                    Log.d("SetApayPasswordActivity", data);
        //
        //                    Gson gson = new Gson();
        //                    ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);
        //
        //                    if (data == null || bean == null) {
        //                        return;
        //                    } else if (bean.getStatus().equals("200")) {
        //
        //                    } else {
        //                        Log.d("PhoneVerifyPwdActivity", bean.getMessage());
        //                    }
        //
        //
        //                }
        //
        //                @Override
        //                public void onError(String error) {
        //
        //                }
        //            });
        //


        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {
                            Toast.makeText(SetApayPasswordActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                            time.start();
                            phonebean.setPhone(phone);

                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }


    private void MobileCode(String code) {


        String BaseCode = Base64.encodeToString(code.getBytes(), Base64.DEFAULT);
        try {
            encryptCode = AESUtils.Encrypt(BaseCode, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();

        params.put("action", "CheckCode");
        params.put("user_name", encryptPhone);
        params.put("mobile_code", encryptCode);

        //        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
        //            @Override
        //            public void onSuccess(String data) {
        //                Log.d("SetApayPasswordActivity", data);
        //                Gson gson = new Gson();
        //
        //                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);
        //
        //                if (bean == null || data == null) {
        //                    return;
        //                } else if (bean.getStatus().equals("200")) {
        //
        //
        //                }
        //
        //            }
        //
        //            @Override
        //            public void onError(String error) {
        //
        //            }
        //        });


        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {
                            Intent intent = new Intent(SetApayPasswordActivity.this, UserSetPayPwdActivity.class);
                            EventBus.getDefault().postSticky(phonebean);
                            startActivity(intent);
                            finish();

                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }


}
