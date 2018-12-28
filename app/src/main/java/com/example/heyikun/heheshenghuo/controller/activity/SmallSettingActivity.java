package com.example.heyikun.heheshenghuo.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.life.ChiYaoRemindActivity;
import com.example.heyikun.heheshenghuo.controller.life.LifeJiNianDayActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.SmallSettingBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/21.
 */

public class SmallSettingActivity extends BaseActivity {

    @BindView(R.id.Small_ImageBack)
    ImageView SmallImageBack;
    @BindView(R.id.mImage_JiNianDay)
    ImageView mImageJiNianDay;
    @BindView(R.id.mText_JiNianDay)
    TextView mTextJiNianDay;
    @BindView(R.id.JiNianDay_ImageClose)
    ImageView JiNianDayImageClose;
    @BindView(R.id.mImage_Chiyai_Remind)
    ImageView mImageChiyaiRemind;
    @BindView(R.id.mText_ChiYai_Remind)
    TextView mTextChiYaiRemind;
    @BindView(R.id.ChiYao_iamgeClose)
    ImageView ChiYaoIamgeClose;
    @BindView(R.id.mImage_WomenShengliqi)
    ImageView mImageWomenShengliqi;
    @BindView(R.id.mText_WomenShengliqi)
    TextView mTextWomenShengliqi;
    @BindView(R.id.ShengLiQi_ImageClose)
    ImageView ShengLiQiImageClose;
    @BindView(R.id.mImage_MoneyShengji)
    ImageView mImageMoneyShengji;
    @BindView(R.id.mText_MoneyShengji)
    TextView mTextMoneyShengji;
    @BindView(R.id.Money_ImageClose)
    ImageView MoneyImageClose;
    @BindView(R.id.mImage_ChuangYi)
    TextView mImageChuangYi;
    @BindView(R.id.Chuanyi_ImageClose)
    ImageView ChuanyiImageClose;
    @BindView(R.id.mText_YunDong)
    TextView mTextYunDong;
    @BindView(R.id.YunDong_ImageClose)
    ImageView YunDongImageClose;
    @BindView(R.id.mText_BingZheng)
    TextView mTextBingZheng;
    @BindView(R.id.BingZheng_ImageClose)
    ImageView BingZhengImageClose;
    private String userid;
    private String AESToken;

    @Override
    protected int layoutId() {
        return R.layout.activity_shouye_smalltools;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mRequest();
    }

    @Override
    protected void initLisenter() {

    }


    private void mRequest() {
        //获取用户id 进行AES加密
        String user_id = AppUtils.get().getString("user_id", "");


        String Token = AppUtils.get().getString("token", "");


        try {

            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();
            String timestamp = TimeUtils.dataOne(currentTime_today);


            //userID进行AES加密
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            //生成二次token 并进行加密
            String TwoToken = user_id + "," + Token + "," + timestamp;

            AESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();


        params.put("action", "ToolList");
        params.put("user_id", userid);
        params.put("token", AESToken);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("SmallSettingActivity", data);

                Gson gson = new Gson();


//                SmallSettingBean settingBean = gson.fromJson(data, SmallSettingBean.class);
//
//                List<SmallSettingBean.DataBean> dataBeen = settingBean.getData();
//
//
//                mTextJiNianDay.setText(dataBeen.get(0).getLabel_name());
//
//                mTextChiYaiRemind.setText(dataBeen.get(1).getLabel_name());
//
//                mTextWomenShengliqi.setText(dataBeen.get(2).getLabel_name());
//
//                mTextMoneyShengji.setText(dataBeen.get(3).getLabel_name());
//
//                mImageChuangYi.setText(dataBeen.get(4).getLabel_name());
//
//                mTextYunDong.setText(dataBeen.get(5).getLabel_name());
//
//                mTextBingZheng.setText(dataBeen.get(6).getLabel_name());
            }

            @Override
            public void onError(String error) {

            }
        });


    }


    private int flag = 0;

    @OnClick({R.id.JiNianDay_ImageClose, R.id.ChiYao_iamgeClose, R.id.ShengLiQi_ImageClose, R.id.Money_ImageClose,
            R.id.Chuanyi_ImageClose, R.id.YunDong_ImageClose, R.id.BingZheng_ImageClose, R.id.Small_ImageBack,
            R.id.mImage_JiNianDay, R.id.mImage_Chiyai_Remind, R.id.mImage_WomenShengliqi, R.id.mImage_MoneyShengji,
            R.id.mImage_ChuangYi, R.id.mText_YunDong, R.id.mText_BingZheng
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.JiNianDay_ImageClose:
                if (flag == 0) {
                    //点击第一次做的操作
                    JiNianDayImageClose.setImageDrawable(getResources().getDrawable(R.drawable.kaiqi4x));


                    flag = 1;
                } else {
                    //点击第二次做的操作
                    JiNianDayImageClose.setImageDrawable(getResources().getDrawable(R.drawable.guanbi4x));

                    flag = 0;
                }


                break;
            case R.id.ChiYao_iamgeClose:
                if (flag == 0) {
                    //点击第一次做的操作
                    ChiYaoIamgeClose.setImageDrawable(getResources().getDrawable(R.drawable.kaiqi4x));


                    flag = 1;
                } else {
                    //点击第二次做的操作
                    ChiYaoIamgeClose.setImageDrawable(getResources().getDrawable(R.drawable.guanbi4x));
                    flag = 0;
                }

                break;
            case R.id.ShengLiQi_ImageClose:

                if (flag == 0) {
                    //点击第一次做的操作
                    ShengLiQiImageClose.setImageDrawable(getResources().getDrawable(R.drawable.kaiqi4x));


                    flag = 1;
                } else {
                    //点击第二次做的操作
                    ShengLiQiImageClose.setImageDrawable(getResources().getDrawable(R.drawable.guanbi4x));
                    flag = 0;
                }

                break;
            case R.id.Money_ImageClose:
                if (flag == 0) {
                    //点击第一次做的操作
                    MoneyImageClose.setImageDrawable(getResources().getDrawable(R.drawable.kaiqi4x));


                    flag = 1;
                } else {
                    //点击第二次做的操作
                    MoneyImageClose.setImageDrawable(getResources().getDrawable(R.drawable.guanbi4x));
                    flag = 0;
                }


                break;
            case R.id.Chuanyi_ImageClose:
                if (flag == 0) {
                    ChuanyiImageClose.setImageDrawable(getResources().getDrawable(R.drawable.guanbi4x));

                    flag = 1;

                } else {
                    ChuanyiImageClose.setImageDrawable(getResources().getDrawable(R.drawable.kaiqi4x));
                    flag = 0;
                }

                break;
            case R.id.YunDong_ImageClose:

                if (flag == 0) {
                    YunDongImageClose.setImageDrawable(getResources().getDrawable(R.drawable.guanbi4x));

                    flag = 1;

                } else {
                    YunDongImageClose.setImageDrawable(getResources().getDrawable(R.drawable.kaiqi4x));
                    flag = 0;
                }

                break;
            case R.id.BingZheng_ImageClose:

                if (flag == 0) {
                    BingZhengImageClose.setImageDrawable(getResources().getDrawable(R.drawable.guanbi4x));

                    flag = 1;

                } else {
                    BingZhengImageClose.setImageDrawable(getResources().getDrawable(R.drawable.kaiqi4x));

                    flag = 0;
                }


                break;

            case R.id.Small_ImageBack:

                finish();
                break;
            //查看纪念日的详情
            case R.id.mImage_JiNianDay:
                mIntent(LifeJiNianDayActivity.class);

                break;

            //查看吃药提醒的详情
            case R.id.mImage_Chiyai_Remind:
                mIntent(ChiYaoRemindActivity.class);

                break;


            //查看女性生理期的详情
            case R.id.mImage_WomenShengliqi:


                break;

            //查看财富升级的详情
            case R.id.mImage_MoneyShengji:


                break;

            //查看穿衣指数的详情
            case R.id.mImage_ChuangYi:


                break;

            //查看运动指数的详情
            case R.id.mText_YunDong:


                break;


            //查看病症的详情
            case R.id.mText_BingZheng:


                break;
        }
    }


    private void mIntent(Class c) {
        Intent intent = new Intent(this, c);

        startActivity(intent);


    }


}
