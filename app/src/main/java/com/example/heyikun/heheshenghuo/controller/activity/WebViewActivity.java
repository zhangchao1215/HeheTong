package com.example.heyikun.heheshenghuo.controller.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.heyikun.heheshenghuo.Constants;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.alipay.AuthResult;
import com.example.heyikun.heheshenghuo.alipay.PayResult;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.AliBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;

import static com.example.heyikun.heheshenghuo.alipay.PayDemoActivity.RSA2_PRIVATE;


/**
 * Created by hyk on 2017/9/21.
 */

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.Main_webView)
    WebView MainWebView;

    private String url;
    private IWXAPI wxApi;
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2017090608586413";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String mUserId;


    @Override
    protected int layoutId() {
        return R.layout.activity_ceti_webview;
    }

    @Override
    protected void initView() {

        wxApi = WXAPIFactory.createWXAPI(this, "wxf88051a3634663a9", false);

        // 将该app注册到微信

        wxApi.registerApp("wxf88051a3634663a9");


        /**
         * 设置的webview
         */
        MainWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = MainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);//设置自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8"); //这是编码格式
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);

        webSettings.setGeolocationEnabled(true);

        //跳转过来的URL
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        Log.d("WebViewActivity", "跳转过来的URL" + url);

        MainWebView.loadUrl(url);


        //        //        在内部加载网页
        //        MainWebView.setWebViewClient(new WebViewClient() {
        //            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        //            @Override
        //            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        //                MainWebView.loadUrl(request.getUrl().toString());
        //
        //                return true;
        //            }
        //        });


        //截取需要的网址
        MainWebView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.d("WebViewActivity", "打印的网址++" + url);

                //在这里截取网址

                Map<String, String> parameters = getParameters(url);


                if (url.contains("fenxiang")) {
                }

                if (url.contains("pay_type") && url.contains("prepay_id")) {
                    //获取支付方式

                    String pay_type = parameters.get("pay_type");
                    //获取支付流水
                    String prepay_id = parameters.get("prepay_id");
                    /**
                     * 进行微信支付
                     */
                    toPay(prepay_id);
                    Log.d("WebViewActivity", "微信支付流水+===" + prepay_id);
                }


                //是否包含
                if (url.contains("pay_type") && url.contains("order_id")) {
                    //获取支付宝支付订单
                    String order_id = parameters.get("order_id");

                    //获取支付方式
                    String pay_type = parameters.get("pay_type");

                    /**
                     * 进行数据的请求 ，获取支付方式以及支付订单，获取完这些信息后在进行第二次请求，进行支付
                     */


                    requestData(pay_type, order_id);

                } else {
                    view.loadUrl(url);
                }


                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                if (url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                    Log.d("WebViewActivity", "微信的URL  " + url);
                    return true;
                } else if (parseScheme(url)) {
                    try {
                        Intent intent;
                        intent = Intent.parseUri(url,
                                Intent.URI_INTENT_SCHEME);
                        intent.addCategory("android.intent.category.BROWSABLE");
                        intent.setComponent(null);
                        // intent.setSelector(null);
                        startActivity(intent);

                        Log.d("WebViewActivity", "支付宝的URL++  " + url);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    view.loadUrl(url);
                }
                return false;
            }

        });


    }

    private void toPay(String prepayid) {

        if (prepayid.equals("")) {
            return;
        }

        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信



        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

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
        Log.d("PayActivity", "第一次拼接的  " + str);
        String stringSignTemp = str + "&key=" + Constants.API_KEY;////注：key为商户平台设置的密钥key

        Log.d("PayActivity", "商户平台设置的密钥key" + stringSignTemp);
        String mSign = MD5Utils.encrypt(stringSignTemp).toUpperCase();

        Log.d("WebViewActivity", "微信支付订单+++" + mSign);
        request.sign = mSign;

        wxApi.sendReq(request);

    }

    private void requestData(String pay_type, String order_id) {

        if (pay_type.equals("") || order_id.equals("")) {
            return;
        }

        Log.d("WebViewActivity", "支付方式 +" + pay_type);

        Log.d("WebViewActivity", "支付流水号+" + order_id);


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
        try {
            String encodeBase64 = Base64.encodeToString(order_id.getBytes(), Base64.DEFAULT);

            Log.d("WebViewActivity", "Base64加密的流水++  " + encodeBase64);

            encryptToken1 = AESUtils.Encrypt(TwotoKen, BaseUrl.AESKey);

            encryptSign = AESUtils.Encrypt(signMd5, BaseUrl.AESKey);

            String AESordId = AESUtils.Encrypt(encodeBase64.toString(), BaseUrl.AESKey);

            Log.d("WebViewActivity", "aes加密的id+  " + AESordId);

            payType = AESUtils.Encrypt(pay_type, BaseUrl.AESKey);

            encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);

            Map<String, String> params = new HashMap<>();
            params.put("action", "Payment");
            params.put("user_id", encryptUserId);
            params.put("token", encryptToken1);
            params.put("app_sign", encryptSign); //     签名
            params.put("order_id", AESordId);  //   订单id AES加密
            params.put("pay_type", payType); //支付方式 AES加密
            Log.d("WebViewActivity", "测试的加密id" + AESordId);
            OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
                @Override
                public void onSuccess(String data) {
                    Log.d("WebViewActivity", "支付返回的数据+++" + data);

                    Gson gson = new Gson();
                    AliBean bean = gson.fromJson(data, AliBean.class);

                    if (bean == null || bean.getOrderString().equals("")) {
                        return;
                    }

                    String orderString = bean.getOrderString();

                    Log.d("WebViewActivity", "支付订单" + orderString);

                    aliPay(orderString);

                }

                @Override
                public void onError(String error) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


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

//                    Log.d("WebViewActivity", "支付的回调" + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(WebViewActivity.this, "支付成功", Toast.LENGTH_SHORT).show();



                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(WebViewActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(WebViewActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(WebViewActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }


    };

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
                PayTask alipay = new PayTask(WebViewActivity.this);
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

    /**
     * 把得到的网址进行截取
     *
     * @param url 得到的URL
     * @return
     */
    public Map<String, String> getParameters(String url) {
        Map<String, String> params = new HashMap<String, String>();
        if (url == null || "".equals(url.trim())) {
            return params;
        }
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String[] split = url.split("[?]");
        if (split.length == 2 && !"".equals(split[1].trim())) {
            String[] parameters = split[1].split("&");
            if (parameters != null && parameters.length != 0) {
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i] != null
                            && parameters[i].trim().contains("=")) {
                        String[] split2 = parameters[i].split("=");
                        //split2可能为1，可能为2
                        if (split2.length == 1) {
                            //有这个参数但是是空的
                            params.put(split2[0], "");
                        } else if (split2.length == 2) {
                            if (!"".equals(split2[0].trim())) {
                                params.put(split2[0], split2[1]);
                            }
                        }
                    }
                }
            }
        }
        return params;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

    //监听webview返回键
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == keyEvent.KEYCODE_BACK) {
            if (MainWebView.canGoBack()) {
                MainWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, keyEvent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainWebView.stopLoading();
        MainWebView.removeAllViews();
        MainWebView.destroy();
        MainWebView = null;
    }

    public boolean parseScheme(String url) {

        if (url.contains("platformapi/startapp")) {

            return true;
        } else {

            return false;
        }
    }

}
