package com.example.heyikun.heheshenghuo.controller.user.fragment;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ArticleCollectAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ArticleCollectBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by hyk on 2017/10/26.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/26
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class CollectArticleFragment extends BaseFragment {
    @BindView(R.id.mListView)
    ListView mListView;
    private List<ArticleCollectBean.DataBean.ArticleBean> articleBeen;
    private ArticleCollectAdapter Adapter;
    private CheckBox checkBox;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_listview;
    }

    @Override
    protected void initData() {
        articleCollect();
    }

    @Override
    protected void initView(View view) {
        articleBeen = new ArrayList<>();



    }

    @Override
    protected void initListener() {

    }


    private void articleCollect() {
        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        String userid = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");

        String AESUserid = null;
        String AEStoken = null;
        String AESsign = null;

        String twotoken = userid + "," + token + "," + timestamp;

        String sign = "AllCollection" + timestamp + BaseUrl.AESKey;

        String MD5Sign = MD5Utils.encrypt(sign);


        try {
            AESUserid = AESUtils.Encrypt(userid, BaseUrl.AESKey);

            AEStoken = AESUtils.Encrypt(twotoken, BaseUrl.AESKey);

            AESsign = AESUtils.Encrypt(MD5Sign, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "AllCollection");
        params.put("user_id", AESUserid);
        params.put("token", AEStoken);
        params.put("app_sign", AESsign);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("UserMyCollectActivity", data);
                Gson gson = new Gson();
                ArticleCollectBean bean = gson.fromJson(data, ArticleCollectBean.class);

                if (data == null || bean == null) {
                    return;
                } else if (!bean.getStatus().equals("200")) {
                    return;
                }

                List<ArticleCollectBean.DataBean.ArticleBean> article = bean.getData().getArticle();

//                articleBeen.addAll(article);
//                Adapter = new ArticleCollectAdapter(articleBeen, getContext());

//                mListView.setAdapter(Adapter);

                Adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });

    }




}
