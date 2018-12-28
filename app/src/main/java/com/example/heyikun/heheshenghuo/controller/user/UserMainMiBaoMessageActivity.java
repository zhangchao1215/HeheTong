package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/22 17:05
 * 修改人:  张超
 * 修改内容: 设置完密保信息做的操作
 * 修改时间: 可以进行 验证，删除，修改密保信息的操作
 */

public class UserMainMiBaoMessageActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.YanZheng_MiBao)
    RelativeLayout YanZhengMiBao;
    @BindView(R.id.Change_MiBaoMsg)
    RelativeLayout ChangeMiBaoMsg;
    @BindView(R.id.Delete_MiBao)
    RelativeLayout DeleteMiBao;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_main_mibaomessage;
    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("密保信息");

       mTextIncludeTitle.setTextColor(R.color.Black);


    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.Image_Back, R.id.YanZheng_MiBao, R.id.Change_MiBaoMsg, R.id.Delete_MiBao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                onBackPressed();
                break;
            //验证密保
            case R.id.YanZheng_MiBao:
                mIntent(MiBaoYanZhengInputPwd.class);

                break;

            //修改密保问题
            case R.id.Change_MiBaoMsg:

                break;

            //删除密保
            case R.id.Delete_MiBao:

                mIntent(MiBaoMsgDeleteActivity.class);

                break;
        }
    }

    private void mIntent(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
