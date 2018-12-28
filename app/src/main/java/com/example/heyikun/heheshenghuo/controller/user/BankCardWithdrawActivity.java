package com.example.heyikun.heheshenghuo.controller.user;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/11
 * <p>
 * 3：类描述： 银行卡提现
 * <p>
 * 4:类功能：
 */

public class BankCardWithdrawActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.edit_AliPay_TiXianMoney)
    EditText editAliPayTiXianMoney;
    @BindView(R.id.edit_Alipay_Account)
    EditText editName;
    @BindView(R.id.edit_Alipay_Name)
    EditText editBandCard;
    @BindView(R.id.AliPay_mBut)
    Button AliPayMBut;
    @BindView(R.id.mText_Tixian_account)
    TextView mTextTixianAccount;
    @BindView(R.id.mText_TiXian_Name)
    TextView mTextTiXianName;
    @BindView(R.id.mLinear_AlipayTiXian)
    LinearLayout mLinearAlipayTiXian;

    private PopupWindow popupWindow;

    @Override
    protected int layoutId() {
        return R.layout.activity_alipay_tixian;
    }

    @Override
    protected void initView() {
        mTextTixianAccount.setText("提现到账姓名");

        mTextTiXianName.setText("提现银行卡号");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

    @OnClick({R.id.Image_Back, R.id.AliPay_mBut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:

                finish();
                break;

            //提交
            case R.id.AliPay_mBut:
                mButSubmit();
                break;
        }
    }


    private void mButSubmit() {
        String money = editAliPayTiXianMoney.getText().toString().trim();

        String Name = editName.getText().toString().trim();

        String BankCard = editBandCard.getText().toString().trim();

        if (money.isEmpty() && Name.isEmpty() && BankCard.isEmpty()) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        } else if (money.isEmpty()) {
            Toast.makeText(this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
        } else if (Name.isEmpty()) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
        } else if (BankCard.isEmpty()) {
            Toast.makeText(this, "银行卡号不能为空", Toast.LENGTH_SHORT).show();
        } else {
            mPopWindow();
        }


    }

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        TextView mtext = (TextView) view.findViewById(R.id.mText_SettingSucess);

        mtext.setText("已提交");


        TextView Tv = (TextView) view.findViewById(R.id.mText_LoginPwd);

        Tv.setText("请等待后台及银行处理");

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
        popupWindow.showAtLocation(findViewById(R.id.mLinear_AlipayTiXian), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
