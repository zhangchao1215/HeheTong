package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ChiYaoRemindBean;
import com.example.heyikun.heheshenghuo.modle.bean.RegisterBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
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
 * 修改时间: 发送一次手机验证码进行次验证,验证完之后删除密保信息
 */

public class MiBaoMsgDeleteActivity extends BaseActivity implements View.OnClickListener {
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
    String userid = null;
    private PopupWindow popupWindow;

    @Override
    protected int layoutId() {
        return R.layout.mibao_yanzheng_activity;
    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("删除密保信息");
        time = new TimeCount(60000, 1000);


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
                Log.d("MiBaoMsgDeleteActivity", data);

                Gson gson = new Gson();

                bean = gson.fromJson(data, ChiYaoRemindBean.class);

                if (data == null || bean == null) {
                    return;
                } else if (bean.getStatus().equals("200")) {
                    Toast.makeText(MiBaoMsgDeleteActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(MiBaoMsgDeleteActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
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

                Log.d("MiBaoMsgDeleteActivity", data);
                Gson gson = new Gson();

                RegisterBean bean = gson.fromJson(data, RegisterBean.class);

                if (bean == null || data == null) {
                    return;
                } else if (bean.getStatus().equals("200")) {

                    mPopWindow();
                } else {
                    Toast.makeText(MiBaoMsgDeleteActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(String error) {

            }
        });
        //


    }

    /**
     * 删除密保信息
     */

    private void DeleteMiBao() {
        String user_id = AppUtils.get().getString("user_id", "");

        Log.d("MiBaoMsgDeleteActivity", user_id);

        String token = AppUtils.get().getString("token", "");

        Log.d("MiBaoMsgDeleteActivity", token);
        String mAESToken = null;
        String encryptAppSignSet = null;
        String userid = null;
        try {
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();

            String timestamp = TimeUtils.dataOne(currentTime_today);

            String TwoToken = user_id + "," + token + "," + timestamp;

            //生成二次token 并进行加密

            mAESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            String app_sign = "DelSecurity" + timestamp + BaseUrl.AESKey;

            String encryptMd5 = MD5Utils.encrypt(app_sign);

            encryptAppSignSet = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "DelSecurity");
        params.put("user_id", userid);
        params.put("token", mAESToken);
        params.put("app_sign", encryptAppSignSet);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("MiBaoMsgDeleteActivity", data);

                Gson gson = new Gson();
                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (!bean.getStatus().equals("200")) {
                    Toast.makeText(MiBaoMsgDeleteActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(MiBaoMsgDeleteActivity.this, AccountSecurityActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }


            }

            @Override
            public void onError(String error) {

            }
        });


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
            mButGetAuthCode.setBackgroundResource(R.drawable.next_but);
        }

        @Override
        public void onFinish() {
            mButGetAuthCode.setText("点击获取验证码");
            mButGetAuthCode.setClickable(true);
        }
    }

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_yanzheng_mibao_ppw, null);

        Button button = (Button) view.findViewById(R.id.mBut_meThink);

        button.setOnClickListener(this);

        Button Tv = (Button) view.findViewById(R.id.mBut_delete);

        Tv.setOnClickListener(this);


        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        popupWindow.setOutsideTouchable(false);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);

        //popupwindow的弹出位置
        popupWindow.showAtLocation(findViewById(R.id.main_Linear), Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }


    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }


    /**
     * button的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //我在想想
            case R.id.mBut_meThink:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    backgroundAlpha(1.0f);
                }

                break;
            //确定删除
            case R.id.mBut_delete:
                DeleteMiBao();


                break;
        }
    }
}
