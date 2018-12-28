package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BigCastUserPeopleAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastPeopleDetailBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.DividerItemDecorationRecy;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.name;

/**
 * Created by hyk on 2017/11/20.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/20
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  大咖的个人中心，简介
 */

public class BigCastPeopleDetailActivity extends BaseActivity {

    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.bigcast_people_detailImage)
    ImageView bigcastPeopleDetailImage;
    @BindView(R.id.bigcast_people_detailName)
    TextView bigcastPeopleDetailName;
    @BindView(R.id.bigcast_people_detailDesc)
    TextView bigcastPeopleDetailDesc;
    @BindView(R.id.title_linear)
    LinearLayout titleLinear;
    @BindView(R.id.bigcast_people_detail_attention)
    TextView bigcastPeopleDetailAttention;
    @BindView(R.id.bigcast_people_detail_heat)
    TextView bigcastPeopleDetailHeat;
    @BindView(R.id.bigcast_people_detail_renqi)
    TextView bigcastPeopleDetailRenqi;
    @BindView(R.id.Linear)
    LinearLayout Linear;
    @BindView(R.id.bigcast_people_detail_jia)
    TextView bigcastPeopleDetailJia;
    @BindView(R.id.bigcast_people_detail_Name1)
    TextView bigcastPeopleDetailName1;
    @BindView(R.id.bigcast_people_detail_size)
    TextView bigcastPeopleDetailSize;
    @BindView(R.id.bigcast_people_detail_recycler)
    RecyclerView bigcastPeopleDetailRecycler;
    @BindView(R.id.bigcast_guanzhu_image)
    ImageView bigcastGuanzhuImage;
    private String id;
    private String aesId;
    private List<BigCastPeopleDetailBean.DataBean.ArticleBean> articleList;
    private BigCastUserPeopleAdapter peopleAdapter;
    private String encryptUserId;
    private String mTokenTwo;
    private String encryptToken;
    private int flag;
    private int state;
    private BigCastPeopleDetailBean.DataBean dataBean;
    private String mtoken;
    private String mUserId;
    private String user_id;
    private String atten_count;

    @Override
    protected int layoutId() {
        return R.layout.activity_bigcast_people_detail;
    }

    @Override
    protected void initView() {
        mUserId = AppUtils.get().getString("user_id", "");


        mtoken = AppUtils.get().getString("token", "");

        articleList = new ArrayList<>();
        init();


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {
        bigcastPeopleDetailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bigcastRequest(id);

    }

    private void init() {

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String desc = intent.getStringExtra("desc");
        String name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");

        bigcastPeopleDetailName.setText(name + "");
        bigcastPeopleDetailDesc.setText(desc + "");
        bigcastPeopleDetailName1.setText(name + "的作品");

        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.touxiang_nan_man_4x)
                .into(bigcastPeopleDetailImage);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        bigcastPeopleDetailRecycler.addItemDecoration(new DividerItemDecorationRecy(this, DividerItemDecorationRecy.VERTICAL_LIST));

        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        bigcastPeopleDetailRecycler.setHasFixedSize(true);
        bigcastPeopleDetailRecycler.setNestedScrollingEnabled(false);

        bigcastPeopleDetailRecycler.setLayoutManager(manager);


    }

    private void bigcastRequest(String id) {

        Log.d("BigCastPeopleDetailActi", "医生是id" + id);
        user_id = AppUtils.get().getString("user_id", "");

        try {
            if (!user_id.equals("")) {

                aesId = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "BigcastPersonal");
        params.put("people_id", id);
        params.put("user_id", aesId);


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("BigCastPeopleDetailActi", data);

                Gson gson = new Gson();
                try {
                    BigCastPeopleDetailBean bigCastPeopleDetailBean = gson.fromJson(data, BigCastPeopleDetailBean.class);
                    BigCastPeopleDetailBean.DataBean dataBean = bigCastPeopleDetailBean.getData();

                    if (bigCastPeopleDetailBean.getStatus().equals("200")) {
                        bigcastPeopleDetailName.setText(dataBean.getBig_name());
                        //关注
                        atten_count = dataBean.getAtten_count();
                        bigcastPeopleDetailAttention.setText(atten_count + "");

                        bigcastPeopleDetailHeat.setText(dataBean.getHot() + "");

                        bigcastPeopleDetailRenqi.setText(dataBean.getPopularity() + "");

                        bigcastPeopleDetailSize.setText("(" + dataBean.getArticle_sum() + ")" + "");

                        bigcastPeopleDetailName.setText(dataBean.getBig_name());

                        Glide.with(BigCastPeopleDetailActivity.this)
                                .load(dataBean.getBig_pic())
                                .placeholder(R.drawable.touxiang_nan_man_4x)
                                .into(bigcastPeopleDetailImage);
                        bigcastPeopleDetailDesc.setText(dataBean.getBig_desc() + "");

                        bigcastPeopleDetailName1.setText(dataBean.getBig_name() + "的作品");


                        state = dataBean.getFollow_state();


                        // 0 是未关注 ，1 是已关注
                        if (state == 0) {
                            bigcastPeopleDetailJia.setText("加关注");


                        } else if (state == 1) {


                            bigcastPeopleDetailJia.setText("已关注");
                            bigcastGuanzhuImage.setImageResource(R.drawable.guanzhu);
                            bigcastPeopleDetailJia.setTextColor(getResources().getColor(R.color.color_ccc));
                        }


                        articleList.addAll(dataBean.getArticle());
                        peopleAdapter = new BigCastUserPeopleAdapter(articleList, BigCastPeopleDetailActivity.this);

                        bigcastPeopleDetailRecycler.setAdapter(peopleAdapter);
                        peopleAdapter.notifyDataSetChanged();

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
        bigcastPeopleDetailJia.setTag(false);

        bigcastPeopleDetailJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                try {
                    count = Integer.parseInt(atten_count);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //这是没关注的状态
                if (user_id.equals("") || mtoken.equals("")) {
                    Toast.makeText(BigCastPeopleDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else if (state == 0) {
                    bigcastGuanZhu(1);
                    bigcastPeopleDetailJia.setText("已关注");
                    bigcastPeopleDetailAttention.setText(count + 1 + "");
                    bigcastPeopleDetailJia.setTextColor(getResources().getColor(R.color.color_ccc));
                    bigcastGuanzhuImage.setImageResource(R.drawable.guanzhu);
                    state = 1;
                } else if (state == 1) { //这是已关注的状态，点击取消关注
                    bigcastGuanZhu(0);
                    bigcastPeopleDetailJia.setText("加关注");
                    bigcastPeopleDetailAttention.setText(count + "");
                    bigcastGuanzhuImage.setImageResource(R.drawable.jiaguanzhu_man3x);
                    bigcastPeopleDetailJia.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                    state = 0;
                }


            }
        });

    }


    private void bigcastGuanZhu(int state) {

        final Map<String, String> params = new HashMap<>();


        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        try {

            //给userID 还有 生成二次token ，在进行AES加密
            encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);

            mTokenTwo = mUserId + "," + mtoken + "," + timestamp;


            encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("action", "Ateention");
        params.put("people_id", id);
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("state", String.valueOf(state));

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.d("BigCastPeopleDetailActi", response);

                        Gson gson = new Gson();
                        try {
                            ChangePwdBean changePwdBean = gson.fromJson(response, ChangePwdBean.class);
                            if (changePwdBean.getStatus().equals("200")) {

                                Toast.makeText(BigCastPeopleDetailActivity.this, changePwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(BigCastPeopleDetailActivity.this, changePwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }


    @OnClick(R.id.Image_Back)
    public void onViewClicked() {
        finish();
    }


}
