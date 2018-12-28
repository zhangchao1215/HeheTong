package com.example.heyikun.heheshenghuo.modle.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.controller.activity.ArticleWebView;
import com.example.heyikun.heheshenghuo.controller.activity.NewLifeWebview;
import com.example.heyikun.heheshenghuo.controller.activity.ShareWebView;
import com.example.heyikun.heheshenghuo.controller.activity.WebViewActivity;
import com.example.heyikun.heheshenghuo.controller.activity.faxian.BigCastWebview;


/**
 * Created by hyk on 2017/10/17.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/17
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  跳转的WebView
 */

public class WebViewUtils {


    private static String mUserId;
    private static String mtoken;


    /**
     * 支付 商品支付
     *
     * @param context
     * @param nextUrl
     */
    public static void IntentWeb(Context context, String nextUrl) {
        mUserId = AppUtils.get().getString("user_id", "");
        mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, WebViewActivity.class);

        intent.putExtra("url", nextUrl + "?user_id=" + mUserId + "&token=" + token + "&from=1");

        App.activity.startActivity(intent);

    }

    public static void ArticleWebView(Context context, String nextUrl) {

        String mUserId = AppUtils.get().getString("user_id", "");


        String mtoken = AppUtils.get().getString("token", "");


        Log.d("WebViewUtils", mUserId);
        Log.d("WebViewUtils", mtoken);


        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        Log.d("HeHeUserFragment", "时间戳+++" + timestamp);

        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, ArticleWebView.class);

        intent.putExtra("shop_url", nextUrl + "&user_id=" + mUserId + "&token=" + token + "&from=1");

        App.activity.startActivity(intent);

    }


    public static void LifeNewWeb(Context context, String url, String title, String data) {

        String mUserId = AppUtils.get().getString("user_id", "");


        String mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        Log.d("HeHeUserFragment", "时间戳+++" + timestamp);

        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, ShareWebView.class);

        intent.putExtra("url", url + "&user_id=" + mUserId + "&token=" + token + "&from=1");
        intent.putExtra("title", title);
        intent.putExtra("data", data);
        App.activity.startActivity(intent);
    }


    /**
     * 分享传 userID token
     *
     * @param context
     * @param nextUrl
     * @param title
     */
    public static void ShareWebView(Context context, String nextUrl, String title) {

        String mUserId = AppUtils.get().getString("user_id", "");


        String mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        Log.d("HeHeUserFragment", "时间戳+++" + timestamp);

        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, ShareWebView.class);

        intent.putExtra("url", nextUrl + "&user_id=" + mUserId + "&token=" + token + "&from=1");
        intent.putExtra("title", title);
        App.activity.startActivity(intent);

    }


    // 给用户看得不用传

    public static void publicWebView(Context context, String url, String title) {

        String mUserId = AppUtils.get().getString("user_id", "");
        String mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, ShareWebView.class);

        intent.putExtra("url", url + "&user_id=" + mUserId + "&token=" + token + "&from=1");

        //        intent.putExtra("url",url+"&from=1");

        intent.putExtra("title", title);
        App.activity.startActivity(intent);


    }

    public static void bigCastWebView(Context context, String url, String title) {

        String mUserId = AppUtils.get().getString("user_id", "");
        String mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, BigCastWebview.class);

        intent.putExtra("url", url + "&user_id=" + mUserId + "&token=" + token + "&from=1");

        //        intent.putExtra("url",url+"&from=1");

        intent.putExtra("title", title);
        App.activity.startActivity(intent);


    }

    public static void bigCastWebViewTwo(Context context, String url, String title) {

        String mUserId = AppUtils.get().getString("user_id", "");
        String mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, BigCastWebview.class);

        intent.putExtra("url", url + "?user_id=" + mUserId + "&token=" + token + "&from=1");

        //        intent.putExtra("url",url+"&from=1");

        intent.putExtra("title", title);
        App.activity.startActivity(intent);


    }


    public static void publicWebTwo(Context context, String url, String title) {

        String mUserId = AppUtils.get().getString("user_id", "");
        String mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        Intent intent = new Intent(context, ShareWebView.class);

        intent.putExtra("url", url + "?user_id=" + mUserId + "&token=" + token + "&from=1");

        intent.putExtra("title", title);
        App.activity.startActivity(intent);


    }

    public static void WebFrom(Context context, String url, String title) {

        Intent intent = new Intent(context, ShareWebView.class);
        intent.putExtra("url", url + "&from=1");
        intent.putExtra("title", title);
        context.startActivity(intent);

    }

}