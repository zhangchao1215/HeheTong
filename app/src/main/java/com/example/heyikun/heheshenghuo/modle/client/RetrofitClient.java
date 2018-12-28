package com.example.heyikun.heheshenghuo.modle.client;


import com.example.heyikun.heheshenghuo.modle.apiservice.IAPiSerVice;
import com.example.heyikun.heheshenghuo.modle.callBack.ICallBack;
import com.example.heyikun.heheshenghuo.modle.requestbuilder.RequestBuilder;
import com.example.heyikun.heheshenghuo.modle.response.ResponseHandler;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/9/8 12:33
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public class RetrofitClient extends RequestBuilder<RetrofitClient> {
    public static RetrofitClient retrofitClient;
    private static IAPiSerVice iaPiSerVice;
    private static Call<ResponseBody> callRequest;
    private int type;
    private final int TYPE_GET = 0x001;
    private final int TYPE_POST = 0x002;

    private RetrofitClient(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(IAPiSerVice.HOST_URL);
        if (okHttpClient != null) {
            builder.client(okHttpClient);
        }
        iaPiSerVice = builder.build().create(IAPiSerVice.class);
    }

    public static RetrofitClient getRetrofit(OkHttpClient okHttpClient) {
        if (retrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (retrofitClient == null) {
                    retrofitClient = new RetrofitClient(okHttpClient);
                }
            }
        }
        return retrofitClient;
    }

    public static RetrofitClient getRetrofit() {
        if (retrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (retrofitClient == null) {
                    retrofitClient = new RetrofitClient(null);
                }
            }
        }
        return retrofitClient;
    }

    public RetrofitClient get() {
        type = TYPE_GET;
        return this;
    }

    @Override
    public void enqueue(ResponseHandler responseHandler) {
        if (url == null) {
            throw new RuntimeException("url == null?");
        }
        if (parmas == null) {
            parmas = new HashMap<>();
        }
        if (headler == null) {
            headler = new HashMap<>();
        }
        switch (type) {
            case TYPE_GET:
                callRequest = iaPiSerVice.get(url, parmas,headler);
                break;
            case TYPE_POST:
                break;
            default:
                throw new RuntimeException("you must detmermine request the way ");
        }
        callRequest.enqueue(new ICallBack(responseHandler));
        type = 0;
    }
}
