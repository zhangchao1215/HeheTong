package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.LoginMessageBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.citysector.CityBaseActivity;
import com.example.heyikun.heheshenghuo.modle.dao.AddressBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.picker.wheelview.OnWheelChangedListener;
import com.example.heyikun.heheshenghuo.modle.picker.wheelview.WheelView;
import com.example.heyikun.heheshenghuo.modle.picker.wheelview.adapter.ArrayWheelAdapter;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.modle.util.AESUtils.Encrypt;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/5 10:44
 * 修改人:  张超
 * 修改内容:  添加收货地址
 * 修改时间:
 */

public class UserChangeAddressActivity extends CityBaseActivity implements View.OnClickListener, OnWheelChangedListener {


    @BindView(R.id.mImage_Address_default)
    ImageView mImageAddressDefault;
    @BindView(R.id.mEdit_AddressName)
    EditText mEditAddressName;
    @BindView(R.id.mEdit_AddressPhone)
    EditText mEditAddressPhone;
    @BindView(R.id.mText_Region)
    TextView mTextRegion;
    @BindView(R.id.mText_Province)
    TextView mTextProvince;
    @BindView(R.id.mText_City)
    TextView mTextCity;
    @BindView(R.id.mText_district)
    TextView mTextDistrict;
    @BindView(R.id.mText_PlesaseSelect)
    TextView mTextPlesaseSelect;
    @BindView(R.id.mEdit_detailedAddress)
    EditText mEditDetailedAddress;
    @BindView(R.id.mBut_AddressPreserve)
    Button mButAddressPreserve;
    @BindView(R.id.mPopupwindow_Linear)
    LinearLayout mPopupwindowLinear;
    @BindView(R.id.address_title)
    TextView addressTitle;
    @BindView(R.id.text_addTitle)
    TextView textAddTitle;
    private PopupWindow popupWindow;

    private AddressBean bean;
    private String userid;
    private String AESToken;
    private String encryptAppSign;

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;

    private int state = 0;
    private PopupWindow mCityPop;
    private String aesName;
    private String aesPhone;
    private String aesCity;
    private String aesProvince;
    private String aesdistrict;
    private String aesAddressDetail;
    private String[] split;
    private String address_id;
    private String strProvince;
    private String strCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_address);
        ButterKnife.bind(this);
        init();
        onWindow();
    }


    private void init() {

        textAddTitle.setText("修改地址");

        bean = new AddressBean();

        Intent intent = getIntent();

        String province = intent.getStringExtra("province");
        String city = intent.getStringExtra("city");
        String district = intent.getStringExtra("district");
        String address = intent.getStringExtra("address");
        address_id = intent.getStringExtra("address_id");
        String people = intent.getStringExtra("people");
        String phone = intent.getStringExtra("phone");
        String state = intent.getStringExtra("state");

        if (state.equals("0")) {
            mImageAddressDefault.setImageResource(R.drawable.weixuanzhong4x);
        } else if (state.equals("1")) {
            mImageAddressDefault.setImageResource(R.drawable.xuanzhongtupian_4man);
        }


        mTextProvince.setText(province);

        mTextCity.setText(city);

        mTextDistrict.setText(district);

        mEditAddressName.setText(people);

        mEditAddressPhone.setText(phone);

        mEditDetailedAddress.setText(address);

        addressTitle.setText("修改收货地址");

    }


    @OnClick({R.id.mBut_AddressPreserve, R.id.mImage_Address_default, R.id.mText_PlesaseSelect})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.mBut_AddressPreserve:

                String name = mEditAddressName.getText().toString().trim();

                String phone = mEditAddressPhone.getText().toString().trim();

                String address = mEditDetailedAddress.getText().toString().trim();

                String Province = mTextProvince.getText().toString();

                String City = mTextCity.getText().toString();

                String district = mTextDistrict.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty()) {
                    Toast.makeText(this, "收货人姓名不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone.isEmpty()) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone.length() > 11 || phone.length() < 11) {
                    Toast.makeText(this, "请输入正确的手机号位数", Toast.LENGTH_SHORT).show();
                } else if (address.isEmpty() || mTextProvince.getText().equals("")) {
                    Toast.makeText(this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                } else if (address.length() < 6) {
                    Toast.makeText(this, "详细地址不能小于6位", Toast.LENGTH_SHORT).show();
                } else {
                    if (Province.contains("市")) {

                        strProvince = Province.substring(0, Province.indexOf("市"));

                    }

                    mChangeRequest(state, name, phone, address, strProvince, City, district);


                }
                break;

            case R.id.mImage_Address_default:

                if (state == 0) {

                    mImageAddressDefault.setImageResource(R.drawable.xuanzhongtupian_4man);

                    state = 1;
                } else {

                    mImageAddressDefault.setImageResource(R.drawable.weixuanzhong4x);

                    state = 0;
                }


                break;
            //选择地址进行添加
            case R.id.mText_PlesaseSelect:

                CityPpw();
                break;

        }

    }

    /**
     * 添加数据
     */

    private void mChangeRequest(int state, String name, String phone, String address, String Province, String City, String district) {

        //获取用户id 进行AES加密
        String user_id = AppUtils.get().getString("user_id", "");

        Log.d("UserAddressGuanLiActivi", "账号id   " + user_id);

        String Token = AppUtils.get().getString("token", "");

        Log.d("UserAddressGuanLiActivi", " TOken     " + Token);


        try {

            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();
            String timestamp = TimeUtils.dataOne(currentTime_today);

            Log.d("UserAddressGuanLiActivi", "时间戳    " + timestamp);


            //userID进行AES加密
            userid = Encrypt(user_id, BaseUrl.AESKey);

            //生成二次token 并进行加密
            String TwoToken = user_id + "," + Token + "," + timestamp;

            AESToken = Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            String app_sign = "UpAddress" + timestamp + BaseUrl.AESKey;

            String encryptMd5 = MD5Utils.encrypt(app_sign);

            encryptAppSign = Encrypt(encryptMd5, BaseUrl.AESKey);
            aesName = AESUtils.Encrypt(name, BaseUrl.AESKey);


            aesPhone = AESUtils.Encrypt(phone, BaseUrl.AESKey);

            aesProvince = AESUtils.Encrypt(Province, BaseUrl.AESKey);

            aesCity = AESUtils.Encrypt(City, BaseUrl.AESKey);


            aesdistrict = AESUtils.Encrypt(district, BaseUrl.AESKey);


            aesAddressDetail = AESUtils.Encrypt(address, BaseUrl.AESKey);
            //
            //            Log.d("UserAddAddressActivity", "aesAddress" + aesAddress);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //        if (mCurrentProviceName.contains("省") || mCurrentProviceName.contains("区") || mCurrentProviceName.contains("市")) {
        //             mCurrentProviceName.split()
        //
        //        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "UpAddress");
        params.put("user_id", userid);
        params.put("token", AESToken);
        params.put("app_sign", encryptAppSign);
        params.put("address_id", address_id);
        params.put("consignee", name);
        params.put("mobile", phone);
        params.put("state", String.valueOf(state));
        params.put("province", Province);
        params.put("city", City);
        params.put("district", district);
        params.put("address", address);

        Log.d("UserChangeAddressActivi", "加密的userID   " + userid);
        Log.d("UserChangeAddressActivi", "加密的token    " + AESToken);
        Log.d("UserChangeAddressActivi", "签名" + encryptAppSign);
        Log.d("UserChangeAddressActivi", "address_id  " + address_id);
        Log.d("UserChangeAddressActivi", "  " + name);
        Log.d("UserChangeAddressActivi", "   " + phone);
        Log.d("UserChangeAddressActivi", "state:" + state);
        Log.d("UserChangeAddressActivi", "   " + Province);
        Log.d("UserChangeAddressActivi", "   " + City);
        Log.d("UserChangeAddressActivi", "   " + district);
        Log.d("UserChangeAddressActivi", "    " + address);


        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {
                            Intent intent = new Intent(UserChangeAddressActivity.this, UserAddressGuanLiActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(UserChangeAddressActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

        //        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
        //            @Override
        //            public void onSuccess(String data) {
        //                Log.d("UserChangeAddressActivi", "修改收货地址+++" + data);
        //
        //                Gson gson = new Gson();
        //
        //                LoginMessageBean bean = gson.fromJson(data, LoginMessageBean.class);
        //
        //                if (bean == null || data == null) {
        //                    return;
        //                } else if (!bean.getStatus().equals("200")) {
        //
        //                    Toast.makeText(UserChangeAddressActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        //                } else {
        //                    Toast.makeText(UserChangeAddressActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        //                    mPopWindow();
        //
        //                }
        //
        //            }
        //
        //            @Override
        //            public void onError(String error) {
        //
        //            }
        //        });

    }

    /**
     *
     */


    private void CityPpw() {
        backgroundAlpha(0.4f);
        View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_myinfo_changecity_sector, null);

        setUpViews(view);

        mCityPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //设置背景颜色
        mCityPop.setBackgroundDrawable((new ColorDrawable(0x00000000)));

        //设置外部可点击
        mCityPop.setFocusable(false);

        mCityPop.setOutsideTouchable(false);


        mCityPop.setClippingEnabled(false);

        setUpData();

        mCityPop.setAnimationStyle(R.style.anim_menu_bottombar);

        mCityPop.showAtLocation(this.findViewById(R.id.mPopupwindow_Linear),

                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        mCityPop.setOnDismissListener(new poponDismissListener());

    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //城市选择弹出的ppw确定
            case R.id.btn_myinfo_sure:
                if (mCityPop.isShowing()) {
                    mTextProvince.setText(mCurrentProviceName);
                    mTextCity.setText(mCurrentCityName);
                    mTextDistrict.setText(mCurrentDistrictName);
                    mCityPop.dismiss();
                    backgroundAlpha(1.0f);
                }

                //                showSelectedResult();

                break;

            //ppw的取消
            case R.id.btn_myinfo_cancel:

                if (mCityPop.isShowing()) {
                    mCityPop.dismiss();
                    backgroundAlpha(1.0f);
                }

                break;
        }
    }

    private void showSelectedResult() {
        Toast.makeText(this, "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "。。区", Toast.LENGTH_SHORT).show();
    }

    private void setUpViews(View view) {

        mViewProvince = (WheelView) view.findViewById(R.id.id_province);
        mViewCity = (WheelView) view.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
        TextView mBtnConfirm = (TextView) view.findViewById(R.id.btn_myinfo_sure);
        TextView mTextcancle = (TextView) view.findViewById(R.id.btn_myinfo_cancel);
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);

        mTextcancle.setOnClickListener(this);

    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    /**
     * 滑动监听事件
     *
     * @param wheel    the wheel view whose state has changed
     * @param oldValue the old value of current item
     * @param newValue the new value of current item
     */

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            Log.d("UserSettingActivity", "更新的县区的地址" + mCurrentDistrictName);

            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }


    // TODO: 2017/9/27 弹出ppw  提交设置的密保问题，在调回到最当初的页面

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        TextView Tv = (TextView) view.findViewById(R.id.mText_LoginPwd);

        TextView mText = (TextView) view.findViewById(R.id.mText_SettingSucess);

        mText.setText("修改成功");

        Tv.setText("祝您购物愉快");

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        popupWindow.setOutsideTouchable(false);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);

        //popupwindow的弹出位置
        popupWindow.showAtLocation(findViewById(R.id.mPopupwindow_Linear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);
                Intent intent = new Intent(UserChangeAddressActivity.this, UserAddressGuanLiActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);

                //给Intent设置启动模式，让他直接返回到上级界面，不然就重新返回来了


            }
        });
        popupWindow.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }

    /**
     * 事件分发，使ppw点击外部不消失，也不响应点击事件
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mCityPop != null && mCityPop.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(event);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    private void onWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.ZhenDuanText));

            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }


}
