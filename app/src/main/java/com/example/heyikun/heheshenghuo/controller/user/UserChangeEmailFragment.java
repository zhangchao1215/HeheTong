package com.example.heyikun.heheshenghuo.controller.user;

import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ChiYaoRemindBean;
import com.example.heyikun.heheshenghuo.modle.bean.RegisterBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
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
 * <p>
 * 3：类描述： 这是更换绑定邮箱进行替换的fragment
 * <p>
 * 4:类功能： 第一步进行输入手机号进行验证，发送验证码
 */

public class UserChangeEmailFragment extends BaseFragment {
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
    private PopupWindow ppw;
    private String nextPwdedit;
    private TimeCount time;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private String encryptPhone;
    private String encryptCodes;
    private String AESToken;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_include_phonenumber;
    }

    @Override
    protected void initData() {
        time = new TimeCount(60000, 1000);
    }

    @Override
    protected void initView(View view) {

        manager = getFragmentManager();

        transaction = manager.beginTransaction();

        if (App.activity instanceof UserChangeEmailActivity) {

            ((UserChangeEmailActivity) App.activity).getmTextChange2().setText("修改邮箱");
        }

    }

    @Override
    protected void initListener() {
        PhoneCode.addTextChangedListener(new EditChangedListener());
    }


    @OnClick({R.id.mBut_getAuthCode, R.id.mBut_Next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBut_getAuthCode:
                String phoneNumber = PhoneNumberEdit.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(getContext(), "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNumber.length() > 11 || phoneNumber.length() < 11) {
                    Toast.makeText(getContext(), "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //                    mButGetAuthCode.setBackground(getResources().getDrawable(R.drawable.next_but));
                    //                    time.start();
                    SendAntuCode(phoneNumber);
                }

                break;
            case R.id.mBut_Next:
                String newPwdedit = PhoneNumberEdit.getText().toString().trim();

                nextPwdedit = PhoneCode.getText().toString().trim();
                String mPhoneCode = PhoneCode.getText().toString().trim();
                if (TextUtils.isEmpty(newPwdedit) || mPhoneCode.isEmpty()) {
                    Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(nextPwdedit)) {
                    Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
                } else if (newPwdedit.length() < 11 || newPwdedit.length() > 11) {
                    Toast.makeText(getContext(), "手机号位数不对", Toast.LENGTH_SHORT).show();
                } else if (mPhoneCode.isEmpty()) {
                    Toast.makeText(getContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    // 在这里进行验证手机验证码，成功后替换到输入新的邮箱账号
                    YanZhengCode(mPhoneCode);

                    //
                    //


                }


                break;
        }
    }


    // TODO: 2017/9/26 EditText的监听事件
    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (PhoneCode.getText().length() > 5) {

                mButNext.setBackground(getResources().getDrawable(R.drawable.queding_but));
            } else {

                mButNext.setBackground(getResources().getDrawable(R.drawable.next_but));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        ppw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                false);
        //设置背景颜色
        ppw.setBackgroundDrawable(new BitmapDrawable());

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);
        ppw.setOutsideTouchable(false);
        ppw.showAtLocation(getView().findViewById(R.id.main_Linear), Gravity.CENTER, 0, 0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppw.dismiss();
                backgroundAlpha(1f);
            }
        });
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

    /**
     * 发送验证码
     */
    private void SendAntuCode(String phone) {
        try {
            encryptPhone = AESUtils.Encrypt(phone, BaseUrl.AESKey);
            Map<String, String> params = new HashMap<>();
            params.put("action", "ObtainCode");
            params.put("user_name", encryptPhone);

            OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
                @Override
                public void onSuccess(String data) {
                    Log.d("UserChangeEmailFragment", data);
                    Gson gson = new Gson();

                    ChiYaoRemindBean bean = gson.fromJson(data, ChiYaoRemindBean.class);

                    if (data == null || bean == null) {
                        return;
                    } else if (bean.getStatus().equals("200")) {
                        Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                    Log.d("RegisterActivity", data);
                }

                @Override
                public void onError(String error) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 验证手机验证码
     */
    /**
     * 验证验证码
     */

    private void YanZhengCode(String code) {
        //获取用户id 进行AES加密
        String user_id = AppUtils.get().getString("user_id", "");


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
                Log.d("UserChangeEmailFragment", data);
                Gson gson = new Gson();

                RegisterBean bean = gson.fromJson(data, RegisterBean.class);

                if (bean == null || data == null) {
                    return;
                } else if (bean.getStatus().equals("200")) {

                    transaction.replace(R.id.mEmail_FrameLayout,new UserChangerNewEmailFragment());
                    transaction.commit();

                } else {
                    Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(String error) {

            }
        });
        //


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
        }

        @Override
        public void onFinish() {
            mButGetAuthCode.setText("点击获取验证码");
            mButGetAuthCode.setClickable(true);
            mButGetAuthCode.setBackground(getResources().getDrawable(R.drawable.next_but));
        }
    }

}
