package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputFilter;
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
import com.example.heyikun.heheshenghuo.HeHeMainActivity;
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
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.password;

/**
 * Created by hyk on 2017/9/25.
 * 类描述： 修改密码，设置新密码
 * <p>  修改支付密码
 * 类功能：1：输入旧密码进行验证，在重新输入新密码
 * 2：还有就是忘记密码，跳转到另一页面去验证手机号，重置密码
 */

public class UserChangePwd_PayActivity extends BaseActivity {
    @BindView(R.id.ChangePwd_EditOldPwd)
    EditText ChangePwdEditOldPwd;
    @BindView(R.id.ChangePwd_Forget_oldPwd)
    TextView ChangePwdForgetOldPwd;
    @BindView(R.id.ChangePwd_NewPwdEdit)
    EditText ChangePwdNewPwdEdit;
    @BindView(R.id.ChangePwd_NexNewPwdEdit)
    EditText ChangePwdNexNewPwdEdit;
    @BindView(R.id.ChangePwd_mButYes)
    Button ChangePwdMButYes;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Change_Popupwindow)
    LinearLayout ChangePopupwindow;
    private PopupWindow ppw;
    private String nextPwd;
    private String token;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_changepwd;
    }

    @Override
    protected void initView() {
        token = AppUtils.get().getString("token", "");
        ChangePwdNewPwdEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        ChangePwdNexNewPwdEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.ChangePwd_Forget_oldPwd, R.id.ChangePwd_mButYes, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();
                break;

            case R.id.ChangePwd_Forget_oldPwd:
                //     忘记密码
                Intent intent = new Intent(this, UserChongZhiPassWordActivity.class);
                startActivity(intent);


                break;
            //输入新密码，进行验证
            case R.id.ChangePwd_mButYes:
                String NewPwd = ChangePwdNewPwdEdit.getText().toString().trim();

                nextPwd = ChangePwdNexNewPwdEdit.getText().toString().trim();
                String oldPwd = ChangePwdEditOldPwd.getText().toString().trim();
                if (oldPwd.isEmpty()) {
                    Toast.makeText(this, "请输入您的旧密码", Toast.LENGTH_SHORT).show();
                } else if (NewPwd.isEmpty()) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (nextPwd.isEmpty()) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (!NewPwd.equals(nextPwd)) {
                    Toast.makeText(this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                } else if (nextPwd.length() < 6 || nextPwd.length() > 6) {
                    Toast.makeText(this, "请输入6位数密码", Toast.LENGTH_SHORT).show();
                } else
                    requestChangePwd(oldPwd, NewPwd);

                break;
        }
    }

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        ppw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        ppw.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        ppw.setOutsideTouchable(false);

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);

        ppw.showAtLocation(findViewById(R.id.Change_Popupwindow), Gravity.CENTER, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppw.dismiss();
                backgroundAlpha(1f);

                //修改密码成功之后跳转到首页
                Intent intent = new Intent(UserChangePwd_PayActivity.this, AccountSecurityActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

                finish();
            }
        });
        ppw.setOnDismissListener(new poponDismissListener());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

    private void requestChangePwd(String oldPwd, String newPwd) {

        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        String user_id = AppUtils.get().getString("user_id", "");
        String app_signMd5 = "UpPayPass" + timestamp + BaseUrl.AESKey;

        String app_sign = MD5Utils.encrypt(app_signMd5);
        String mtoken = user_id + "," + token + "," + timestamp;
        String mOldPwd = Base64.encodeToString(oldPwd.getBytes(), Base64.DEFAULT);
        String mNewPwd = Base64.encodeToString(newPwd.getBytes(), Base64.DEFAULT);

        String aesSign = null;
        String aesUser = null;
        String aesToken = null;
        String aesOld = null;
        String aesnew = null;
        try {
            aesSign = AESUtils.Encrypt(app_sign, BaseUrl.AESKey);
            aesUser = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            aesToken = AESUtils.Encrypt(mtoken, BaseUrl.AESKey);
            aesOld = AESUtils.Encrypt(mOldPwd, BaseUrl.AESKey);
            aesnew = AESUtils.Encrypt(mNewPwd, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();

        params.put("action", "UpPayPass");
        params.put("user_id", aesUser);
        params.put("token", aesToken);
        params.put("app_sign", aesSign);
        params.put("old_pass", aesOld);
        params.put("new_pass", aesnew);

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {
                            mPopWindow();
                        } else {
                            Toast.makeText(UserChangePwd_PayActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }


}
