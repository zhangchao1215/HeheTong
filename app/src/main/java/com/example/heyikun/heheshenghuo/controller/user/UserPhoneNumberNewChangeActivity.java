package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/27.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/27
 * <p>
 * 3：类描述： 进行更换手机号，验证手机验证码
 * <p>
 * 4:类功能： 输入手机号获取验证码。再点击下一步之后，在进行验证新的手机号
 */

public class UserPhoneNumberNewChangeActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
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
    @BindView(R.id.mText_ShenFen_Verify)
    TextView mTextShenFenVerify;
    TextView UserPhoneMRelativeTitle;
    @BindView(R.id.UserPhone_mTextTitle)
    TextView UserPhoneMTextTitle;

    private TimeCount time;
    private String phoneNumber;
    private PopupWindow popupWindow;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_phone_verify;
    }

    @Override
    protected void initView() {

        time = new TimeCount(60000, 1000);

        UserPhoneMRelativeTitle.setText("更换绑定手机");

        UserPhoneMTextTitle.setText("请设置新的手机号码");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.mBut_getAuthCode, R.id.mBut_Next, R.id.mText_ShenFen_Verify, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.Image_Back:
                finish();
                break;


            case R.id.mBut_getAuthCode:

                phoneNumber = PhoneNumberEdit.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNumber.length() > 11 || phoneNumber.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mButNext.setBackground(getResources().getDrawable(R.drawable.queding_but));
                    time.start();
                }


                break;
            case R.id.mBut_Next:

                String Phone = PhoneNumberEdit.getText().toString().trim();
                String PhoneCode = this.PhoneCode.getText().toString().trim();
                if (TextUtils.isEmpty(Phone) && TextUtils.isEmpty(PhoneCode)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (Phone.length() > 11 || Phone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(PhoneCode)) {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    mPopWindow();


                }


                break;
            case R.id.mText_ShenFen_Verify:


                break;
        }
    }

    // TODO: 2017/9/27 弹窗 ppw设置好的新手机号

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        TextView Tv = (TextView) view.findViewById(R.id.mText_LoginPwd);

        Tv.setText("已成功绑定新手机号");

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
        popupWindow.showAtLocation(findViewById(R.id.NewPhoneNumber_Linear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);
                Intent intent = new Intent(UserPhoneNumberNewChangeActivity.this, AccountSecurityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

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

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

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
