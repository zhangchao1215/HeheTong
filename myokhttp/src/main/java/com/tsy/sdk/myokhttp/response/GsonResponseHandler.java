package com.tsy.sdk.myokhttp.response;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import com.google.gson.internal.$Gson$Types;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Gson类型的回调接口
 * Created by tsy on 16/8/15.
 */
public abstract class GsonResponseHandler<T> implements IResponseHandler {

    private Type mType;

    public GsonResponseHandler() {
        Type myclass = getClass().getGenericSuperclass();    //反射获取带泛型的class
        if (myclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameter = (ParameterizedType) myclass;      //获取所有泛型
        mType = $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);  //将泛型转为type
    }

    private Type getType() {
        return mType;
    }

    @Override
    public final void onSuccess(final Response response) {
        ResponseBody responseBody = response.body();
        String responseBodyStr = "";

        try {
            responseBodyStr = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("onResponse fail read response body");
            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(response.code(), "fail read response body");
                }
            });
            return;
        } finally {
            responseBody.close();
        }

        try {

            JSONObject jsonObject = new JSONObject(responseBodyStr);
            final Iterator<String> keys = jsonObject.keys();
            final JSONObject jsonObject1 = jsonObject.optJSONObject("status");
            if(jsonObject1!=null){
                if ("10086".equals(jsonObject1.optString("code"))) {
                    if(MyOkHttp.context!=null){
                        Intent intent = new Intent();
                        intent.putExtra("isLoginOut", true);
                        intent.putExtra("token", "myokhttp");
                        intent.setAction("app_single_login_action");
                        MyOkHttp.context.sendBroadcast(intent);
                        return;
                    }

                }
            }

        } catch (JSONException e) {
        }
        final String finalResponseBodyStr = responseBodyStr;

        try {
            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Gson gson = new Gson();
                    Log.e("TAGSS", finalResponseBodyStr + "");
                    try {
                        onSuccess(response.code(), (T) gson.fromJson(finalResponseBodyStr, getType()));

                    } catch (Exception e) {
                        Log.e("TAG", "解析失败----" + response.request().url().toString());
                        Log.e("TAG", "解析失败----" + e.getMessage());


                        onFailure(response.code(),e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("onResponse fail parse gson, body=" + finalResponseBodyStr);
            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(response.code(), "fail parse gson, body=" + finalResponseBodyStr);
                }
            });

        }
    }

    public abstract void onSuccess(int statusCode, T response);

    @Override
    public void onProgress(long currentBytes, long totalBytes) {

    }
}
