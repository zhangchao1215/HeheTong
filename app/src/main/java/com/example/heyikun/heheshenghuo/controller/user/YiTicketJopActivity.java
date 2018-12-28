package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/13
 * <p>
 * 3：类描述： 易券任务 ， 如何获取更多易券
 * <p>
 * 4:类功能： 点击跳转各种页面，
 */

public class YiTicketJopActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.mBut_recommend)
    Button mButRecommend;
    @BindView(R.id.mBut_qianDao)
    Button mButQianDao;
    @BindView(R.id.mBut_shouHuo)
    Button mButShouHuo;
    @BindView(R.id.mBut_ShaiDan)
    Button mButShaiDan;
    @BindView(R.id.mBut_detection)
    Button mButDetection;
    @BindView(R.id.mBut_read)
    Button mButRead;

    @Override
    protected int layoutId() {
        return R.layout.activity_ticket_yi_jop;


    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("易券任务");

        mTextIncludeTitle.setTextSize(16);

        mTextIncludeTitle.setTextColor(getResources().getColor(R.color.Black));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.Image_Back, R.id.mBut_recommend, R.id.mBut_qianDao, R.id.mBut_shouHuo, R.id.mBut_ShaiDan, R.id.mBut_detection, R.id.mBut_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();


                break;
            //去推荐
            case R.id.mBut_recommend:


                break;

            //去签到
            case R.id.mBut_qianDao:


                break;

            //去收货
            case R.id.mBut_shouHuo:


                break;

            //去晒单
            case R.id.mBut_ShaiDan:


                break;

            // 去检测
            case R.id.mBut_detection:


                break;
            //去阅读

            case R.id.mBut_read:


                break;

        }
    }


    private void mIntent(Class c) {
        Intent intent = new Intent(YiTicketJopActivity.this, c);
        startActivity(intent);


    }

}
