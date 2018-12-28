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
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
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
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

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
 * 3：类描述：绑定邮箱
 * <p>
 * 4:类功能： 点击进行邮箱进行验证密码.输入绑定的邮箱
 */

public class UserMailboxBingDingActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mailbox_edit)
    EditText mailboxEdit;
    @BindView(R.id.mBut_Sendemail)
    Button mButSendemail;
    @BindView(R.id.mText_TitleEmail)
    TextView mTextTitleEmail;
    @BindView(R.id.mText_Shuru_Email)
    TextView mTextShuruEmail;
    @BindView(R.id.Input_EmailLinear)
    LinearLayout InputEmailLinear;
    private PopupWindow ppw;
    private String user_id;
    private String token;
    private String AESTokenDel;
    private String encryptAppSignDel;
    private String userid;
    private String send_password;
    private String aeSemail;
    private String send_password1;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_mailboxverify_pwd;
    }

    @Override
    protected void initView() {
        mTextTitleEmail.setText("绑定邮箱");


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {
        mailboxEdit.addTextChangedListener(new EditChangedListener());
    }


    @OnClick(R.id.mBut_Sendemail)
    public void onViewClicked() {

        String editEmail = mailboxEdit.getText().toString().trim();

        if (TextUtils.isEmpty(editEmail)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();

        }
        if (editEmail.length() < 7) {
            Toast.makeText(this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
        } else {
            SendEmail(editEmail);
        }


    }


    // TODO: 2017/9/26 EditText的监听事件
    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (mailboxEdit.getText().length() > 6) {

                mButSendemail.setBackground(getResources().getDrawable(R.drawable.queding_but));
            } else {

                mButSendemail.setBackground(getResources().getDrawable(R.drawable.next_but));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void mPopWindow(final String email) {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_sendemail, null);

        Button button = (Button) view.findViewById(R.id.mBut_emailIKonw);

        ppw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        ppw.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        ppw.setOutsideTouchable(false);

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);

        ppw.showAtLocation(findViewById(R.id.Input_EmailLinear), Gravity.CENTER, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppw.dismiss();
                backgroundAlpha(1f);
                Intent intent = new Intent(UserMailboxBingDingActivity.this, UserInputMailBoxVerifyActivity.class);

                intent.putExtra("AESemail", aeSemail);

                intent.putExtra("email", email);

                intent.putExtra("sendcode", send_password1);
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


    private void SendEmail(final String email) {
        //获取用户id 进行AES加密
        user_id = AppUtils.get().getString("user_id", "");


        token = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timestamp = TimeUtils.dataOne(currentTime_today);

        //进行签名 ，Md5进行加密
        String app_sign = "BindingEmail" + timestamp + BaseUrl.AESKey;

        String encryptMd5 = MD5Utils.encrypt(app_sign);


        try {
            //userID进行AES加密
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            String TwoToken = user_id + "," + token + "," + timestamp;

            AESTokenDel = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            encryptAppSignDel = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

            String BaseEmail = Base64.encodeToString(email.getBytes(), Base64.DEFAULT);

            aeSemail = AESUtils.Encrypt(BaseEmail, BaseUrl.AESKey);

            Log.d("UserMailboxBingDingActi", "加密的AES邮箱   " + aeSemail);

            send_password1 = AESUtils.Encrypt("sendCode", BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "BindingEmail");
        params.put("user_id", userid);
        params.put("token", AESTokenDel);
        params.put("app_sign", encryptAppSignDel);
        params.put("tpl_name", send_password1);
        params.put("email", aeSemail);

        //        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
        //            @Override
        //            public void onSuccess(String data) {
        //                Log.d("UserMailboxVerifyPwdAct", data);
        //                Gson gson = new Gson();
        //
        //                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);
        //
        //                if (!bean.getStatus().equals("200")) {
        //                    return;
        //                } else {
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

        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {

                            mPopWindow(email);
                            Toast.makeText(UserMailboxBingDingActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(UserMailboxBingDingActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }

}
