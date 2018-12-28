package com.heyikun.hehe.jiguang;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.HeHeMainActivity;
import com.example.heyikun.heheshenghuo.controller.activity.ShareWebView;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Project name：
 * <p>
 * 类说明：
 * <p>
 * 2017/12/13 0013.
 * <p>
 * by：张超
 */
public class MyJGPushReceiver extends BroadcastReceiver {
    public static final String TAG = "MyJGPushReceiver";
    public static String m = "这是广播";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {

        String regId = JPushInterface.getRegistrationID(context);

        Log.d(TAG, "极光推送的id    " + regId);

        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        //        Logger.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + AndroidUtil.printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.e(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "接受到推送下来的自定义消息");
            Toast.makeText(context, "接受到推送下来的自定义消息", Toast.LENGTH_SHORT).show();

            // Push Talk messages are push down by custom message format
            //            processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "接受到推送下来的通知");
            Toast.makeText(context, "接受到推送下来的通知", Toast.LENGTH_SHORT).show();

            receivingNotification(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.e(TAG, "用户点击打开了通知");
            openNotification(context, bundle);

        } else {
            Log.e(TAG, "Unhandled intent - " + intent.getAction());
        }

    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.e(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.e(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e(TAG, "extras : " + extras);
        if (message.contains("测试")) {
            Intent intent = new Intent(context, ShareWebView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("url","https://hehe.heyishenghuo.com/mobile2/messages.php?fangzhao=1");
            intent.putExtra("title","我的消息");
            context.startActivity(intent);
            Log.d(TAG, "走第一步");
        } else {
            Intent intent = new Intent(context, HeHeMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.d(TAG, "走第二步");
        }


    }

    private void openNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.e(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.e(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e(TAG, "extras : " + extras);

        if (message.contains("测试")) {
            Intent intent = new Intent(context, ShareWebView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("url","https://hehe.heyishenghuo.com/mobile2/messages.php?fangzhao=1");
            intent.putExtra("title","我的消息");
            context.startActivity(intent);

            Log.d(TAG, "走第一步");
        } else {
            Intent intent = new Intent(context, HeHeMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.d(TAG, "走第二步");
        }

    }


    private void publicWebView(Context context) {

        String mUserId = AppUtils.get().getString("user_id", "");
        String mtoken = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        //给userID 还有 生成二次token ，在进行AES加密

        String token = mUserId + "," + mtoken + "," + timestamp;


        if (mUserId.equals("") || mtoken.equals("")) {
            Intent intent = new Intent(context, LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        } else {

            Intent intent = new Intent(context, ShareWebView.class);

            String url = "https://hehe.heyishenghuo.com/mobile2/messages.php?fangzhao=1";

            intent.putExtra("url", url + "&user_id=" + mUserId + "&token=" + token + "&from=1");

            //        intent.putExtra("url",url+"&from=1");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("title", "我的消息");
            context.startActivity(intent);

        }
    }

}
