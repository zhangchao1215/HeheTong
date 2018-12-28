package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.RecyclerAddressAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.TakeAddressBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.data;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/5 11:56
 * 修改人:  张超
 * 修改内容:   用户的收货地址 ，进来的第一个页面
 * 修改时间:  可以用来进行添加收货地址，和删除收货地址
 */

public class UserAddressGuanLiActivity extends BaseActivity implements View.OnClickListener, RecyclerAddressAdapter.AddressLisenter {

    @BindView(R.id.mImage_Back)
    ImageView mImageBack;
    @BindView(R.id.mText_AddAddress)
    TextView mTextAddAddress;

    @BindView(R.id.mPPw_Linear)
    LinearLayout mPPwLinear;

    @BindView(R.id.Base_RecyclerView)
    RecyclerView BaseRecyclerView;
    @BindView(R.id.mBut_Add)
    Button mButAdd;
    @BindView(R.id.Linear_No_Data)
    LinearLayout LinearNoData;
    @BindView(R.id.mText)
    TextView mText;
    private PopupWindow popupWindow;
    private List<TakeAddressBean.DataBean> beanList;

    private String userid, AESToken;
    private String encryptAppSign;
    private RecyclerAddressAdapter addressAdapter;
    private int DeletePos;
    private String timestamp;
    private String user_id;
    private String token;
    private String encryptAppSignDel;
    private String AESTokenDel;
    private String address_id;
    private List<TakeAddressBean.DataBean> dataBeen;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_address_guanli;
    }

    @Override
    protected void initView() {

        init();
    }

    private void init() {
        beanList = new ArrayList<>();
        dataBeen = new ArrayList<>();
        BaseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override

    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRequest();

    }

    @Override
    protected void initLisenter() {

    }

    private void initRequest() {
        beanList.clear();
        Map<String, String> params = new HashMap<>();

        //获取用户id 进行AES加密
        user_id = AppUtils.get().getString("user_id", "");

        Log.d("UserAddressGuanLiActivi", "账号id   " + user_id);

        token = AppUtils.get().getString("token", "");

        Log.d("UserAddressGuanLiActivi", " TOken     " + token);


        try {

            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();

            String timestamp = TimeUtils.dataOne(currentTime_today);

            String TwoToken = user_id + "," + token + "," + timestamp;

            //userID进行AES加密
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            //生成二次token 并进行加密

            AESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            String app_sign = "ShowAddress" + timestamp + BaseUrl.AESKey;

            String encryptMd5 = MD5Utils.encrypt(app_sign);

            encryptAppSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        params.put("action", "ShowAddress");
        params.put("user_id", userid);
        params.put("token", AESToken);
        params.put("app_sign", encryptAppSign);
        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<TakeAddressBean>() {
                    @Override
                    public void onSuccess(int statusCode, TakeAddressBean response) {

                        List<TakeAddressBean.DataBean> dataBeen = response.getData();
                        if (response.getStatus().equals("200")) {

                            LinearNoData.setVisibility(View.GONE);

                            beanList.addAll(dataBeen);

                            addressAdapter = new RecyclerAddressAdapter(beanList, UserAddressGuanLiActivity.this);

                            addressAdapter.setAddressItemLisenter(UserAddressGuanLiActivity.this);

                            BaseRecyclerView.setAdapter(addressAdapter);

                            addressAdapter.notifyDataSetChanged();

                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
        //
                OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d("UserAddressGuanLiActivi", data);



                    }

                    @Override
                    public void onError(String error) {

                    }
                });


    }

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_address_ppw, null);

        TextView mTVdismiss = (TextView) view.findViewById(R.id.mText_dismiss);

        TextView mTVdelete = (TextView) view.findViewById(R.id.mText_Delete);

        mTVdismiss.setOnClickListener(this);
        mTVdelete.setOnClickListener(this);

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        popupWindow.setOutsideTouchable(false);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);

        //popupwindow的弹出位置nnnnnn
        popupWindow.showAtLocation(findViewById(R.id.mPPw_Linear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        //        button.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                popupWindow.dismiss();
        //                backgroundAlpha(1f);
        //
        //                //给Intent设置启动模式，让他直接返回到上级界面，不然就重新返回来了
        //
        //
        //            }
        //        });
        popupWindow.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }


    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mText_dismiss:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    backgroundAlpha(1f);
                }


                break;
            //点击删除地址做的操作
            case R.id.mText_Delete:

                DeleteAddtess();


                break;

        }


    }

    private void DeleteAddtess() {

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timestamp = TimeUtils.dataOne(currentTime_today);

        //进行签名 ，Md5进行加密
        String app_sign = "DelAddress" + timestamp + BaseUrl.AESKey;

        String encryptMd5 = MD5Utils.encrypt(app_sign);


        try {
            String TwoToken = user_id + "," + token + "," + timestamp;

            AESTokenDel = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            encryptAppSignDel = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> parmas = new HashMap<>();
        parmas.put("action", "DelAddress");
        parmas.put("user_id", userid);
        parmas.put("token", AESTokenDel);
        parmas.put("app_sign", encryptAppSignDel);
        parmas.put("address_id", address_id);

        Log.d("UserAddressGuanLiActivi", "address_id   " + address_id);


        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(parmas)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {
                            beanList.remove(DeletePos);
                            addressAdapter.notifyDataSetChanged();

                            if (popupWindow.isShowing()) {
                                popupWindow.dismiss();
                                backgroundAlpha(1.0f);
                                Toast.makeText(UserAddressGuanLiActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(UserAddressGuanLiActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

        //        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, parmas, "", new MyCallBack() {
        //            @Override
        //            public void onSuccess(String data) {
        //
        //                Log.d("UserAddressGuanLiActivi", data);
        //
        //                Gson gson = new Gson();
        //
        //                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);
        //                if (bean == null || data == null) {
        //                    return;
        //                } else if (!bean.getStatus().equals("200")) {
        //                    Toast.makeText(UserAddressGuanLiActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        //                    return;
        //                } else {
        //                    beanList.remove(DeletePos);
        //                    addressAdapter.notifyDataSetChanged();
        //
        //                    if (popupWindow.isShowing()) {
        //                        popupWindow.dismiss();
        //                        backgroundAlpha(1.0f);
        //                        Toast.makeText(UserAddressGuanLiActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        //
        //                    }
        //                }
        //
        //
        //            }
        //
        //            @Override
        //            public void onError(String error) {
        //
        //            }
        //        });
        //
    }


    @OnClick({R.id.mImage_Back, R.id.mText_AddAddress, R.id.mBut_Add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mImage_Back:
                finish();
                break;
            case R.id.mText_AddAddress:

                Intent in = new Intent(this, UserAddAddressActivity.class);
                startActivity(in);
                break;

            case R.id.mBut_Add:

                Intent intent = new Intent(this, UserAddAddressActivity.class);
                startActivity(intent);

                break;
        }
    }


    /**
     * 自定义点击事件
     *
     * @param v
     * @param position
     */
    @Override
    public void DeleteAddress(View v, int position) {
        DeletePos = position;
        TakeAddressBean.DataBean dataBean = beanList.get(position);

        address_id = dataBean.getAddress_id();

        Log.d("UserAddressGuanLiActivi", "DeletePos:" + DeletePos);

        mPopWindow();


    }

    //重新编辑
    @Override
    public void ChangeAddress(View view, int position) {

        TakeAddressBean.DataBean dataBean = beanList.get(position);

        Intent in = new Intent(this, UserChangeAddressActivity.class);

        in.putExtra("address_id", dataBean.getAddress_id());
        in.putExtra("phone", dataBean.getMobile());
        in.putExtra("province", dataBean.getProvince());
        in.putExtra("city", dataBean.getCity());
        in.putExtra("district", dataBean.getDistrict());
        in.putExtra("address", dataBean.getAddress_desc());
        in.putExtra("people", dataBean.getConsignee());
        in.putExtra("state", dataBean.getState());
        startActivityForResult(in, 200);

    }


    /**
     * 跳转回传结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }


}
