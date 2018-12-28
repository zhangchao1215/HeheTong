package com.example.heyikun.heheshenghuo.controller.user;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
 * 3：类描述： 我的活动
 * <p>
 * 4:类功能：
 */

public class UserMyActivitiesActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_SeeDetails)
    TextView mTextSeeDetails;
    @BindView(R.id.MyActivities_Name)
    TextView MyActivitiesName;
    @BindView(R.id.mText_Time)
    TextView mTextTime;
    @BindView(R.id.mText_AddAddress)
    TextView mTextAddAddress;
    @BindView(R.id.My_CanYuPeople)
    TextView MyCanYuPeople;
    private PopupWindow popupWindow;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_myactivities;
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


    @OnClick({R.id.Image_Back, R.id.mText_SeeDetails, R.id.My_CanYuPeople})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;
            //查看活动的详情
            case R.id.mText_SeeDetails:


                break;

            //查看参加人数的
            case R.id.My_CanYuPeople:
                mPopWindow();

                break;
        }
    }


    // TODO: 2017/9/27 弹窗 ppw设置好的新手机号

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_myactivity_ppw, null);

        Button button = (Button) view.findViewById(R.id.mBut_mActivity_Ikonw);

        button.setOnClickListener(this);

        TextView JoinName = (TextView) view.findViewById(R.id.Join_Name);

        TextView AllPeople = (TextView) view.findViewById(R.id.mText_allPeople);

        TextView Number = (TextView) view.findViewById(R.id.Join_PhoneNumber);


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
        popupWindow.showAtLocation(findViewById(R.id.mLinear_MyActivities_ppw), Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());
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
