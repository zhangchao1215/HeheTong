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

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBPhoneBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;

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
 * 3：类描述： 设置新的支付密码
 * 5:
 */

public class UserSetPayPwdActivity extends BaseActivity {

    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.ChongZhi_NewPwdedit)
    EditText NewPwdedit;
    @BindView(R.id.ChongZhiNextNewPwd)
    EditText NextNewPwd;
    @BindView(R.id.mButChongZhi_commit)
    Button mButChongZhiCommit;
    @BindView(R.id.ChongZhi_PhoneVerify_Linear)
    LinearLayout ChongZhiPhoneVerifyLinear;
    private PopupWindow ppw;
    private String encryptPwd;
    private String encryptS;
    private String phone;
    private String encryptPhone;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_apay_pwd;
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
        } else if (NewPwdedit.getText().length() < 6 || NextNewPwd.getText().length() < 6) {
            Toast.makeText(this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
        } else if (!newPwdedit.equals(nextPwdedit)) {
            Toast.makeText(this, "密码不一致，重新输入", Toast.LENGTH_SHORT).show();

        } else {

            NewPwd(nextPwdedit);

        }


    }

    private void NewPwd(String newPwd) {

        String user_id = AppUtils.get().getString("user_id", "");

        String token = AppUtils.get().getString("token", "");

        String pwd = Base64.encodeToString(newPwd.getBytes(), Base64.DEFAULT);

        String timestames = TimeUtils.getCurrentTime_Today();

        String time = TimeUtils.dataOne(timestames);

        String mToken = user_id + "," + token + "," + time;

        String AESUserid = null;
        String AESToken = null;

        String AESsign = null;

        String sign = "UpPay" + time + BaseUrl.AESKey;

        String md5Sign = MD5Utils.encrypt(sign);


        try {
            AESUserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            encryptPwd = AESUtils.Encrypt(pwd, BaseUrl.AESKey);

            AESToken = AESUtils.Encrypt(mToken, BaseUrl.AESKey);

            AESsign = AESUtils.Encrypt(md5Sign, BaseUrl.AESKey);


            encryptPhone = AESUtils.Encrypt(phone, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> parmas = new HashMap<>();
        parmas.put("action", "UpPay");
        parmas.put("user_id", AESUserid);
        parmas.put("token", AESToken);
        parmas.put("app_sign", AESsign);
        parmas.put("new_pass", encryptPwd);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, parmas, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("UserChongZhi_PhoneVerif", data);

                Gson gson = new Gson();

                ChangePwdBean pwdBean = gson.fromJson(data, ChangePwdBean.class);

                if (pwdBean == null || data == null) {
                    return;
                } else if (pwdBean.getStatus().equals("200")) {
                    Toast.makeText(UserSetPayPwdActivity.this, pwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                    mPopWindow();

                } else {
                    Toast.makeText(UserSetPayPwdActivity.this, pwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }


    // TODO: 2017/9/26 EditText的监听事件
    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (NextNewPwd.getText().length() > 2) {

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
                Intent intent = new Intent(UserSetPayPwdActivity.this, UserSettingActivity.class);
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
