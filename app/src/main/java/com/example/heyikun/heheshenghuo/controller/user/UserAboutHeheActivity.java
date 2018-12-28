package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.view.View;
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
 * 创建时间: 2017/10/5 18:21
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

public class UserAboutHeheActivity extends BaseActivity {
    @BindView(R.id.mText_finish)
    TextView mTextFinish;
    @BindView(R.id.Hehe_welcome)
    RelativeLayout HeheWelcome;
    @BindView(R.id.Hehe_Update)
    RelativeLayout HeheUpdate;
    @BindView(R.id.Hehe_Help)
    RelativeLayout HeheHelp;
    @BindView(R.id.Hehe_FanKui)
    RelativeLayout HeheFanKui;
    @BindView(R.id.Hehe_mText_XieYi)
    TextView HeheMTextXieYi;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_hehe_about;
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


    @OnClick({R.id.mText_finish, R.id.Hehe_welcome, R.id.Hehe_Update, R.id.Hehe_Help, R.id.Hehe_FanKui, R.id.Hehe_mText_XieYi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mText_finish:
                finish();

                break;
            case R.id.Hehe_welcome:


                break;
            case R.id.Hehe_Update:


                break;
            case R.id.Hehe_Help:
                Intent in = new Intent(this, UserHeheHelpActivity.class);
                startActivity(in);

                break;
            case R.id.Hehe_FanKui:
                Intent intent = new Intent(this, UserFeedBackActivity.class);
                startActivity(intent);

                break;
            case R.id.Hehe_mText_XieYi:
                break;
        }
    }
}
