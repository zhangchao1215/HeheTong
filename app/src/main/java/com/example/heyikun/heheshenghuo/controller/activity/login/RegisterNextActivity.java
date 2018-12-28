package com.example.heyikun.heheshenghuo.controller.activity.login;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.HeHeMainActivity;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ChiYaoRemindBean;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.modle.util.AESUtils.Encrypt;

/**
 * Created by hyk on 2017/9/19.
 */

public class RegisterNextActivity extends BaseActivity {

    @BindView(R.id.RegisterNext_finish)
    TextView RegisterNextFinish;
    @BindView(R.id.RegisterNext_Edit_Pwd)
    EditText RegisterNextEditPwd;
    @BindView(R.id.Register_image_gone)
    ImageView ImageOne;
    @BindView(R.id.Register_LoginNext_Pwd)
    EditText RegisterLoginNextPwd;
    @BindView(R.id.Register_image_next)
    ImageView ImageNext;
    @BindView(R.id.Register_But_complete)
    Button RegisterButComplete;
    private String AESphone;
    private String phone;
    private String basedit;


    @Override
    protected int layoutId() {
        return R.layout.activity_hehe_register_next;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("Phone");
        Log.d("RegisterNextActivity", phone);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

    private int flag = 0;

    @OnClick({R.id.Register_image_gone, R.id.Register_image_next, R.id.Register_But_complete, R.id.RegisterNext_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Register_image_gone:
                PwdGone();
                break;
            case R.id.Register_image_next:
                PwdVisable();
                break;
            case R.id.Register_But_complete:

                mButComplete();

                //
                //
                //
                //
                break;

            case R.id.RegisterNext_finish:
                finish();
                break;
        }
    }

    private void PwdGone() {
        //显示密文密码
        if (flag == 0) {
            RegisterNextEditPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ImageOne.setImageResource(R.drawable.pwd_gone);
            flag = 1;
        } else {
            RegisterNextEditPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ImageOne.setImageResource(R.drawable.pwd_visible);

            flag = 0;
        }


    }


    private void PwdVisable() {
        //显示明文密码
        if (flag == 0) {
            RegisterLoginNextPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ImageNext.setImageResource(R.drawable.pwd_gone);
            flag = 1;
        } else {
            RegisterLoginNextPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ImageNext.setImageResource(R.drawable.pwd_visible);

            flag = 0;
        }

    }


    private void mButComplete() {


        String editOne = RegisterNextEditPwd.getText().toString().trim();

        String editNext = RegisterLoginNextPwd.getText().toString().trim();

        if (editOne.isEmpty() || editNext.isEmpty()) {
            Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
        } else if (editOne.length() < 6 || editOne.length() < 6) {
            Toast.makeText(this, "密码长度不够", Toast.LENGTH_SHORT).show();
        }
        if (!editOne.equals(editNext)) {
            Toast.makeText(this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        } else
            basedit = Base64.encodeToString(editNext.getBytes(), Base64.DEFAULT);

        String Pwdencrypt = null;
        try {
            Pwdencrypt = Encrypt(basedit, BaseUrl.AESKey);
            AESphone = AESUtils.Encrypt(phone, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "Register");
        params.put("user_name", AESphone);
        params.put("pass", Pwdencrypt);
        //        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
        //            @Override
        //            public void onSuccess(String data) {
        //                Log.d("RegisterNextActivity", data);
        //                Gson gson = new Gson();
        //
        //                ChiYaoRemindBean bean = gson.fromJson(data, ChiYaoRemindBean.class);
        //
        //                if (data == null || bean == null) {
        //                    return;
        //                } else if (bean.getStatus().equals("200")) {
        //
        //                } else {
        //                    Toast.makeText(RegisterNextActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
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

                        if (response.getStatus().equals("200")) {
                            Toast.makeText(RegisterNextActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterNextActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(RegisterNextActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

        //        .enqueue(new RawResponseHandler() {
        //            @Override
        //            public void onSuccess(int statusCode, String response) {
        //                Log.d("RegisterNextActivity", response);
        //
        //            }
        //
        //            @Override
        //            public void onFailure(int statusCode, String error_msg) {
        //
        //            }
        //        });

    }
}
