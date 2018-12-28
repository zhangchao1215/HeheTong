package com.example.heyikun.heheshenghuo.controller.user;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/6 8:29
 * 修改人:  张超
 * 修改内容:  账户充值 , 选择微信充值，和支付宝充值
 * 修改时间: 支付宝微信去支付选择一种方式
 */

public class UserAccountRechargeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.mText_money)
    TextView mTextMoney;
    @BindView(R.id.mEdit_Recharge_money)
    EditText mEditRechargeMoney;
    @BindView(R.id.mImage_WeiXinPay)
    ImageView mImageWeiXinPay;
    @BindView(R.id.mImage_AliPay)
    ImageView mImageAliPay;
    @BindView(R.id.mBut_submit)
    Button mButSubmit;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.WeiXin_Pay)
    RelativeLayout WeiXinPay;
    @BindView(R.id.Ali_Pay)
    RelativeLayout AliPay;
    @BindView(R.id.mLinear_Change_ppw)
    LinearLayout mLinearChangePpw;
    private PopupWindow popupWindow;
    private String editMoney;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_account_recharge;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {


    }


    private int flag = 0;

    @OnClick({R.id.WeiXin_Pay, R.id.Ali_Pay, R.id.mBut_submit, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.WeiXin_Pay:

                flag = 0;

                mImageAliPay.setImageDrawable(getResources().getDrawable(R.drawable.weixuanzhong4x));

                mImageWeiXinPay.setImageDrawable(getResources().getDrawable(R.drawable.xuanzhong4x));

                break;
            case R.id.Ali_Pay:

                flag = 1;
                mImageWeiXinPay.setImageDrawable(getResources().getDrawable(R.drawable.weixuanzhong4x));

                mImageAliPay.setImageDrawable(getResources().getDrawable(R.drawable.xuanzhong4x));

                //                if(flag == 0){
                //
                //                    flag = 1;
                //                }else {
                //
                //
                //                    flag =0;
                //                }


                break;

            case R.id.mBut_submit:


                editMoney = mEditRechargeMoney.getText().toString().trim();
                if (editMoney.isEmpty()) {
                    Toast.makeText(this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mPopWindow();
                }


                break;

            case R.id.Image_Back:
                finish();
                break;
        }
    }

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_gopay_ppw, null);

        Button button = (Button) view.findViewById(R.id.mBut_GotoPay);

        button.setOnClickListener(this);

        TextView PayName = (TextView) view.findViewById(R.id.Pay_Name);

        TextView Money = (TextView) view.findViewById(R.id.mText_ChangeMoney);

        TextView PayMent = (TextView) view.findViewById(R.id.mText_Payment);


        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));

        //设置外部可点击
        popupWindow.setOutsideTouchable(true);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);

        //popupwindow的弹出位置
        popupWindow.showAtLocation(findViewById(R.id.mLinear_Change_ppw), Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

        if (flag == 0) {

            //选择是微信支付方式

            Money.setText(editMoney);

            PayMent.setText("微信");

        } else if (flag == 1) {

            //选择是支付宝支付方式

            Money.setText(editMoney);

            PayMent.setText("支付宝");



        }


    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBut_mActivity_Ikonw:


                break;
        }
    }


    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");

            popupWindow.dismiss();
            backgroundAlpha(1f);
        }

    }

}
