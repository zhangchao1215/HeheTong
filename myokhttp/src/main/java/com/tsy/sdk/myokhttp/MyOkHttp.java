package com.tsy.sdk.myokhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.tsy.sdk.myokhttp.builder.DeleteBuilder;
import com.tsy.sdk.myokhttp.builder.DownloadBuilder;
import com.tsy.sdk.myokhttp.builder.GetBuilder;
import com.tsy.sdk.myokhttp.builder.PatchBuilder;
import com.tsy.sdk.myokhttp.builder.PostBuilder;
import com.tsy.sdk.myokhttp.builder.PutBuilder;
import com.tsy.sdk.myokhttp.builder.UpLoadImagesBuilder;
import com.tsy.sdk.myokhttp.builder.UploadBuilder;
import com.tsy.sdk.myokhttp.callback.MyCallback;
import com.tsy.sdk.myokhttp.response.IResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * MyOkhttp
 * Created by tsy on 16/9/14.
 */
public class MyOkHttp {

    private static OkHttpClient mOkHttpClient;
    public static Handler mHandler = new Handler(Looper.getMainLooper());
    public static Context context;

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * construct
     */
    public MyOkHttp() {
        this(null);
    }

    /**
     * construct
     *
     * @param okHttpClient custom okhttpclient
     */
    public MyOkHttp(OkHttpClient okHttpClient) {
        if (mOkHttpClient == null) {
            synchronized (MyOkHttp.class) {
                if (mOkHttpClient == null) {
                    if (okHttpClient == null) {
                        mOkHttpClient = new OkHttpClient();
                    } else {
                        mOkHttpClient = okHttpClient;
                    }
                }
            }
        }
    }

    public GetBuilder get() {
        return new GetBuilder(this);
    }

    public PostBuilder post() {
        return new PostBuilder(this);
    }

    public PutBuilder put() {
        return new PutBuilder(this);
    }

    public PatchBuilder patch() {
        return new PatchBuilder(this);
    }

    public DeleteBuilder delete() {
        return new DeleteBuilder(this);
    }

    public UpLoadImagesBuilder upLoadImages() {
        return new UpLoadImagesBuilder(this);
    }

    public UploadBuilder upload() {
        return new UploadBuilder(this);
    }

    public DownloadBuilder download() {
        return new DownloadBuilder(this);
    }

    /**
     * do cacel by tag
     *
     * @param tag tag
     */
    public void cancel(Object tag) {
        Dispatcher dispatcher = mOkHttpClient.dispatcher();
        for (Call call : dispatcher.queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
                Log.e("MyokHttp","关掉一个请求=="+call.request().url().toString());
            }
        }
        for (Call call : dispatcher.runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
                Log.e("MyokHttp","关掉一个请求=="+call.request().url().toString());
            }
        }
    }


}
