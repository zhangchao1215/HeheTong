package com.example.heyikun.heheshenghuo.controller.life;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ChiYaoRemindBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/16
 * <p>
 * 3：类描述： 吃药提醒 在小工具列表中
 * <p>
 * 4:类功能：
 */

public class ChiYaoRemindActivity extends BaseActivity {
    @BindView(R.id.Base_RecyclerView)
    RecyclerView BaseRecyclerView;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_TitleRemind)
    TextView mTextTitleRemind;
    @BindView(R.id.mText_AddReming)
    TextView mTextAddReming;
    @BindView(R.id.mText_Remind)
    TextView mTextRemind;
    private String encryptUserId;
    private String encryptToken;

    @Override
    protected int layoutId() {
        return R.layout.activity_life_chiyao_remind;
    }

    @Override
    protected void initView() {
        mRequest("2");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

    private void mRequest(String id) {

        Map<String, String> params = new HashMap<>();

        String user_id = AppUtils.get().getString("user_id", "");

        Log.d("HeHeLifeFragment", "我的id" + user_id);

        String token = AppUtils.get().getString("token", "");

        Log.d("HeHeLifeFragment", "我的token" + token);


        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);
        try {

            //给userID 还有 生成二次token ，在进行AES加密
            encryptUserId = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            String mToken = user_id + "," + token + "," + timestamp;


            encryptToken = AESUtils.Encrypt(mToken, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        params.put("action", "Show_disease_day");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("label_id", id);


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("LifeJiNianDayActivity", "吃药提醒" + data);

                Gson gson = new Gson();

                ChiYaoRemindBean remindBean = gson.fromJson(data, ChiYaoRemindBean.class);

                if (data == null || !remindBean.getStatus().equals("200")) {
                    return;
                } else if (remindBean.getData() == null) {
                    mTextRemind.setVisibility(View.VISIBLE);
                } else {
                    //在这里进行加载数据


                }


            }

            @Override
            public void onError(String error) {

            }
        });


    }


    @OnClick({R.id.mText_AddReming, R.id.mText_Remind, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:

                finish();
                break;

            case R.id.mText_AddReming:


                break;
            case R.id.mText_Remind:


                break;
        }
    }
}
