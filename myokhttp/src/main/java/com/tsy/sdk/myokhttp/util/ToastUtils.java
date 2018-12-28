package com.tsy.sdk.myokhttp.util;

import android.content.Context;
import android.widget.Toast;


/**
 * Toast工具类，解决多个Toast同时出现的问题
 */
public class ToastUtils {

    private static Toast mToast;
    private static Context context;

    /********************** 非连续弹出的Toast ***********************/
    public static void showSingleToast(int resId) {
        getSingleToast(resId, Toast.LENGTH_SHORT).show();
    }
public static void init(Context mconText){
    context = mconText;
}
    public static void showSingleToast(String text) {
        getSingleToast(text, Toast.LENGTH_SHORT).show();
    }

    public static void showSingleLongToast(int resId) {
        getSingleToast(resId, Toast.LENGTH_LONG).show();
    }

    public static void showSingleLongToast(String text) {
        getSingleToast(text, Toast.LENGTH_LONG).show();
    }

    /*********************** 连续弹出的Toast ************************/
    public static void showToast(int resId) {
        getToast(resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String text) {
        getToast(text, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(int resId) {
        getToast(resId, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(String text) {
        getToast(text, Toast.LENGTH_LONG).show();
    }

    public static Toast getSingleToast(int resId, int duration) { // 连续调用不会连续弹出，只是替换文本
        return getSingleToast(context.getResources().getText(resId).toString(), duration);
    }

    //禁止连续弹出
    public static Toast getSingleToast(String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
        }
        return mToast;
    }

    public static Toast getToast(int resId, int duration) { // 连续调用会连续弹出
        return getToast(context.getResources().getText(resId).toString(), duration);
    }

    //可以连续弹出
    public static Toast getToast(String text, int duration) {
        return Toast.makeText(context, text, duration);
    }
}
