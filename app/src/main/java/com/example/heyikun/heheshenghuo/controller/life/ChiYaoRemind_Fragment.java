package com.example.heyikun.heheshenghuo.controller.life;

import android.util.Log;
import android.view.View;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyk on 2017/9/12.
 */

public class ChiYaoRemind_Fragment extends BaseFragment {

    private String encryptUserId;
    private String encryptToken;

    @Override
    protected int getLayoutId() {
        return R.layout.acticity_main_chiyaotixing;
    }

    @Override
    protected void initData() {
        getDay("2");
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }


    private void getDay(String id) {

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


        Map<String, String> params = new HashMap<>();
        params.put("action", "Show_mark_day");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("label_id", id);


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("Fragment", "吃药提醒" + data);
            }

            @Override
            public void onError(String error) {

            }
        });


    }
}
