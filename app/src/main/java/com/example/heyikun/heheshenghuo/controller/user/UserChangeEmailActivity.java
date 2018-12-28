package com.example.heyikun.heheshenghuo.controller.user;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by hyk on 2017/9/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/28
 * <p>
 * 3：类描述： 更换邮箱的一系列操作
 * <p>
 * 4:类功能：  进行发送邮箱验证码的操作
 */

public class UserChangeEmailActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Change_TitleText)
    TextView ChangeTitleText;
    @BindView(R.id.mText_Email)
    TextView mTextEmail;
    @BindView(R.id.Image_Quan_Hui1)
    ImageView ImageQuanHui1;
    @BindView(R.id.Image_Quan_Hui2)
    ImageView ImageQuanHui2;
    @BindView(R.id.mEmail_FrameLayout)
    FrameLayout mEmailFrameLayout;
    @BindView(R.id.mText_Change2)
    TextView mTextChange2;

    private FragmentManager manager;
    private FragmentTransaction transaction;


    @Override
    protected int layoutId() {
        return R.layout.activity_user_shenfen_yanzheng_email;
    }

    @Override
    protected void initView() {
        ChangeTitleText.setText("更换绑定邮箱");
        mTextEmail.setText("验证身份");

        init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    private void init() {
        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();

        transaction.replace(R.id.mEmail_FrameLayout, new UserChangeEmailFragment());

        transaction.commit();


    }

    public TextView getmTextChange2() {
        return mTextChange2;
    }

    public void setmTextChange2(TextView mTextChange2) {
        this.mTextChange2 = mTextChange2;
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
