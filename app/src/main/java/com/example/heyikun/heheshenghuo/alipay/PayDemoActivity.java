package com.example.heyikun.heheshenghuo.alipay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.alipay.util.OrderInfoUtil2_0;

import java.util.Map;

/**
 * 重要说明:
 * <p>
 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
 */
public class PayDemoActivity extends FragmentActivity {

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2017090608586413";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYob+oQTodvgcLqWAbk+J+yTU4XPEtEbokwygufwe7zpyCihO3GKEWzRbL5t14ZJKaMzr3P8k4ugz1WvGJcuTnFq4ZPU8ZfT8JAYdLWDQSOeimyWNStwGcmI/j6bXq6y6myI0pbQgLhH13oHJNMfbbUoTP/I9LUSj2g91S75WVta52CgZmw00iiiyCxWQqBLi+mDOxBT4Kwa/0en057OGsVhAxv85aCMZ/IPhm6Ta5fFayWGqUGLvL7kwA4hhFk68Xl3UGT6QQaHEKYAzrXSaS9AHdPuFRKlrDE//vrfONPrhHMOxXvpvjThhYIykoebj6DbYmxUtJ4wl+EMSRoOHpAgMBAAECggEAPwMHzL6g74Z0Aix6sOfsqcsHXa2BI8odvu+Stx9aYf56PqoiWYShfHhO4P7+j6V1oJNl1I1Q1Up57xEMhmIYfg6u8VyOO0eprl4jLMfNN3kQw0qA5rUGxU92l/D0WXeeWtyQ6nlIyPh5k9l5VsU51HHMwtDRl5Z6AsuNo5+lcZhbC/8r4hbuhPFCaMYTRpdgM9GaQqEqWeHUxOHKB2Mx9PIwZVswzvzhbSDKyHYKmD072WjytoCxQnw/+dU18BvoPy88tFNKyNRH2N0ryZgw+tzeXFolZifcddUcOhAPK9MoMFb+jGhnXiQMWHlLYTpcIJ4k7PvIFG2s+Szq4iqnAQKBgQDjbALV5XNEZbjHR/9zKO60kdI+8gJemVHFy+ywhW78XK+oi+KSI6tBBHdrMbWyKp61H2KYbh+IUqw0k0rTvbNsrFm+cQ94VzjBosYodtf2mJDUN/6FrcNUQRchIuERS5mnZUvpBAUglnMx2kHRyExiFJQO1z9uZAJb6UyNNHIBBQKBgQCrz8w5bNkv/I4b3K4PKY7C0ewf2ic8I/Y1yi0SaKv7L4Qj58KvBVZAMwiza/8bqUjtgkMQ+vJQqWx1FK0R6pCMpXSOINT+zB5rm+87cLKn2Y5jc/gg5W8s+4nyL4qjnro497PSZV8Q9nR2MYbGF+jvsjVTCFzwk55bKQjopmdClQKBgQCDdX0SHWcK423zK8gazk9la2FH52a9Pg0Js/4mb4sfL4iOegXHCf1FQQqymPJ5ga9p7TF2AToS+A74+SdozCA6MkpSDlKt8mUpcSjwXPorXjdhpNhod3AQdOukyN+muregDqrZj+xS0QTXjV08oXadv11yUrQk4ISIkowgl29K7QKBgDYaw/c5fdOMtruzbOS9c4WKUc9eBYj80iXyOutXJwF83yHnc/llttmUuKK32ag+UQbqRHBudixMjij4j9/afBaua9vuHuT2JoZAnr+bJ8ePzTUoafUCC1ahB7nKmuAXthEGgAw7fAp9cgNeaVCsauBAwGYbdhkUg4O1kmahMFdZAoGBANTTh9pHywzC7270ym4gkDDt1cByySxkx4NS3slra5C7+MePR/Fq7im/0u6Kjzi954ay6NjaYpUKAt+xhsyzvX3A5oIrFLlXzgd6vl0X6EfCCCOinaY7oFRmRgtPTSDzBbVreQvuTXpTY2jnB/TQbiSryqtwylBIQOFkPU49g7be";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

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
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(PayDemoActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayDemoActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String orderString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_main);
        init();
    }

    private void init() {

//        orderString = "alipay_sdk=alipay-sdk-php-20161101&app_id=2017090608586413&biz_content=%7B%22subject%22%3A%22test123%22%2C%22out_trade_no%22%3A%22appzfb2222%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Ftest2.heyishenghuo.com%2Fapi%2Fincludes%2Frespond.php&sign_type=RSA2&timestamp=2017-10-30+09%3A29%3A36&version=1.0&sign=P6aZD1IbEZwPlIGXp993LA%2BJdQaZcpg6WKCrlltYUkBYsHegyp8WdZOmDRsyQKmAnw%2FjCJYLXI%2BmubNTUHlwne1AelxxDgRPTdQfNN%2FxmTwWskN0R32Gm4mQlqI%2BVRcd%2FMNy0iQpc4d35EUQY%2BS3fWVsnQIoILakS1FbTQEuEo6sodAjdmuyCKQtWbACMtiBsscE6wYctI68AWjlaem0x8tAVv22%2BBLu2mbLqyAa3rkovzvrZ2B0oGzM1S4S9VM%2BDBTOc9EPno00zo3G1JiEKPGA09IkdhRwpK1aMm5AfgxR%2BUM9ECXBn7oNmRil5rSaKQBnTm9RA0WrHXAN8w9KwA%3D%3D";


//        orderString = "alipay_sdk=alipay-sdk-php-20161101&app_id=2017090608586413&biz_content=%7B%22body%22%3A%22%22%2C%22subject%22%3A%22%5Cu548c%5Cu5408%5Cu751f%5Cu6d3b-%5Cu7f51%5Cu7edc%5Cu5546%5Cu54c1%22%2C%22out_trade_no%22%3A%22appzfb-299-%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Ftest2.heyishenghuo.com%2Fapi%2Fincludes%2Frespond.php&sign_type=RSA2&timestamp=2017-10-30+17%3A37%3A29&version=1.0&sign=GXu1WkmcpdwulTAWcXtmjZJ2rh4xY%2Bi9ICikTVBHogUwcIi%2BFRowVjRC9QbcXqThVwmTG9HxUCC5nY0QRkWdrIjewU2JQjCqpOjOJhfWvubtm7aeKlgywdRrkWT41hArFE2SZHQ34QDLLLWhyzKOSRBuMBYkG66RBILSI%2FtSHebABAbjEDKk9HCOrjwqzKUXJIJzzC3zJiuOrrD7axNvSVq8jv3ARN0GsCB2gS4VbujVsCaqBiXhO0lsrlc6rataL0VbBI%2B7jmcBIAb6wSx1m7Xt6uinr9iQip6o7g1JfSGan%2BR4s4pKw%2BJuTB7Ucw%2FKZcumGJlfpX2h9II32raMRA%3D%3D";

        orderString = "alipay_sdk=alipay-sdk-php-20161101&app_id=2017090608586413&biz_content=%7B%22subject%22%3A%22%5Cu548c%5Cu5408%5Cu751f%5Cu6d3b-%5Cu7f51%5Cu7edc%5Cu5546%5Cu54c1%22%2C%22out_trade_no%22%3A%22appzfb-299-%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Ftest2.heyishenghuo.com%2Fapi%2Fincludes%2Frespond.php&sign_type=RSA2&timestamp=2017-10-30+18%3A08%3A07&version=1.0&sign=duMQgBNPaRny8LbAMU7gqPE2AjwyeibzzK2CqrFAZJD%2BOwo%2Foslk1nPyCxzPkEqw2Mcm%2F9fWmjgILm44wljOptrBNiVGrwfxh%2BxS%2F17KqlAHh2DR%2FaJpFOSwaku4F9QOJXCzELJX1Nt7LCG3CX9EJ0fBWcg54H6APyHXo3GBqrYye3AO%2FmS7900RglhiM1V5m1gKDRnN32Jpivjz%2BRMQfRA0ZttaGHk2sixHFsU3O%2B%2BUr6SfbopIhjlWhVE6fauhQ40hsOAq2qMw%2F5SHWTwpq7c4J4JrLGMQ%2Bx3L50A9EHEJ6sdBGZY%2BOGaKmh3xtPzuI7DOzvJxtV1CfrkmunOLpA%3D%3D";

    }


    /**
     * 支付宝支付业务
     *
     * @param v
     */
    public void pay(View v) {
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

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        Log.d("PayDemoActivity", "orderParam+++" + orderParam);
        Log.d("PayDemoActivity", "sign+++" + sign);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayDemoActivity.this);
                Map<String, String> result = alipay.payV2(orderString, true);
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
     * 支付宝账户授权业务
     *
     * @param v
     */
    public void authV2(View v) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        //        Log.d("PayDemoActivity", "info+++" + info);
        //        Log.d("PayDemoActivity", "sign+++" + sign);

        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(PayDemoActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    //    public void h5Pay(View v) {
    //        Intent intent = new Intent(this, H5PayDemoActivity.class);
    //        Bundle extras = new Bundle();
    //        /**
    //         * url 是要测试的网站，在 Demo App 中会使用 H5PayDemoActivity 内的 WebView 打开。
    //         *
    //         * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
    //         * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
    //         * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
    //         * 进行测试。
    //         *
    //         * H5PayDemoActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
    //         * 可以参考它实现自定义的 URL 拦截逻辑。
    //         */
    //        String url = "http://m.taobao.com";
    //        extras.putString("url", url);
    //        intent.putExtras(extras);
    //        startActivity(intent);
    //    }

}
