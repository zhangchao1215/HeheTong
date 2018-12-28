package com.tsy.sdk.myokhttp.callback;

import android.text.TextUtils;

import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.IResponseHandler;
import com.tsy.sdk.myokhttp.util.LogUtils;
import com.tsy.sdk.myokhttp.util.NetworkUtils;
import com.tsy.sdk.myokhttp.util.ToastUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by tsy on 16/9/18.
 */
public class MyCallback implements Callback {
    private final String TOAST_404 = "系统正在维护，请稍后再试";
    private final String TOAST_000 = "网络连接不可用，请检查您的网络设置";

    private IResponseHandler mResponseHandler;

    public MyCallback(IResponseHandler responseHandler) {
        mResponseHandler = responseHandler;
    }

    @Override
    public void onFailure(final Call call, final IOException e) {
        LogUtils.e("onFailure", e);
        if (mResponseHandler == null) {
            return;
        }
        MyOkHttp.mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (NetworkUtils.isConnected(MyOkHttp.context)) {
                    if(!call.isCanceled()){
//                        ToastUtils.showSingleToast(TOAST_404);

                    }
                } else {
//                    ToastUtils.showSingleToast(TOAST_000);
                }
                if (TextUtils.isEmpty(e.toString())) {
                    mResponseHandler.onFailure(0, "time out");
                } else {
                    mResponseHandler.onFailure(0, e.toString());
                }

            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) {
        if (mResponseHandler == null) {
            return;
        }
        if (response.isSuccessful()) {


            mResponseHandler.onSuccess(response);
        } else {
            LogUtils.e("onResponse fail status=" + response.code());

            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
//                    if(response.code()==404){
//                        ToastUtils.showSingleToast("服务器异常haha1");
//                    }
                    switch (response.code()) {
                        case 404:
//                            ToastUtils.showSingleToast(TOAST_404);
                            break;
                        case 500:
//                            ToastUtils.showSingleToast(TOAST_404);
                            break;
                        default:

                    }
                    mResponseHandler.onFailure(response.code(), "fail status=" + response.code());
                }
            });
        }
    }
}
