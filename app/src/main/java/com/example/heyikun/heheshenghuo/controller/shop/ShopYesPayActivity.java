package com.example.heyikun.heheshenghuo.controller.shop;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.Constants;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.alipay.AuthResult;
import com.example.heyikun.heheshenghuo.alipay.PayResult;
import com.example.heyikun.heheshenghuo.controller.activity.ShareWebView;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.AliBean;
import com.example.heyikun.heheshenghuo.modle.bean.WeChatBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.bean.shop.ShopPayBean;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.PayPwdEditText;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.heyikun.hehe.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.R.id.mytext;
import static com.example.heyikun.heheshenghuo.alipay.PayDemoActivity.RSA2_PRIVATE;

/**
 * Created by hyk on 2017/11/29.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/29
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：商品最后确认购买页面
 */

public class ShopYesPayActivity extends BaseActivity implements View.OnClickListener, WXPayEntryActivity.PayListener {


    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Text_allMoney)
    TextView TextAllMoney;
    @BindView(R.id.relative1)
    RelativeLayout relative1;
    @BindView(R.id.Text_allYibi)
    TextView TextAllYibi;
    @BindView(R.id.mtext_yiBi)
    TextView mtextYiBi;
    @BindView(R.id.mtext_yiBi_yu_e)
    TextView mtextYiBiYuE;
    @BindView(R.id.mtext_change)
    TextView mtextChange;
    @BindView(R.id.shop_change_relative)
    RelativeLayout shopChangeRelative;
    @BindView(R.id.shop_input_money)
    EditText shopInputMoney;
    @BindView(R.id.shop_text_yes)
    TextView shopTextYes;
    @BindView(R.id.shop_yes_relative)
    RelativeLayout shopYesRelative;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text_account_yu_e)
    TextView textAccountYuE;
    @BindView(R.id.radio_yuEpay)
    ImageView radioYuEpay;
    @BindView(R.id.Shop_Image_WeixinPay)
    ImageView ShopImageWeixinPay;
    @BindView(R.id.Shop_Image_Alipay)
    ImageView ShopImageAlipay;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.Text_Bottom_pay_money)
    TextView TextBottomPayMoney;
    @BindView(R.id.mBut_YesPay)
    Button mButYesPay;
    @BindView(R.id.relative2)
    RelativeLayout relative2;
    private int PAY_FLAG = 1;
    private IWXAPI wxApi;
    private String mUserId;
    private String mtoken;
    private WXPayEntryActivity wxPayEntryActivity;
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2017090608586413";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String order_id;
    private String encryptUserid;
    private String encryptToken;
    private int pay_integral;

    private double payable;
    private String aeSordId;
    private String editMoney;
    private int ybAll_money;
    private int order_integral;
    private int change_integral;
    private String encryptUserId;
    private String encryptToken1;
    private String pay_integral1;
    private PayPwdEditText edit;
    private String editPass;
    private String user_name;
    private String user_image;
    private ShopPayBean.DataBean dataBean;

    @Override
    protected int layoutId() {
        return R.layout.activity_shop_confirm_pay;
    }

    @Override
    protected void initView() {
        user_name = AppUtils.get().getString("user_name", "");

        user_image = AppUtils.get().getString("user_image", "");

        init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        LodaData();
    }

    @Override
    protected void initLisenter() {

    }

    private void init() {


        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");

        wxApi = WXAPIFactory.createWXAPI(this, "wxf88051a3634663a9", false);

        // 将该app注册到微信

        wxApi.registerApp("wxf88051a3634663a9");


        mUserId = AppUtils.get().getString("user_id", "");


        mtoken = AppUtils.get().getString("token", "");

    }

    private void LodaData() {

        Log.d("ShopYesPayActivity", "接手的订单id" + order_id);
        //获取时间戳

        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        //生成二次token
        String TwotoKen = mUserId + "," + mtoken + "," + timestamp;

        try {
            encryptUserid = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);
            encryptToken = AESUtils.Encrypt(TwotoKen, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "GetPayInfo");
        params.put("user_id", encryptUserid);
        params.put("token", encryptToken);
        params.put("order_id", order_id);

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.d("ShopYesPayActivity", response);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ShopPayBean>() {
                    @Override
                    public void onSuccess(int statusCode, ShopPayBean response) {
                        if (response.getStatus().equals("200")) {
                            dataBean = response.getData();

                            //我的余额
                            textAccountYuE.setText(dataBean.getYour_surplus() + "");

                            //费用总计
                            TextAllMoney.setText(dataBean.getCost_total() + "");
                            //易币总计 需要花费的易币
                            order_integral = dataBean.getOrder_integral();
                            TextAllYibi.setText(String.valueOf(order_integral));

                            //易币余额 你的总易币数
                            ybAll_money = dataBean.getYb_money();
                            mtextYiBiYuE.setText(String.valueOf(ybAll_money));

                            //应付总额

                            TextBottomPayMoney.setText(dataBean.getGoods_amount() + "");


                            //实际用的易券
                            mtextYiBi.setText(dataBean.getPay_integral() + "");

                            pay_integral = dataBean.getPay_integral();

                            payable = dataBean.getPayable();

                        }


                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }


    @OnClick({R.id.Image_Back, R.id.mtext_change, R.id.shop_text_yes, R.id.Shop_Image_Alipay,
            R.id.Shop_Image_WeixinPay, R.id.mBut_YesPay, R.id.radio_yuEpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;

            //修改易币金额
            case R.id.mtext_change:

                shopChangeRelative.setVisibility(View.GONE);

                shopYesRelative.setVisibility(View.VISIBLE);
                break;

            //修改完之后确认
            case R.id.shop_text_yes:

                editMoney = shopInputMoney.getText().toString().trim();

                if (editMoney.isEmpty()) {


                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                    shopChangeRelative.setVisibility(View.VISIBLE);
                    shopYesRelative.setVisibility(View.GONE);

                    TextAllYibi.setText(String.valueOf(order_integral));

                    //易币余额 你的总易币数
                    mtextYiBiYuE.setText(String.valueOf(ybAll_money));
                    mtextYiBiYuE.setTextColor(getResources().getColor(R.color.text_colot));

                } else {

                    mtextYiBi.setText(editMoney + "");

                    int YibiMoney = Integer.parseInt(editMoney);

                    Log.d("ShopYesPayActivity", "YibiMoney:" + YibiMoney);
                    Log.d("ShopYesPayActivity", "ybAll_money:" + ybAll_money);

                    if (YibiMoney > order_integral) {

                        mtextYiBiYuE.setText("不可超出当前易币总数");
                        mtextYiBiYuE.setTextColor(getResources().getColor(R.color.red));

                        mButYesPay.setClickable(false);
                        mButYesPay.setBackgroundResource(R.color.color_d8d8d8);
                    } else {
                        mButYesPay.setClickable(true);
                        mtextYiBiYuE.setText(String.valueOf(ybAll_money));

                        change_integral = YibiMoney;

                    }
                    shopChangeRelative.setVisibility(View.VISIBLE);
                    shopYesRelative.setVisibility(View.GONE);

                }


                break;

            //余额支付
            case R.id.radio_yuEpay:

                PAY_FLAG = 0;

                radioYuEpay.setImageResource(R.drawable.zhifuxuanzhong_4man);

                ShopImageAlipay.setImageResource(R.drawable.weixuanzhong4x);
                ShopImageWeixinPay.setImageResource(R.drawable.weixuanzhong4x);


                break;

            // 微信支付
            case R.id.Shop_Image_WeixinPay:

                PAY_FLAG = 1;

                radioYuEpay.setImageResource(R.drawable.weixuanzhong4x);

                ShopImageAlipay.setImageResource(R.drawable.weixuanzhong4x);
                ShopImageWeixinPay.setImageResource(R.drawable.zhifuxuanzhong_4man);

                mButYesPay.setClickable(true);
                mButYesPay.setBackgroundResource(R.color.colorTextYangXIn);
                break;

            //支付宝支付
            case R.id.Shop_Image_Alipay:

                PAY_FLAG = 2;
                mButYesPay.setBackgroundResource(R.color.colorTextYangXIn);
                mButYesPay.setClickable(true);

                radioYuEpay.setImageResource(R.drawable.weixuanzhong4x);
                ShopImageAlipay.setImageResource(R.drawable.zhifuxuanzhong_4man);
                ShopImageWeixinPay.setImageResource(R.drawable.weixuanzhong4x);


                break;

            //点击最后进行确认支付
            case R.id.mBut_YesPay:

                if (PAY_FLAG == 0) {
                    pay_integral1 = mtextYiBi.getText().toString().trim();
                    mPop();
                } else if (PAY_FLAG == 1) {

                    String pay_integral = mtextYiBi.getText().toString().trim();

                    Log.d("ShopYesPayActivity", "修改易券的数据微信" + pay_integral);

                    requestDataWeChat("WeChat", order_id, pay_integral, String.valueOf(payable));

                    Log.d("ShopYesPayActivity", pay_integral);

                } else if (PAY_FLAG == 2) {
                    String pay_integral = mtextYiBi.getText().toString().trim();

                    Log.d("ShopYesPayActivity", "修改易券的数据支付宝" + pay_integral);

                    requestDataAlipay("zhifubao", order_id, pay_integral, String.valueOf(payable));

                    Log.d("ShopYesPayActivity", pay_integral);
                }


                break;
        }
    }

    /**
     * 进行微信的支付
     *
     * @param prepayid
     */
    private void toWeChatPay(String prepayid) {

        if (prepayid.equals("")) {
            return;
        }

        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信


        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);
        WxPayCfg.wxpayLisenter = this;


        PayReq request = new PayReq();
        String appid = Constants.APP_ID;
        request.appId = appid;
        //商家id
        String partnerid = Constants.MCH_ID;
        request.partnerId = partnerid;
        //订单号
        request.prepayId = prepayid;

        request.packageValue = "Sign=WXPay";

        String nonce_str = getNonceStr();

        request.nonceStr = nonce_str;


        request.timeStamp = timestamp;


        Log.d("PayActivity", "request:上传上去的参数是什么" + request);
        /**
         * 生成签名sign
         */
        String str = "appid=" + Constants.APP_ID + "&noncestr=" + nonce_str + "&package=Sign=WXPay&partnerid=" + partnerid + "&prepayid=" + prepayid + "&timestamp=" + timestamp;
        //appid=wxf88051a3634663a9&noncestr=yso9vHeWTWITXYkT&package=Sign=WXPay&partnerid=1488231382&prepayid=wx20171025172433082690123b0424917222&timestamp1508923473
        String stringSignTemp = str + "&key=" + Constants.API_KEY;////注：key为商户平台设置的密钥key
        String mSign = MD5Utils.encrypt(stringSignTemp).toUpperCase();

        request.sign = mSign;

        wxApi.sendReq(request);

    }

    /**
     * 生成的随机字符串
     *
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        long val = random.nextLong();
        String res = MD5Utils.encrypt(val + "yzx").toUpperCase();
        if (32 < res.length()) return res.substring(0, 32);
        else return res;
    }


    private void requestDataAlipay(String pay_type, String order_id, String pay_integral, String payable) {

        if (pay_type == null || order_id == null) {
            return;
        }

        //获取时间戳
        mUserId = AppUtils.get().getString("user_id", "");


        String mtoken = AppUtils.get().getString("token", "");


        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        //生成二次token
        String TwotoKen = mUserId + "," + mtoken + "," + timestamp;

        String md5 = "Payment" + timestamp + BaseUrl.AESKey;

        String signMd5 = MD5Utils.encrypt(md5);

        String encryptSign = null;


        String payType = null;

        String encryptToken1 = null;

        String encryptUserId = null;

        String AESpay_integral = null;

        String AESpayable = null;
        try {
            String encodeBase64 = Base64.encodeToString(order_id.getBytes(), Base64.DEFAULT);


            encryptToken1 = AESUtils.Encrypt(TwotoKen, BaseUrl.AESKey);

            encryptSign = AESUtils.Encrypt(signMd5, BaseUrl.AESKey);

            aeSordId = AESUtils.Encrypt(encodeBase64.toString(), BaseUrl.AESKey);


            payType = AESUtils.Encrypt(pay_type, BaseUrl.AESKey);

            encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);


            AESpay_integral = AESUtils.Encrypt(pay_integral, BaseUrl.AESKey);

            AESpayable = AESUtils.Encrypt(payable, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "Payment");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken1);
        params.put("app_sign", encryptSign); //     签名
        params.put("order_id", aeSordId);  //   订单id AES加密
        params.put("pay_type", payType); //支付方式 AES加密
        params.put("pay_integral", AESpay_integral);
        params.put("payable", AESpayable);

        App.myOkHttp.post().url(BaseUrl.BaseUrl).params(params)
                .enqueue(new GsonResponseHandler<AliBean>() {
                    @Override
                    public void onSuccess(int statusCode, AliBean response) {

                        if (response.getStatus().equals("200")) {


                            String orderString = response.getOrderString();


                            if (!orderString.equals("")) {

                                aliPay(orderString);
                            }


                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }

    private void requestDataWeChat(String pay_type, String order_id, String pay_integral, String payable) {

        if (pay_type == null || order_id == null) {
            return;
        }
        Log.d("ShopYesPayActivity","实际支付的易币金额   "+ pay_integral);

        //获取时间戳
        mUserId = AppUtils.get().getString("user_id", "");


        String mtoken = AppUtils.get().getString("token", "");


        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        //生成二次token
        String TwotoKen = mUserId + "," + mtoken + "," + timestamp;

        String md5 = "Payment" + timestamp + BaseUrl.AESKey;

        String signMd5 = MD5Utils.encrypt(md5);

        String encryptSign = null;


        String payType = null;

        encryptToken1 = null;

        encryptUserId = null;

        String AESpay_integral = null;

        String AESpayable = null;

        String pay_pwd = null;
        try {

            pay_pwd = AESUtils.Encrypt("123456", BaseUrl.AESKey);

            String encodeBase64 = Base64.encodeToString(order_id.getBytes(), Base64.DEFAULT);


            encryptToken1 = AESUtils.Encrypt(TwotoKen, BaseUrl.AESKey);

            encryptSign = AESUtils.Encrypt(signMd5, BaseUrl.AESKey);

            aeSordId = AESUtils.Encrypt(encodeBase64.toString(), BaseUrl.AESKey);


            payType = AESUtils.Encrypt(pay_type, BaseUrl.AESKey);

            encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);


            AESpay_integral = AESUtils.Encrypt(pay_integral, BaseUrl.AESKey);

            AESpayable = AESUtils.Encrypt(payable, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "Payment");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken1);
        params.put("app_sign", encryptSign); //     签名
        params.put("order_id", aeSordId);  //   订单id AES加密
        params.put("pay_type", payType); //支付方式 AES加密
        params.put("pay_integral", AESpay_integral);
        params.put("payable", AESpayable);
        //        params.put("pay_pass", pay_pwd);
        App.myOkHttp.post().url(BaseUrl.BaseUrl).params(params)
                .enqueue(new GsonResponseHandler<WeChatBean>() {
                    @Override
                    public void onSuccess(int statusCode, WeChatBean response) {

                        if (response.getStatus().equals("200")) {

                            String prepayid = response.getPrepayid();


                            Log.d("ShopYesPayActivity", response.getMessage());
                            if (!prepayid.equals("")) {

                                toWeChatPay(prepayid);
                                //                                finish();
                            }


                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

        //                .enqueue(new RawResponseHandler() {
        //                    @Override
        //                    public void onSuccess(int statusCode, String response) {
        //                        Log.d("ShopYesPayActivity", response);
        //                    }
        //
        //                    @Override
        //                    public void onFailure(int statusCode, String error_msg) {
        //
        //                    }
        //                });

    }

    private void requestDataYue(String pay_type, String order_id, String pay_integral, String payable, String pwd) {

        if (pay_type == null || order_id == null) {
            return;
        }

        //获取时间戳
        mUserId = AppUtils.get().getString("user_id", "");


        String mtoken = AppUtils.get().getString("token", "");


        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        //生成二次token
        String TwotoKen = mUserId + "," + mtoken + "," + timestamp;

        String md5 = "Payment" + timestamp + BaseUrl.AESKey;

        String signMd5 = MD5Utils.encrypt(md5);

        String encryptSign = null;


        String payType = null;

        String encryptToken1 = null;

        String encryptUserId = null;

        String AESpay_integral = null;

        String AESpayable = null;

        String pay_pwd = null;
        try {

            pay_pwd = AESUtils.Encrypt(pwd, BaseUrl.AESKey);

            String encodeBase64 = Base64.encodeToString(order_id.getBytes(), Base64.DEFAULT);


            encryptToken1 = AESUtils.Encrypt(TwotoKen, BaseUrl.AESKey);

            encryptSign = AESUtils.Encrypt(signMd5, BaseUrl.AESKey);

            aeSordId = AESUtils.Encrypt(encodeBase64.toString(), BaseUrl.AESKey);


            payType = AESUtils.Encrypt(pay_type, BaseUrl.AESKey);

            encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);


            AESpay_integral = AESUtils.Encrypt(pay_integral, BaseUrl.AESKey);

            AESpayable = AESUtils.Encrypt(payable, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "Payment");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken1);
        params.put("app_sign", encryptSign); //     签名
        params.put("order_id", aeSordId);  //   订单id AES加密
        params.put("pay_type", payType); //支付方式 AES加密
        params.put("pay_integral", AESpay_integral);
        params.put("payable", AESpayable);
        params.put("pay_pass", pay_pwd);
        App.myOkHttp.post().url(BaseUrl.BaseUrl).params(params)
                .enqueue(new GsonResponseHandler<WeChatBean>() {
                    @Override
                    public void onSuccess(int statusCode, WeChatBean response) {

                        if (response.getStatus().equals("200")) {
                            Intent intent = new Intent(ShopYesPayActivity.this, ShareWebView.class);
                            intent.putExtra("url", "https://hehe.heyishenghuo.com/mobile2/user.php?act=order_list");
                            intent.putExtra("title", "全部订单");
                            startActivity(intent);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            finish();
                            Toast.makeText(ShopYesPayActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ShopYesPayActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

        //                .enqueue(new RawResponseHandler() {
        //                    @Override
        //                    public void onSuccess(int statusCode, String response) {
        //                        Log.d("ShopYesPayActivity", response);
        //
        //                    }
        //
        //                    @Override
        //                    public void onFailure(int statusCode, String error_msg) {
        //
        //                    }
        //                });

    }


    /**
     * 支付宝业务
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();

                    Log.d("WebViewActivity", "支付的回调" + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(ShopYesPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        changeOrderId(order_id);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ShopYesPayActivity.this, "支付取消了", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    Log.d("PayDemoActivity", resultStatus);
                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(ShopYesPayActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(ShopYesPayActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }


    };

    public void changeOrderId(String order_id) {

        //获取时间戳
        mUserId = AppUtils.get().getString("user_id", "");


        String mtoken = AppUtils.get().getString("token", "");


        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        //生成二次token
        String TwotoKen = mUserId + "," + mtoken + "," + timestamp;


        String AEStoken = null;

        String AESUserid = null;
        try {
            AEStoken = AESUtils.Encrypt(TwotoKen, BaseUrl.AESKey);

            AESUserid = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "UpOrderStatus");
        params.put("user_id", AESUserid);
        params.put("token", AEStoken);
        params.put("order_id", order_id);

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.d("WebView", "修改支付的信息 " + response);

                        Gson gson = new Gson();
                        try {
                            ChangePwdBean changePwdBean = gson.fromJson(response, ChangePwdBean.class);

                            if (changePwdBean.getStatus().equals("200")) {
                                Intent intent = new Intent(ShopYesPayActivity.this, ShareWebView.class);
                                intent.putExtra("url", "https://hehe.heyishenghuo.com/mobile2/user.php?act=order_list");
                                intent.putExtra("title", "全部订单");
                                startActivity(intent);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }

    /**
     * 进行支付
     */

    private void aliPay(final String ordString) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ShopYesPayActivity.this);
                Map<String, String> result = alipay.payV2(ordString, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }


    private void mPop() {
        backgroundAlpha(0.4f);
        View view = LayoutInflater.from(this)
                .inflate(R.layout.activity_yu_e_pay_ppw, null);

        PopupWindow ppw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                , true);
        TextView textName = (TextView) view.findViewById(R.id.Pay_Name);
        TextView payPrice = (TextView) view.findViewById(R.id.pay_price);
        TextView payYibi = (TextView) view.findViewById(R.id.pay_yibi);
        ImageView image = (ImageView) view.findViewById(R.id.pay_image);

        payPrice.setText(dataBean.getCost_total() + "");
        payYibi.setText(String.valueOf(order_integral));

        edit = (PayPwdEditText) view.findViewById(mytext);

        edit.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.colorAccent,
                R.color.black, 20);
        edit.setShowPwd(true);


        Button payBut = (Button) view.findViewById(R.id.pay_But);
        payBut.setOnClickListener(this);


        textName.setText(user_name);

        Glide.with(this)
                .load(user_image)
                .placeholder(R.drawable.touxiang_nan_man_4x)
                .into(image);
        ppw.setBackgroundDrawable(new BitmapDrawable());

        ppw.setOutsideTouchable(true);

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);
        ppw.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        ppw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        ppw.showAtLocation(findViewById(R.id.mPPw_Linear), Gravity.CENTER, 0, 0);
        ppw.setOnDismissListener(new poponDismissListener());

    }


    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    /**
     * popupwindow的内部类
     */
    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }

    //余额支付的点击事件
    @Override
    public void onClick(View v) {
        pay_integral1 = mtextYiBi.getText().toString().trim();
        editPass = edit.getPwdText().trim();
        Log.d("ShopYesPayActivity", "支付密码   " + editPass);
        if (editPass.equals("")) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {

            requestDataYue("balances", order_id, pay_integral1, String.valueOf(payable), editPass);
        }


    }

    private void CheckPayPass(String pass) {
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        String md5 = "CheckPayPass" + timestamp + BaseUrl.AESKey;

        String encryptMd5 = MD5Utils.encrypt(md5);

        String aesSign = null;
        String aesPass = null;
        try {
            aesSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);
            aesPass = AESUtils.Encrypt(pass, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "CheckPayPass");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken1);
        params.put("app_sign", aesSign);
        params.put("pay_pass", aesPass);
        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.d("ShopYesPayActivity", "支付结果     " + response);

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }

    //这是我那个吊起支付的页面
    @Override
    public void wxPayResult(int resultId) {

        Log.d("ShopYesPayActivity", "resultId:" + resultId);

        switch (resultId) {
            case 0:
                Intent intent = new Intent(this, ShareWebView.class);
                intent.putExtra("url", "https://hehe.heyishenghuo.com/mobile2/user.php?act=order_list");
                intent.putExtra("title", "全部订单");
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

                break;
            case -2:

                break;
        }

    }

}
