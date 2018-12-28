package com.example.heyikun.heheshenghuo.controller.life;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
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
 * Created by hyk on 2017/9/29.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/29
 * <p>
 * 3：类描述：纪念日。生日提醒 列表的显示
 * <p>
 * 4:类功能： 点击纪念日跳转到这个页面，就是一些提醒的详情，还可以进行添加提醒，就是跳转到另一页面进行添加
 */

public class LifeJiNianDayActivity extends BaseActivity {
    @BindView(R.id.mText_AddRemind)
    TextView mTextAddRemind;
    @BindView(R.id.mText_TitleRemind)
    TextView mTextTitleRemind;
    @BindView(R.id.mText_TimeRemind)
    TextView mTextTimeRemind;
    @BindView(R.id.mText_target)
    TextView mTextTarget;
    @BindView(R.id.mText_dayRemind)
    TextView mTextDayRemind;
    private String encryptUserId;
    private String encryptToken;

    @Override
    protected int layoutId() {
        return R.layout.activity_life_jinian_day_remind;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mRequest("2");
    }

    @Override
    protected void initLisenter() {

    }


    //   点击进行添加提醒
    @OnClick(R.id.mText_AddRemind)
    public void onViewClicked() {

        Intent intent = new Intent(this, LifeAddJiNianDayActivity.class);
        startActivity(intent);

    }


    //纪念日页面的请求
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


        params.put("action", "Show_mark_day");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("label_id", id);


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("LifeJiNianDayActivity","纪念日"+ data);

                Gson gson = new Gson();

            }

            @Override
            public void onError(String error) {

            }
        });




    }


}
