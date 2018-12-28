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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
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
 * Created by hyk on 2017/9/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/28
 * <p>           更换邮箱三部曲之第二部
 * <p>
 * 3：类描述：  这是更换邮箱中的输入新的邮箱页面
 * <p>
 * 4:类功能：输入新的邮箱
 */

public class UserChangerNewEmailFragment extends BaseFragment {
    @BindView(R.id.Email_TitleText)
    TextView EmailTitleText;
    @BindView(R.id.mEdit_Email)
    EditText mEditEmail;
    @BindView(R.id.mBut_SendEmail)
    Button mButSendEmail;
    @BindView(R.id.InClude_EmailText)
    TextView InCludeEmailText;
    @BindView(R.id.mTextSengEmail)
    TextView mTextSengEmail;
    @BindView(R.id.InClude_EmailLinear)
    LinearLayout InCludeEmailLinear;
    @BindView(R.id.Include_EmailTitleLinear)
    RelativeLayout IncludeEmailTitleLinear;
    private PopupWindow popupWindow;
    private String AESTokenDel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_include_send_email;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        EmailTitleText.setText("请输入您的新邮箱");
        mButSendEmail.setText("提交");

        if (App.activity instanceof UserChangeEmailActivity) {
            ((UserChangeEmailActivity) App.activity).getImageQuanHui1().setImageDrawable(getResources().getDrawable(R.drawable.quanlan_man_4x));
        }

    }

    @Override
    protected void initListener() {

        mEditEmail.addTextChangedListener(new EditChangedListener());

    }


    @OnClick(R.id.mBut_SendEmail)
    public void onViewClicked() {
        String email = mEditEmail.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (App.activity instanceof UserChangeEmailActivity) {
                ((UserChangeEmailActivity) App.activity).getImageQuanHui2().setImageDrawable(getResources().getDrawable(R.drawable.quanlan_man_4x));
            }
        }


    }



    private void SendEmail(final String email) {
        //获取用户id 进行AES加密
       String user_id = AppUtils.get().getString("user_id", "");


       String token = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timestamp = TimeUtils.dataOne(currentTime_today);

        //进行签名 ，Md5进行加密
        String app_sign = "SendEmail" + timestamp + BaseUrl.AESKey;

        String encryptMd5 = MD5Utils.encrypt(app_sign);


        String userid = null;
        String encryptAppSignDel = null;
        String aeSemail = null;
        String send_password1 = null;
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
        params.put("action", "SendEmail");
        params.put("user_id", userid);
        params.put("token", AESTokenDel);
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
//                    mPopWindow(email);
                    Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }


    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_ppw_sendemail, null);

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

        popupWindow.showAtLocation(getView().findViewById(R.id.main_Linear), Gravity.CENTER, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);
                Intent intent = new Intent(getContext(), AccountSecurityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);



            }
        });

        popupWindow.setOnDismissListener(new poponDismissListener());
    }


    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);


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

            if (mEditEmail.getText().length() > 5) {

                mButSendEmail.setBackground(getResources().getDrawable(R.drawable.queding_but));
            } else {

                mButSendEmail.setBackground(getResources().getDrawable(R.drawable.next_but));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
