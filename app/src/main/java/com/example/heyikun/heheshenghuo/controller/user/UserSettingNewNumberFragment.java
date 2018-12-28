package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
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

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by hyk on 2017/9/27.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/27
 * <p>
 * 3：类描述： 设置新手机号
 * <p>
 * 4:类功能：进行绑定新的手机号码，发送验证码
 */

public class UserSettingNewNumberFragment extends BaseFragment {
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
    Unbinder unbinder;
    private PopupWindow popupWindow;
    private TimeCount time;
    private TextView text;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_include_newphone_number;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        time = new TimeCount(60000, 1000);


        if (App.activity instanceof UserMiBaoYanZhengActivity) {
            ((UserMiBaoYanZhengActivity) App.activity).getImageQuanHui1().setImageDrawable(getResources().getDrawable(R.drawable.quanlan_man_4x));

        }

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.mBut_getAuthCode, R.id.mBut_Next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mBut_getAuthCode:
                String PhoneNumber = PhoneNumberEdit.getText().toString().trim();

                if (PhoneNumber.isEmpty()) {
                    Toast.makeText(getContext(), "手机号不能为空", Toast.LENGTH_SHORT).show();

                } else if (PhoneNumber.length() > 11 || PhoneNumber.length() < 11) {
                    Toast.makeText(getContext(), "手机号位数不对,请输入正确的手机格式", Toast.LENGTH_SHORT).show();
                } else {
                    //在这里面进行网路请求，发送验证码
                    time.start();

                }


                break;
            case R.id.mBut_Next:
                //点击下一步验证手机号

                if (PhoneNumberEdit.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (PhoneCode.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //在这进行点击下一步，进行弹出ppw。验证正确的手机号
                    mPopWindow();
                    //在请求成功之后在把那个圈圈换成蓝色的，绑定成功

                    if (App.activity instanceof UserMiBaoYanZhengActivity) {
                        ((UserMiBaoYanZhengActivity) App.activity).getImageQuanHui2().setImageDrawable(getResources().getDrawable(R.drawable.quanlan_man_4x));

                    }

                }


                break;
        }
    }


    // TODO: 2017/9/27 弹窗 ppw设置好的新手机号

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_ppw_iknow, null);

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
        popupWindow.showAtLocation(getView().findViewById(R.id.InClude_NewNumberPhoneLinear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                popupWindow.dismiss();
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
