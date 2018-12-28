package com.example.heyikun.heheshenghuo.controller.user;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
 * 3：类描述： 进行身份验证的邮箱验证
 * <p>
 * 4:类功能： 去进行邮箱的各种验证。1：验证邮箱，2验证手机号 3 绑定成功
 */

public class UserMiBaoYanZhengActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mEmail_FrameLayout)
    FrameLayout mEmailFrameLayout;

    FragmentManager manager;

    FragmentTransaction transaction;
    @BindView(R.id.Change_TitleText)
    TextView ChangeTitleText;
    @BindView(R.id.Image_Quan_Hui1)
    ImageView ImageQuanHui1;
    @BindView(R.id.Image_Quan_Hui2)
    ImageView ImageQuanHui2;
    @BindView(R.id.mText_Email)
    TextView mTextEmail;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_shenfen_yanzheng_email;
    }

    @Override
    protected void initView() {

        mTextEmail.setText("验证密保");

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();

        transaction.replace(R.id.mEmail_FrameLayout, new UserMiBaoVerifyFragment());

        transaction.commit();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick(R.id.Image_Back)
    public void onViewClicked() {
        finish();
    }


    public ImageView getImageQuanHui1() {
        return ImageQuanHui1;
    }

    public void setImageQuanHui1(ImageView imageQuanHui1) {
        ImageQuanHui1 = imageQuanHui1;
    }

    public ImageView getImageQuanHui2() {
        return ImageQuanHui2;
    }

    public void setImageQuanHui2(ImageView imageQuanHui2) {
        ImageQuanHui2 = imageQuanHui2;
    }


}
