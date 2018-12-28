package com.example.heyikun.heheshenghuo.controller.life;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.JianNianDayAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.MainBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBDateBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.ScreenUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by hyk on 2017/9/12.
 */

public class JiNianDayFragment extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private String user_id;
    private String encryptUserId;
    private String encryptToken;

    private List<MainBean.DataBean.DayBean> mList;

    private JianNianDayAdapter adapter;

    private int width;

    @Override
    protected int getLayoutId() {
        return R.layout.acticity_life_birthday;
    }

    @Override
    protected void initData() {
        getDay("1");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //设置水平滑动
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);

        width = ScreenUtils.getScreenWidth(getContext()) / 2;


        adapter = new JianNianDayAdapter(mList, getContext(), width);

        mRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initView(View view) {
//        if (!EventBus.getDefault().isRegistered(this)) {//加上判断
//            EventBus.getDefault().register(this);
//        }
    }

    @Override
    protected void initListener() {

    }


    private void getDay(String id) {

        user_id = AppUtils.get().getString("user_id", "");

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
                Log.d("Fragment", "纪念日" + data);


            }

            @Override
            public void onError(String error) {

            }
        });


    }

//    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
//    public void dayMainThread(EBDateBean bean) {
//
//
//        mList = bean.getLifeDayList();
//        adapter = new JianNianDayAdapter(mList, getContext(), width);
//
//        mRecyclerView.setAdapter(adapter);
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//
//        if (EventBus.getDefault().isRegistered(this))//加上判断
//            EventBus.getDefault().unregister(this);
    }

}
