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
import com.example.heyikun.heheshenghuo.modle.bean.RegisterBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/27.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/27
 * <p>
 * 3：类描述： 进行更换手机号，验证手机验证码
 * <p>
 * 4:类功能： 输入手机号获取验证码。再点击下一步之后，在进行验证新的手机号
 */

public class UserPhoneNumberChangeActivity extends BaseActivity {

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
    @BindView(R.id.mText_ShenFen_Verify)
    TextView mTextShenFenVerify;
    @BindView(R.id.NewPhoneNumber_Linear)
    RelativeLayout NewPhoneNumberLinear;
    private TimeCount time;
    private String phoneNumber;

    private String encryptPhone;
    private String AESToken;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_phone_verify;
    }

    @Override
    protected void initView() {
        mTextShenFenVerify.setVisibility(View.VISIBLE);

        time = new TimeCount(60000, 1000);

        mTextIncludeTitle.setText("更换绑定手机");

        UserPhoneMTextTitle.setText("请确保手机畅通，输入以绑定的手机号");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.mBut_getAuthCode, R.id.mBut_Next, R.id.mText_ShenFen_Verify, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.Image_Back:
                finish();
                break;


            case R.id.mBut_getAuthCode:

                phoneNumber = PhoneNumberEdit.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();

                }
                if (phoneNumber.length() > 11 || phoneNumber.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();

                } else {
                    mButNext.setBackground(getResources().getDrawable(R.drawable.queding_but));
                    //                    time.start();
                    getPhoneCode(phoneNumber);
                }


                break;
            case R.id.mBut_Next:

                String phone = PhoneNumberEdit.getText().toString().trim();
                String mCode = PhoneCode.getText().toString().trim();
                Log.d("UserPhoneNumberChangeAc", mCode);

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(mCode)) {
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone.length() > 11 || phone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mCode)) {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    YanZhengCode(mCode);
                }


                break;

            //点击进行身份验证，没有手机号的情况下
            case R.id.mText_ShenFen_Verify:
                Intent intent = new Intent(this, UserShenFenVerifyActivity.class);
                startActivity(intent);

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
    private void getPhoneCode(String phone) {
        Map<String, String> params = new HashMap<>();
        try {

            encryptPhone = AESUtils.Encrypt(phone, BaseUrl.AESKey);

            Log.d("UserPhoneNumberChangeAc", "加密的手机号" + encryptPhone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("action", "ObtainCode");
        params.put("user_name", encryptPhone);

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {

                            time.start();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }


    /**
     * 验证验证码
     */

    /**
     * 验证验证码
     */

    private void YanZhengCode(String code) {
        //获取用户id 进行AES加密
        String user_id = AppUtils.get().getString("user_id", "");


        String token = AppUtils.get().getString("token", "");
        String encryptCodes = null;
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
        //        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
        //            @Override
        //            public void onSuccess(String data) {
        //                Log.d("UserPhoneNumberChangeAc", data);
        //                Gson gson = new Gson();
        //
        //                RegisterBean bean = gson.fromJson(data, RegisterBean.class);
        //
        //                if (bean == null || data == null) {
        //                    return;
        //                } else if (bean.getStatus().equals("200")) {
        //
        //                    //进行身份验证 ？？？
        //                    Intent intent = new Intent(UserPhoneNumberChangeActivity.this, UserShenFenVerifyActivity.class);
        //
        //                    startActivity(intent);
        //                } else {
        //                    Toast.makeText(UserPhoneNumberChangeActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
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
        //
        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.d("UserPhoneNumberChangeAc", response);

                        Gson gson = new Gson();


                        ChangePwdBean changePwdBean = gson.fromJson(response, ChangePwdBean.class);

                        if (changePwdBean.getStatus().equals("200")) {

                            Intent intent = new Intent(UserPhoneNumberChangeActivity.this,UserPhoneNumberNewChangeActivity.class);
                            startActivity(intent);

                            Toast.makeText(UserPhoneNumberChangeActivity.this, changePwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserPhoneNumberChangeActivity.this, changePwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }


}
