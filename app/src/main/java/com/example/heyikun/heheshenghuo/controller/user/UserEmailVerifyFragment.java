package com.example.heyikun.heheshenghuo.controller.user;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
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
 * Created by hyk on 2017/9/27.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/27
 * <p>          进行身份验证中的邮箱验证
 * 3：类描述： 输入邮箱号，进行验证邮箱号
 * <p>
 * 4:类功能： 可以看做是更换绑定手机
 */

public class UserEmailVerifyFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.InClude_EmailText)
    TextView InCludeEmailText;
    @BindView(R.id.InClude_EmailLinear)
    LinearLayout InCludeEmailLinear;
    @BindView(R.id.mBut_SendEmail)
    Button mButSendEmail;
    @BindView(R.id.mEdit_Email)
    EditText mEditEmail;
    @BindView(R.id.Email_TitleText)
    TextView EmailTitleText;
    @BindView(R.id.mTextSengEmail)
    TextView mTextSengEmail;
    @BindView(R.id.Include_EmailTitleLinear)
    RelativeLayout IncludeEmailTitleLinear;

    private int flag = 0;
    private PopupWindow ppw;
    private Button button;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private String editEmail;
    private UserEmailYanZhengActivity UserEmail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_include_send_email;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView(View view) {
        InCludeEmailText.setVisibility(View.GONE);
        InCludeEmailLinear.setVisibility(View.GONE);

        fragmentManager = getFragmentManager();

        transaction = fragmentManager.beginTransaction();

        UserEmail = new UserEmailYanZhengActivity();

    }

    @Override
    protected void initListener() {
        mEditEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEditEmail.getText().length() > 6) {
                    mButSendEmail.setBackground(getResources().getDrawable(R.drawable.queding_but));
                } else {
                    mButSendEmail.setBackground(getResources().getDrawable(R.drawable.next_but));
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTextSengEmail.setOnClickListener(this);


    }


    @OnClick(R.id.mBut_SendEmail)
    public void onViewClicked() {
        //第一次点击做的操作
        if (flag == 0) {

            editEmail = mEditEmail.getText().toString().trim();

            if (editEmail.isEmpty()) {
                Toast.makeText(getContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
            } else {
                //在这里做一个网络请求的处理，发送邮箱验证码,并且弹出ppw
                mPopWindow();


            }


            //第二次点击做的操作
            flag = 1;
        } else {

            if (mEditEmail.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
                //还要在这进行判断验证码是否正确。不正确的话不能到下一步

            } else {
                //如果都成功之后就替换到下一个选项，验证手机号
                transaction.replace(R.id.mEmail_FrameLayout, new UserSettingNewNumberFragment());
                transaction.commit();

            }

            flag = 0;
        }


    }

    private void SendEmail(String email) {
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
        String AESTokenDel = null;
        String encryptAppSignDel = null;
        String tpl_name = null;
        try {
            //userID进行AES加密
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            String TwoToken = user_id + "," + token + "," + timestamp;

            AESTokenDel = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            encryptAppSignDel = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

            tpl_name = AESUtils.Encrypt("sendCode", BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "SendEmail");
        params.put("user_id", userid);
        params.put("token", AESTokenDel);
        params.put("app_sign", encryptAppSignDel);
        params.put("tpl_name", tpl_name);
        params.put("email", email);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("UserMailboxVerifyPwdAct", data);
                Gson gson = new Gson();

                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (!bean.getStatus().equals("200")) {
                    return;
                } else {
                    mPopWindow();
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

        button = (Button) view.findViewById(R.id.mBut_emailIKonw);

        button.setOnClickListener(this);

        ppw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        ppw.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        ppw.setOutsideTouchable(false);

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);

        ppw.showAtLocation(getView().findViewById(R.id.Include_EmailTitleLinear), Gravity.CENTER, 0, 0);

        ppw.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);


    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }


    //Ppw里面的点击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mBut_emailIKonw:
                //在这里面进行判断，当ppw进行显示的时候，dismiss掉，并且把button中值换掉

                if (ppw.isShowing()) {


                    ppw.dismiss();

                    backgroundAlpha(1f);

                    mButSendEmail.setText("下一步");


                    EmailTitleText.setText("请输入邮箱验证码");
                    //把隐藏的内容进行显示出来
                    InCludeEmailText.setVisibility(View.VISIBLE);
                    InCludeEmailLinear.setVisibility(View.VISIBLE);

                    mEditEmail.setText("");

                }

                break;

            case R.id.mTextSengEmail:
                flag = 1;
                mPopWindow();


                break;
        }


    }

}
