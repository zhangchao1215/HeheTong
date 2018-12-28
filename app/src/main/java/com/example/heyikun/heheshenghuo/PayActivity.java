package com.example.heyikun.heheshenghuo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.alipay.PayDemoActivity;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/9.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/9
 * <p>
 * 3：类描述：  微信支付
 * <p>
 * 4:类功能：
 */

public class PayActivity extends Activity {
    @BindView(R.id.mBut_Pay)
    Button mButPay;
    @BindView(R.id.AliPay_but)
    Button AliPayBut;
    private IWXAPI wxApi;
    private PayReq req;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String prepay_id;
    private String sign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_activity);
        ButterKnife.bind(this);
//        wxApi = WXAPIFactory.createWXAPI(this, "wxf88051a3634663a9", false);
//
//        // 将该app注册到微信
//
//        wxApi.registerApp("wxf88051a3634663a9");
//        aipay();

    }

    /**
     * 微信支付业务
     */
    private void wxPay(String result) {
        Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
        try {
            JSONObject json = new JSONObject(result);
            if (null != json && !json.has("retcode")) {

                req.appId = json.getString("appid");
                req.partnerId = json.getString("partnerid");
                req.prepayId = json.getString("prepayid");
                req.nonceStr = json.getString("noncestr");
                req.timeStamp = json.getString("timestamp");
                req.packageValue = json.getString("package");
                req.sign = json.getString("sign");
                req.extData = "app data"; // optional
                Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();

                wxApi.sendReq(req);
            } else {
                Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
                Toast.makeText(PayActivity.this, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("PAY_GET", "异常：" + e.getMessage());
            Toast.makeText(PayActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.mBut_Pay)
    public void onViewClicked() {
        //        wxPay("0.01");
        //        req.appId = Constants.APP_ID;
        //        req.partnerId = "1488231382";
        //        req.prepayId = "WX1217752501201407033233368018";
        //        req.nonceStr = "e7d161ac8d8a76529d39f5b4249ccb";
        //        req.timeStamp = "1412000000";
        //        req.packageValue = "Sign=WXpay";
        //        req.sign = "C380BEC2BFD727A4B6845133519F3AD6";
        //        req.extData = "app data"; //
        //        wxApi.sendReq(req);

//        toPay();


    }


    private void toPay() {
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信

        Map<String, String> params = new HashMap<>();

        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        PayReq request = new PayReq();

        String appid = "wxf88051a3634663a9";
        request.appId = appid;


        //商家id
        String partnerid = "1488231382";
        request.partnerId = partnerid;


        //订单号
        String prepayid = "wx201710271412141e8c9bad280456107288";
        request.prepayId = prepayid;

        Log.d("PayActivity", "订单" + prepay_id);

        request.packageValue = "Sign=WXPay";
        nonce_str = getNonceStr();
        request.nonceStr = nonce_str;

        Log.d("PayActivity", "随机" + nonce_str);
        request.timeStamp = timestamp;


        Log.d("PayActivity", "签名" + sign);

        Log.d("PayActivity", "request:上传上去的参数是什么" + request);
        /**
         * 生成签名sign
         */
        String str = "appid=wxf88051a3634663a9&noncestr=" + nonce_str + "&package=Sign=WXPay&partnerid=" + partnerid + "&prepayid=" + prepayid + "&timestamp=" + timestamp;
        //appid=wxf88051a3634663a9&noncestr=yso9vHeWTWITXYkT&package=Sign=WXPay&partnerid=1488231382&prepayid=wx20171025172433082690123b0424917222&timestamp1508923473
        Log.d("PayActivity", "第一次拼接的  " + str);
        String stringSignTemp = str + "&key=jdqa6ngvycg6ror9sizbtbem987jkyig";////注：key为商户平台设置的密钥key

        Log.d("PayActivity", "商户平台设置的密钥key" + stringSignTemp);
        String mSign = MD5Utils.encrypt(stringSignTemp).toUpperCase();

        Log.d("PayActivity", "最后的签名 " + mSign);
        request.sign = mSign;

        wxApi.sendReq(request);

    }


    public void aipay() {
        AliPayBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
//                Intent intent = new Intent(PayActivity.this, PayDemoActivity.class);
//                startActivity(intent);


            }
        });


    }

    public static String getNonceStr() {
        Random random = new Random();
        long val = random.nextLong();
        String res = MD5Utils.encrypt(val + "yzx").toUpperCase();
        if (32 < res.length()) return res.substring(0, 32);
        else return res;
    }
}
