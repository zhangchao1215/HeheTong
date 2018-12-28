package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
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
 * Created by hyk on 2017/9/26.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/26
 * <p>
 * 3：类描述： 提交邮箱验证码,然后跳转到最初的设置页面
 * <p>
 * 4:类功能：
 */

public class UserInputMailBoxVerifyActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_Shuru_Email)
    TextView mTextShuruEmail;
    @BindView(R.id.mailbox_edit)
    EditText mailboxEdit;
    @BindView(R.id.mBut_Submitemail)
    Button mButSubmitemail;
    @BindView(R.id.mText_resend_email)
    TextView mTextResendEmail;
    @BindView(R.id.Input_EmailVerifyLinear)
    LinearLayout InputEmailVerifyLinear;
    private PopupWindow ppw;
    private PopupWindow popupWindow;
    private String encryptEmail;
    private String userid;
    private String AESTokenTwo;
    private String encryptAppSign;
    private String encryptCode;
    private String Intentemail;
    private String decryptEmail;
    private String IntentmEmail;
    private String user_id;

    private String NextTwoToken;
    private String token;
    String encryptAppSignDel = null;
    private String SendCode;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_input_mailboxverify;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        Intentemail = intent.getStringExtra("AESemail");

        IntentmEmail = intent.getStringExtra("email");


        SendCode = intent.getStringExtra("sendcode");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {
        mailboxEdit.addTextChangedListener(new EditChangedListener());
    }


    @OnClick({R.id.Image_Back, R.id.mBut_Submitemail, R.id.mText_resend_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;
            case R.id.mBut_Submitemail:
                String emailVerify = mailboxEdit.getText().toString().trim();

                if (emailVerify.isEmpty()) {
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mRequest(emailVerify);

                    //                    mPopWindow();

                }


                break;

            //点击再次发送验证码
            case R.id.mText_resend_email:

                NextSendCode(IntentmEmail);

                break;


        }
    }

    /**
     * 进行邮箱验证码的验证
     *
     * @param Emailcode
     */
    private void mRequest(String Emailcode) {

        //获取用户id 进行AES加密
        user_id = AppUtils.get().getString("user_id", "");


        token = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timestamp = TimeUtils.dataOne(currentTime_today);

        //进行签名 ，Md5进行加密
        String app_sign = "CheckEmailCode" + timestamp + BaseUrl.AESKey;

        String encryptMd5 = MD5Utils.encrypt(app_sign);


        try {
            //userID进行AES加密
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            String TwoToken = user_id + "," + token + "," + timestamp;

            AESTokenTwo = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            encryptAppSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

            String mEmail = Base64.encodeToString(Emailcode.getBytes(), Base64.DEFAULT);

            encryptCode = AESUtils.Encrypt(mEmail, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "CheckEmailCode");
        params.put("user_id", userid);
        params.put("token", AESTokenTwo);
        params.put("app_sign", encryptAppSign);
        params.put("email", Intentemail);
        params.put("email_code", encryptCode);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("UserInputMailBoxVerifyA", data);

                Gson gson = new Gson();

                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (data == null || bean == null) {
                    return;
                } else if (!bean.getStatus().equals("200")) {
                    Toast.makeText(UserInputMailBoxVerifyActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserInputMailBoxVerifyActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    mPopWindow();
                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }


    /**
     * 点击此处再次发送验证码。
     */

    private void NextSendCode(final String email) {
        //获取用户id 进行AES加密
        user_id = AppUtils.get().getString("user_id", "");


        token = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timestamp = TimeUtils.dataOne(currentTime_today);

        //进行签名 ，Md5进行加密
        String app_sign = "BindingEmail" + timestamp + BaseUrl.AESKey;

        String encryptMd5 = MD5Utils.encrypt(app_sign);


        String send_password1 = null;
        String aeSemail = null;
        try {
            //userID进行AES加密
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            String TwoToken = user_id + "," + token + "," + timestamp;

            NextTwoToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            encryptAppSignDel = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

            String BaseEmail = Base64.encodeToString(email.getBytes(), Base64.DEFAULT);

            aeSemail = AESUtils.Encrypt(BaseEmail, BaseUrl.AESKey);


            send_password1 = AESUtils.Encrypt("sendCode", BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "BindingEmail");
        params.put("user_id", userid);
        params.put("token", NextTwoToken);
        params.put("app_sign", encryptAppSignDel);
        params.put("tpl_name", send_password1);
        params.put("email", aeSemail);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("UserMailboxVerifyPwdAct", data);
                Gson gson = new Gson();

                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (!bean.getStatus().equals("200")) {
                    return;
                } else {
                    Toast.makeText(UserInputMailBoxVerifyActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                    mPpw();
                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }


    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        TextView Tv = (TextView) view.findViewById(R.id.mText_LoginPwd);


        Tv.setText(IntentmEmail + "已绑定");

        ppw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        ppw.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        ppw.setOutsideTouchable(false);

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);

        ppw.showAtLocation(findViewById(R.id.Input_EmailVerifyLinear), Gravity.CENTER, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppw.dismiss();
                backgroundAlpha(1f);
                //点击成功后跳转到最初的设置页面
                Intent intent = new Intent(UserInputMailBoxVerifyActivity.this, AccountSecurityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

            }
        });
        ppw.setOnDismissListener(new poponDismissListener());

    }


    private void mPpw() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_sendemail, null);

        Button button = (Button) view.findViewById(R.id.mBut_emailIKonw);

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        popupWindow.setOutsideTouchable(false);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);

        popupWindow.showAtLocation(findViewById(R.id.Input_EmailVerifyLinear), Gravity.CENTER, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);


            }
        });

        popupWindow.setOnDismissListener(new poponDismissListener());
    }


    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }


    //Ppw的关闭事件
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    // TODO: 2017/9/26 EditText的监听事件
    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (mailboxEdit.getText().length() > 5) {

                mButSubmitemail.setBackground(getResources().getDrawable(R.drawable.queding_but));
            } else {

                mButSubmitemail.setBackground(getResources().getDrawable(R.drawable.next_but));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
