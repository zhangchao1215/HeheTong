package com.example.heyikun.heheshenghuo.controller.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ServeShangquanAdaoter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ServeShangquanBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.LocationUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.modle.util.AESUtils.Encrypt;

/**
 * Created by hyk on 2018/1/3.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/3
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 和合服务 的 商圈
 */

public class ShangQuanActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Serve_address_but)
    Button ServeAddressBut;
    @BindView(R.id.Shangquan_recycler)
    RecyclerView ShangquanRecycler;
    private double latitude;
    private double longitude;
    private String AEStoken = null;
    private String AESuserid = null;
    private List<ServeShangquanBean.DataBean.DistrictBean> districtBeanList;
    private ServeShangquanAdaoter serveShangquanAdaoter;
    private String user_id;
    private String token;
    private ServeShangquanBean.DataBean.DistrictBean districtBean;

    @Override
    protected int layoutId() {
        return R.layout.activity_shangquan;
    }

    @Override
    protected void initView() {
        user_id = AppUtils.get().getString("user_id", "");
        token = AppUtils.get().getString("token", "");

        districtBeanList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        ShangquanRecycler.setLayoutManager(gridLayoutManager);
        try {
            Location location = LocationUtils.getInstance(this).showLocation();

            latitude = location.getLatitude(); //纬度
            longitude = location.getLongitude();// 经度


            Log.d("ShangQuanActivity", "latitude:纬度" + latitude);
            Log.d("ShangQuanActivity", "longitude:经度" + longitude);


        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    protected void initData() {
        mLoadData();
    }

    @Override
    protected void initLisenter() {

    }

    private void mLoadData() {

        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        String Twotoken = user_id + "," + token + "," + timestamp;

        try {
            AESuserid = Encrypt(user_id, BaseUrl.AESKey);

            AEStoken = Encrypt(Twotoken, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "LocationUser");
        params.put("user_id", AESuserid);
        params.put("token", AEStoken);
        params.put("longitude", String.valueOf(longitude));
        params.put("latitude", String.valueOf(latitude));

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ServeShangquanBean>() {
                    @Override
                    public void onSuccess(int statusCode, ServeShangquanBean response) {

                        if (response.getStatus().equals("200")) {
                            ServeShangquanBean.DataBean data = response.getData();

                            ServeAddressBut.setText(data.getAddress());

                            districtBeanList.addAll(data.getDistrict());

                            serveShangquanAdaoter = new ServeShangquanAdaoter(districtBeanList, ShangQuanActivity.this);
                            serveShangquanAdaoter.setOnItemLisenter(new ServeShangquanAdaoter.OnItemLisenter() {
                                @Override
                                public void itemListenter(int position) {

                                    if(user_id.equals("")||token.equals("")){
                                        Toast.makeText(ShangQuanActivity.this, "请先登录", Toast.LENGTH_SHORT).show();

                                    }

                                    districtBean = districtBeanList.get(position);
                                    shangquan(districtBean.getDistrict_id());

                                }
                            });
                            ShangquanRecycler.setAdapter(serveShangquanAdaoter);
                            serveShangquanAdaoter.notifyDataSetChanged();

                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.d("HeHeServeFragment", error_msg);
                    }
                });

    }


    private void shangquan(String position) {

        String aesUserid = null;
        String aesToken = null;
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(currentTime_today);
        String twoToken = user_id + "," + token + "," + timeStamp;
        try {
            aesUserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "AddDistrict");
        params.put("user_id", aesUserid);
        params.put("token", aesToken);
        params.put("district_id", position);

        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {
                        if (response.getStatus().equals("200")) {
                            Toast.makeText(ShangQuanActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("district_Name", districtBean.getDistrict_name());
                            setResult(201, intent);
                            AppUtils.put().putString("district_Name", districtBean.getDistrict_name()).commit();

                        } else {
                            Toast.makeText(ShangQuanActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
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
