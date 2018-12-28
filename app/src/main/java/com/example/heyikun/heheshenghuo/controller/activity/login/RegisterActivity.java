package com.example.heyikun.heheshenghuo.controller.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ChiYaoRemindBean;
import com.example.heyikun.heheshenghuo.modle.bean.RegisterBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.Code;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/19.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.Register_finish)
    TextView RegisterFinish;
    @BindView(R.id.Register_Edit_PhoneNumer)
    EditText RegisterEditPhoneNumer;
    @BindView(R.id.Register_getAuthCode)
    TextView RegisterGetAuthCode;
    @BindView(R.id.Register_Edit_Pwd)
    EditText RegisterEditPwd;
    @BindView(R.id.Register_ButNext)
    Button RegisterButNext;


    @BindView(R.id.mCheckbox)
    CheckBox checkbox;
    @BindView(R.id.text_xieyi)
    TextView textXieyi;
    //产生的验证码
    private String realCode;
    private String etCodes;
    private String editPwd;
    private String userPhone;
    private TimeCount time;
    private String encryptPhone;
    private ChiYaoRemindBean bean;

    @Override
    protected int layoutId() {
        return R.layout.activity_hehe_register;
    }

    @Override
    protected void initView() {

        //生成图片验证码


        time = new TimeCount(60000, 1000);


        //        boolean checked = checkbox.isChecked();
        //
        //        if (checked) {
        //
        //            RegisterButNext.setClickable(false);
        //            RegisterButNext.setBackgroundResource(R.color.color_ccc);
        //
        //        } else {
        //            RegisterButNext.setBackgroundResource(R.color.colorTextYangXIn);
        //            RegisterButNext.setClickable(true);
        //
        //        }
        boolean isChecked = true;
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    RegisterButNext.setClickable(false);
                    RegisterButNext.setBackgroundResource(R.color.color_ccc);
                } else {
                    RegisterButNext.setBackgroundResource(R.color.colorTextYangXIn);
                    RegisterButNext.setClickable(true);
                }


            }
        });

        checkbox.setChecked(isChecked);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {
    }


    @OnClick({R.id.Register_finish, R.id.Register_getAuthCode, R.id.Register_ButNext
            , R.id.text_xieyi, R.id.mCheckbox
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Register_finish:
                finish();
                break;
            case R.id.Register_getAuthCode:
                String phone = RegisterEditPhoneNumer.getText().toString().trim();

                if (phone.isEmpty()) {
                    Toast.makeText(this, "您输入的内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    SendAntuCode();
                }


                break;
            case R.id.Register_ButNext:

                IRegister();


                break;

            case R.id.text_xieyi:
                String url = "https://hehe.heyishenghuo.com/mobile2/article.php?id=842&from=1";

                WebViewUtils.publicWebView(this, url, "用户协议");


                break;
            case R.id.mCheckbox:

                break;

        }
    }

    /**
     * 验证手机验证码
     */

    private void IRegister() {

        userPhone = RegisterEditPhoneNumer.getText().toString().trim();

        editPwd = RegisterEditPwd.getText().toString().trim();


        if (userPhone.isEmpty() || editPwd.isEmpty()) {
            Toast.makeText(this, "输入的内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (userPhone.isEmpty()) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (userPhone.length() > 11 || userPhone.length() < 11) {
            Toast.makeText(this, "手机号输入有误", Toast.LENGTH_SHORT).show();
        } else if (editPwd.isEmpty()) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        //        String BasePwd = Base64.encodeToString(editPwd.getBytes(), Base64.DEFAULT);

        try {
            String iphone = AESUtils.Encrypt(userPhone, BaseUrl.AESKey);

            String s = Base64.encodeToString(editPwd.getBytes(), Base64.DEFAULT);

            String encryptCodes = AESUtils.Encrypt(s, BaseUrl.AESKey);

            Map<String, String> params = new HashMap<>();
            params.put("action", "CheckCode");
            params.put("user_name", iphone);
            params.put("mobile_code", encryptCodes);
            OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
                @Override
                public void onSuccess(String data) {
                    Log.d("RegisterActivity", "验证得到的验证码" + data);
                    Gson gson = new Gson();

                    RegisterBean bean = gson.fromJson(data, RegisterBean.class);

                    if (bean == null || data == null) {
                        return;
                    } else if (bean.getStatus().equals("200")) {
                        Intent in = new Intent(RegisterActivity.this, RegisterNextActivity.class);
                        in.putExtra("Phone", userPhone);
                        startActivity(in);
                    } else {
                        Toast.makeText(RegisterActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onError(String error) {

                }
            });
            //
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送验证码
     */
    private void SendAntuCode() {

        String userPhone = RegisterEditPhoneNumer.getText().toString().trim();

        try {
            encryptPhone = AESUtils.Encrypt(userPhone, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "RegisterCode");
        params.put("user_name", encryptPhone);

        //        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
        //            @Override
        //            public void onSuccess(String data) {
        //                Log.d("RegisterActivity", "得到验证码" + data);
        //
        //                Gson gson = new Gson();
        //
        //                bean = gson.fromJson(data, ChiYaoRemindBean.class);
        //
        //                if (data == null || bean == null) {
        //
        //                    Toast.makeText(RegisterActivity.this, "系统繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
        //                    return;
        //                } else if (bean.getStatus().equals("200")) {
        //
        //                    time.start();
        //
        //                    Toast.makeText(RegisterActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        //
        //
        //                } else {
        //                    Toast.makeText(RegisterActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        //                }
        //
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
                .enqueue(new GsonResponseHandler<ChiYaoRemindBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChiYaoRemindBean response) {
                        if (response == null) {
                            Toast.makeText(RegisterActivity.this, "系统繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (response.getStatus().equals("200")) {
                            time.start();
                            Toast.makeText(RegisterActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }


    //点击的是图片验证码，自己随机生成的
    @Override
    public void onClick(View v) {

    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            RegisterGetAuthCode.setClickable(false);
            RegisterGetAuthCode.setText("(" + millisUntilFinished / 1000 + ") 秒后重新发送");
            RegisterGetAuthCode.setTextColor(getResources().getColor(R.color.color_ccc));
        }

        @Override
        public void onFinish() {
            RegisterGetAuthCode.setText("获取验证码");
            RegisterGetAuthCode.setClickable(true);
            RegisterGetAuthCode.setTextColor(getResources().getColor(R.color.ZhenDuanText));

        }
    }
}
