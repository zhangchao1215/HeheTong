package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBPhoneBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/26.
 * 1：创建人hyk ： 张超
 * 2：创建时间2017/9/26
 * <p>
 * <p>
 * 3：类描述： 重新设置登录密码 。通过手机验证码进行重置密码
 * 4: 通过手机验证码验证过后，在重新设置新的密码
 * 5:
 */

public class UserChongZhi_PhoneVerifyPwd extends BaseActivity {
    @BindView(R.id.ChongZhi_NewPwdedit)
    EditText NewPwdedit;
    @BindView(R.id.ChongZhiNextNewPwd)
    EditText NextNewPwd;
    @BindView(R.id.mButChongZhi_commit)
    Button mButChongZhiCommit;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.ChongZhi_PhoneVerify_Linear)
    LinearLayout ChongZhiPhoneVerifyLinear;
    private PopupWindow ppw;
    private String encryptPwd;
    private String encryptS;
    private String phone;
    private String encryptPhone;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_phoneverify_chongzhipwd;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        Log.d("UserChongZhi_PhoneVerif", phone);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {
        NextNewPwd.addTextChangedListener(new EditChangedListener());

        ImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @OnClick(R.id.mButChongZhi_commit)
    public void onViewClicked() {


        String newPwdedit = NewPwdedit.getText().toString().trim();

        String nextPwdedit = NextNewPwd.getText().toString().trim();

        if (TextUtils.isEmpty(newPwdedit)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(nextPwdedit)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        } else if (NewPwdedit.getText().length() < 6 && NextNewPwd.getText().length() < 6) {
            Toast.makeText(this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
        } else if (!newPwdedit.equals(nextPwdedit)) {
            Toast.makeText(this, "密码不一致，重新输入", Toast.LENGTH_SHORT).show();

        } else {

            NewPwd(nextPwdedit, phone);

        }


    }

    private void NewPwd(String newPwd, String phone) {

        String pwd = Base64.encodeToString(newPwd.getBytes(), Base64.DEFAULT);
        String s = Base64.encodeToString("mobile_phone".getBytes(), Base64.DEFAULT);

        try {
            encryptPwd = AESUtils.Encrypt(pwd, BaseUrl.AESKey);

            encryptS = AESUtils.Encrypt(s, BaseUrl.AESKey);

            encryptPhone = AESUtils.Encrypt(phone, BaseUrl.AESKey);

            Log.d("UserChongZhi_PhoneVerif", "加密的密码" + encryptPwd);
            Log.d("UserChongZhi_PhoneVerif", "加密的参数" + encryptS);
            Log.d("UserChongZhi_PhoneVerif", "加密的手机号" + encryptPhone);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> parmas = new HashMap<>();
        parmas.put("action", "ForgetPass");
        parmas.put("new_pass", encryptPwd);
        parmas.put("parameter", encryptS);
        parmas.put("finally", encryptPhone);

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(parmas)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {
                            Toast.makeText(UserChongZhi_PhoneVerifyPwd.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                            mPopWindow();

                        } else {
                            Toast.makeText(UserChongZhi_PhoneVerifyPwd.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
        //                .enqueue(new RawResponseHandler() {
        //                    @Override
        //                    public void onSuccess(int statusCode, String response) {
        //                        Log.d("UserChongZhi_PhoneVerif", response);
        //                    }
        //
        //                    @Override
        //                    public void onFailure(int statusCode, String error_msg) {
        //
        //                    }
        //                });
    }


    // TODO: 2017/9/26 EditText的监听事件
    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (NextNewPwd.getText().length() >= 5) {

                mButChongZhiCommit.setBackground(getResources().getDrawable(R.drawable.queding_but));
            } else {

                mButChongZhiCommit.setBackground(getResources().getDrawable(R.drawable.next_but));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        ppw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                false);
        //设置背景颜色
        ppw.setBackgroundDrawable(new BitmapDrawable());

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);
        ppw.setOutsideTouchable(false);
        ppw.showAtLocation(findViewById(R.id.ChongZhi_PhoneVerify_Linear), Gravity.CENTER, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppw.dismiss();
                backgroundAlpha(1f);
                Intent intent = new Intent(UserChongZhi_PhoneVerifyPwd.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        });
        ppw.setOnDismissListener(new poponDismissListener());

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


    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void Phone(EBPhoneBean bean) {
        phone = bean.getPhone();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
