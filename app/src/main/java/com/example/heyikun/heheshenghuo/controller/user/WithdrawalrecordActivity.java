package com.example.heyikun.heheshenghuo.controller.user;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/11
 * <p>
 * 3：类描述： 提现记录
 * <p>
 * 4:类功能：
 */

public class WithdrawalrecordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_AliPay)
    TextView mTextAliPay;
    @BindView(R.id.mText_Time)
    TextView mTextTime;
    @BindView(R.id.alipay)
    RelativeLayout alipay;
    @BindView(R.id.mText_ChuLi)
    TextView mTextChuLi;
    @BindView(R.id.mText_Money)
    TextView mTextMoney;
    @BindView(R.id.AliPay_Detail)
    RelativeLayout AliPayDetail;
    @BindView(R.id.mText_BandCard)
    TextView mTextBandCard;
    @BindView(R.id.mText_Time1)
    TextView mTextTime1;
    @BindView(R.id.BandCard)
    RelativeLayout BandCard;
    @BindView(R.id.BandCard_mText_ChuLi)
    TextView BandCardMTextChuLi;
    @BindView(R.id.BandCard_mText_Money)
    TextView BandCardMTextMoney;
    @BindView(R.id.relative_bandcard)
    RelativeLayout relativeBandcard;
    @BindView(R.id.BandCard_detail)
    RelativeLayout BandCardDetail;
    private PopupWindow popupWindow;

    @Override
    protected int layoutId() {
        return R.layout.activity_withdrawal_record;
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

    @OnClick({R.id.Image_Back, R.id.AliPay_Detail, R.id.BandCard_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;

            //支付宝查看提现金额的详情
            case R.id.AliPay_Detail:
                mPopWindow();

                break;

            //银行卡查看提现的详情以及错误的信息
            case R.id.BandCard_detail:
                mPopWindow();

                break;
        }
    }


    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_usert_withdraw_detail_ppw, null);

        Button button = (Button) view.findViewById(R.id.mbut_widthraw);

        button.setOnClickListener(this);

        TextView mName = (TextView) view.findViewById(R.id.mText_Widthraw_Name);


        TextView mAliPay = (TextView) view.findViewById(R.id.mText_Widthraw_Alipay);

        TextView mMsg = (TextView) view.findViewById(R.id.mText_Widthraw_Message);

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
        popupWindow.showAtLocation(findViewById(R.id.mLinear_widthrawPPw), Gravity.CENTER, 0, 0);


        popupWindow.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }

    //里面button的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mbut_widthraw:
                popupWindow.dismiss();
                backgroundAlpha(1f);

                break;

        }


    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }
}
