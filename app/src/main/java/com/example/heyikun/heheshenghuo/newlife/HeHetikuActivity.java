package com.example.heyikun.heheshenghuo.newlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.TikuAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.TikuImageBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.type;

/**
 * Created by hyk on 2018/1/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class HeHetikuActivity extends BaseActivity {
    public static final String TAG = "HeHetikuActivity";

    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.tiku_tablayout)
    TabLayout tikuTab;
    @BindView(R.id.tiKu_imageRecycler)
    RecyclerView tiKuImageRecycler;
    @BindView(R.id.tiku_resultRecycler)
    RecyclerView tikuResultRecycler;
    @BindView(R.id.text_change)
    TextView textChange;
    @BindView(R.id.tiMu_Recycler)
    RecyclerView tiMuRecycler;
    @BindView(R.id.text_Dingyue)
    TextView textDingyue;
    private String[] strTab = {"养身", "养心", "养财"};
    private List<TikuImageBean.DataBean.TwoClassBean> mList;
    private HeHetiKuimageAdapter imageAdapter;
    private HeHetiKuiResultAdapter resultAdapter;
    private TikuAdapter tikuAdapter;
    private List<TikuImageBean.DataBean.TestsBean> testList;
    private List<TikuImageBean.DataBean.LabelBean> labelBeanList;
    private String user_id;
    private int type = 1;

    @Override
    protected int layoutId() {
        return R.layout.activity_hehe_tiku;
    }

    @Override
    protected void initView() {
        user_id = AppUtils.get().getString("user_id", "");

        mList = new ArrayList<>();
        testList = new ArrayList<>();

        labelBeanList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        tiKuImageRecycler.setLayoutManager(gridLayoutManager);

        noGrildrScroll(tiKuImageRecycler, gridLayoutManager);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        tikuResultRecycler.setLayoutManager(manager);

        noLinearScroll(tikuResultRecycler, manager);


        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        tiMuRecycler.setLayoutManager(linearLayoutManager);
        noGrildrScroll(tiMuRecycler, linearLayoutManager);
        init();


    }


    @Override
    protected void onResume() {
        super.onResume();
        LoadData(1, "");
        resultData("14", 1);

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initLisenter() {

    }

    private void init() {

        for (int i = 0; i < strTab.length; i++) {
            tikuTab.addTab(tikuTab.newTab().setText(strTab[i]));
        }

        tikuTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                switch (position) {
                    case 0:
                        LoadData(1, "");
                        resultData("14", 1);
                        type = 1;
                        break;
                    case 1:
                        type = 2;
                        LoadData(2, "");
                        resultData("16", 2);
                        break;
                    case 2:
                        type = 3;
                        LoadData(3, "");
                        resultData("18", 3);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void LoadData(final int type, String id) {
        mList.clear();
        labelBeanList.clear();

        String token = AppUtils.get().getString("token", "");

        String aesUserid = null;
        String aesToken = null;

        String time_today = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(time_today);

        String twoToken = user_id + "," + token + "," + timeStamp;

        try {
            if (!user_id.equals("") && !token.equals("")) {
                aesUserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
                aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);
                Log.d(TAG, aesToken);
                Log.d(TAG, aesUserid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, user_id);
        Map<String, String> params = new HashMap<>();
        params.put("action", "GetQuestions");
        params.put("user_id", aesUserid);
        params.put("token", aesToken);
        params.put("ques_type", String.valueOf(type));
        params.put("class_id", "");


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private TikuImageBean imageBean;

            @Override
            public void onSuccess(String data) {
                Log.d("HeHetikuActivity", data);
                Gson gson = new Gson();
                try {
                    imageBean = gson.fromJson(data, TikuImageBean.class);


                    if (imageBean.getResult().equals("200")) {
                        TikuImageBean.DataBean dataBean = imageBean.getData();
                        mList.addAll(dataBean.getTwo_class());

                        //图标的adapter
                        imageAdapter = new HeHetiKuimageAdapter(mList, HeHetikuActivity.this);
                        tiKuImageRecycler.setAdapter(imageAdapter);
                        imageAdapter.notifyDataSetChanged();


                        imageAdapter.setOnItemClick(new HeHetiKuimageAdapter.OnItemClick() {
                            @Override
                            public void onItemclick(int position) {
                                TikuImageBean.DataBean.TwoClassBean twoClassBean = imageBean.getData().getTwo_class().get(position);

                                Log.d(TAG, "type点击之后的type:" + type);
                                switch (type) {
                                    case 1:
                                        resultData(twoClassBean.getClass_id(), 1);

                                        break;
                                    case 2:
                                        resultData(twoClassBean.getClass_id(), 1);

                                        break;
                                    case 3:
                                        resultData(twoClassBean.getClass_id(), 1);

                                        break;


                                }


                            }
                        });


                        List<TikuImageBean.DataBean.LabelBean> label = imageBean.getData().getLabel();

                        labelBeanList.addAll(label);

                        tikuAdapter = new TikuAdapter(labelBeanList, HeHetikuActivity.this);


                        //最上方的adapter
                        tikuAdapter.setOnitemClick(new TikuAdapter.OnitemClick() {
                            @Override
                            public void onItemClick(int position) {
                                TikuImageBean.DataBean.LabelBean labelBean = imageBean.getData().getLabel().get(position);

                                WebViewUtils.publicWebView(HeHetikuActivity.this, labelBean.getVote_link(), labelBean.getVote_name());


                            }
                        });

                        tiMuRecycler.setAdapter(tikuAdapter);
                        tikuAdapter.notifyDataSetChanged();
                        Log.d(TAG, "testList.size():" + testList.size());
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });

    }


    private void resultData(String class_id, int type) {

        testList.clear();

        String token = AppUtils.get().getString("token", "");

        String aesUserid = null;
        String aesToken = null;

        String time_today = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(time_today);

        String twoToken = user_id + "," + token + "," + timeStamp;

        try {
            if (!user_id.equals("") && !token.equals("")) {
                aesUserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
                aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);
                Log.d(TAG, aesToken);

            }
            Log.d(TAG, aesUserid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "GetQuestions");
        params.put("user_id", aesUserid);
        params.put("token", aesToken);
        params.put("ques_type", String.valueOf(type));
        params.put("class_id", class_id);
        Log.d(TAG, "二级分类的id   +++   " + class_id);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private TikuImageBean tikuImageBean;

            @Override
            public void onSuccess(String data) {

                Log.d(TAG, "最下方是什么数据++++++   " + data);
                Gson gson = new Gson();

                try {
                    tikuImageBean = gson.fromJson(data, TikuImageBean.class);


                    if (tikuImageBean.getResult().equals("200")) {

                        if (testList == null) {
                            tikuResultRecycler.setVisibility(View.GONE);
                        } else {
                            tikuResultRecycler.setVisibility(View.VISIBLE);
                        }

                        testList.addAll(tikuImageBean.getData().getTests());

                        resultAdapter = new HeHetiKuiResultAdapter(testList, HeHetikuActivity.this);
                        resultAdapter.setTikuOnClick(new HeHetiKuiResultAdapter.TikuOnClick() {
                            @Override
                            public void onItemClick(int position) {
                                TikuImageBean.DataBean.TestsBean testsBean = testList.get(position);

                                WebViewUtils.publicWebView(HeHetikuActivity.this, testsBean.getVote_link(), "测试");

                            }
                        });

                        tikuResultRecycler.setAdapter(resultAdapter);
                        resultAdapter.notifyDataSetChanged();

                    } else {
                        tikuResultRecycler.setVisibility(View.GONE);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(String error) {

            }
        });


    }


    //    @OnClick(R.id.text_change)
    //    public void onViewClicked() {
    //
    //    }


    private void noLinearScroll(RecyclerView recyclerView, LinearLayoutManager manager) {
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void noGrildrScroll(RecyclerView recyclerView, GridLayoutManager manager) {
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }


    @OnClick({R.id.Image_Back, R.id.text_change, R.id.text_Dingyue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;
            case R.id.text_change:


                break;
            case R.id.text_Dingyue:

                Intent intent = new Intent(this, SubscribeActivity.class);
                startActivity(intent);

                break;
        }
    }
}
