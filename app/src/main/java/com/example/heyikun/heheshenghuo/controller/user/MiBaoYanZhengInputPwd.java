package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ChiYaoRemindBean;
import com.example.heyikun.heheshenghuo.modle.bean.RegisterBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/22 17:36
 * 修改人:  张超
 * 修改内容:  设置完密保问题中里的验证密保
 * 修改时间: 发送一次手机验证码进行次验证
 */

public class MiBaoYanZhengInputPwd extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.UserPhone_mTextTitle)
    TextView UserPhoneMTextTitle;
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
    private String encryptPhone;
    private ChiYaoRemindBean bean;
    private String phone;
    private TimeCount time;
    private String encryptCodes;
    private String user_id;
    private String AESToken;

    @Override
    protected int layoutId() {
        return R.layout.mibao_yanzheng_activity;
    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("验证密保问题");
        time = new TimeCount(60000, 1000);

        PhoneCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (PhoneCode.getText().length() > 3) {
                    mButNext.setBackgroundResource(R.drawable.queding_but);
                } else
                    mButNext.setBackgroundResource(R.drawable.next_but);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.Image_Back, R.id.mBut_getAuthCode, R.id.mBut_Next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                onBackPressed();

                break;

            //获取验证码
            case R.id.mBut_getAuthCode:
                phone = PhoneNumberEdit.getText().toString().trim();
                if (phone.isEmpty()) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                if (phone.length() > 11 || phone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机位数", Toast.LENGTH_SHORT).show();
                } else {
                    SendAntuCode(phone);
                    time.start();
                }


                break;

            //点击下一步进行操作
            case R.id.mBut_Next:

                String code = PhoneCode.getText().toString().trim();
                if (phone.isEmpty() || PhoneCode.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    YanZhengCode(code);
                }


                break;
        }
    }


    /**
     * 发送验证码
     */
    private void SendAntuCode(String phoneNumber) {

        try {
            encryptPhone = AESUtils.Encrypt(phoneNumber, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "ObtainCode");
        params.put("user_name", encryptPhone);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("MiBaoYanZhengInputPwd", data);
                Gson gson = new Gson();

                bean = gson.fromJson(data, ChiYaoRemindBean.class);

                if (data == null || bean == null) {
                    return;
                } else if (bean.getStatus().equals("200")) {
                    Toast.makeText(MiBaoYanZhengInputPwd.this, bean.getMessage(), Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(MiBaoYanZhengInputPwd.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }


                Log.d("RegisterActivity", data);
            }

            @Override
            public void onError(String error) {

            }
        });


    }

    /**
     * 验证验证码
     */

    private void YanZhengCode(String code) {
        //获取用户id 进行AES加密
        user_id = AppUtils.get().getString("user_id", "");

        Log.d("UserAddressGuanLiActivi", "账号id   " + user_id);

        String token = AppUtils.get().getString("token", "");
        try {
            //把验证码先Base64 在进行AES加密
            String s = Base64.encodeToString(code.getBytes(), Base64.DEFAULT);

            encryptCodes = AESUtils.Encrypt(s, BaseUrl.AESKey);

            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();

            String timestamp = TimeUtils.dataOne(currentTime_today);

            String TwoToken = user_id + "," + token + "," + timestamp;

            //生成二次token 并进行加密

            AESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "CheckCode");
        params.put("user_name", encryptPhone);
        params.put("mobile_code", encryptCodes);
        params.put("token", AESToken);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("MiBaoYanZhengInputPwd", data);
                Gson gson = new Gson();

                RegisterBean bean = gson.fromJson(data, RegisterBean.class);

                if (bean == null || data == null) {
                    return;
                } else if (bean.getStatus().equals("200")) {
                    Intent in = new Intent(MiBaoYanZhengInputPwd.this, MiBaoYanZhengMsg.class);
                    startActivity(in);
                } else {
                    Toast.makeText(MiBaoYanZhengInputPwd.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(String error) {

            }
        });
        //


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
        }

        @Override
        public void onFinish() {
            mButGetAuthCode.setText("点击获取验证码");
            mButGetAuthCode.setClickable(true);
            mButGetAuthCode.setBackground(getResources().getDrawable(R.drawable.next_but));
        }
    }

}
