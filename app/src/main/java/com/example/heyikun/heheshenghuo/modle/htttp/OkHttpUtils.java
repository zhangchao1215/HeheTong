package com.example.heyikun.heheshenghuo.modle.htttp;

import android.util.Log;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.util.NetWorkUtls;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.http.HttpMethod;
import okio.Buffer;

/**
 * Created by hyk on 2017/9/11.
 */

public class OkHttpUtils {
    private OkHttpClient okHttpClient = null;
    CacheDao cacheDao;

    private OkHttpUtils() {

        cookie();
    }

    public static OkHttpUtils okHttpUtils;

    public static OkHttpUtils getInstands() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                okHttpUtils = new OkHttpUtils();
            }
            return okHttpUtils;
        }
        return okHttpUtils;

    }

    private void cookie() {


        File cacheFile = new File(App.activity.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(8, TimeUnit.SECONDS); // 设置连接超时时间
        builder.writeTimeout(8, TimeUnit.SECONDS);// 设置写入超时时间
        builder.readTimeout(8, TimeUnit.SECONDS);// 设置读取数据超时时间
        builder.retryOnConnectionFailure(true);// 设置进行连接失败重试
        //        builder.addNetworkInterceptor(mRewriteCacheControlInterceptor);
        builder.addInterceptor(new EnhancedCacheInterceptor());
//        builder.addNetworkInterceptor(new EnhancedCacheInterceptor());
        builder.cache(cache);// 设置缓存
        builder.cookieJar(new CookieJar() {
            private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url, cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url);
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });

        okHttpClient = builder.build();
    }

    public void OKhttpGet(String url, Map<String, String> map, String cookie, final MyCallBack callBack) {

        StringBuffer sb = new StringBuffer("?");
        if (map.size() > 0) {
            Set<String> values = map.keySet();
            int sum = 0;
            for (String key : values) {
                String value = map.get(key);
                if (sum == 0) {
                    sb.append(key + "=" + value);
                    sum++;
                } else {
                    sb.append("&" + key + "=" + value);
                }
            }
        }
        url = url + sb.toString();
        Log.e("url", url);
        Request request = null;
        if (cookie != null) {
            request = new Request.Builder().get().url(url).addHeader("cookie", cookie).build();
        } else {
            request = new Request.Builder().get().addHeader("Content-Type", "application/json").url(url).build();
        }

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();

                if (data != null) {
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(data);
                        }
                    });

                } else {
                    Log.d("OkHttpUtils", "数据为空或数据错误");
                }

            }
        });

    }


    public void OkhttpPost(String url, Map<String, String> map, String cookie, final MyCallBack callBack) {

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (map != null && map.size() > 0) {
            for (String key : map.keySet()) {
                String value = map.get(key);
                if (key.equals("button")) {
                    builder.addPart(MultipartBody.create(MediaType.parse("image/*"), value));
                    Log.d("OkHttpUtils", "key+++" + key);
                }
                if (value == null) {

                } else {
                    builder.addFormDataPart(key, value);
                    if (value.endsWith(".jpg") || value.endsWith(".png")) {
                        String imgName = value.substring(value.lastIndexOf("/") + 1);
                        builder.addFormDataPart(key, value, MultipartBody.create(MediaType.parse("image/*"), new File(value)));

                        Log.d("UserSettingActivity", "上传图片OKhttp  ++  " + imgName);
                        Log.d("UserSettingActivity", "value++  " + value);
                    }
                }
            }
        }
        Request request = null;
        if (cookie != null) {
            request = new Request.Builder().url(url)
                    .addHeader("cookie", cookie)
                    .post(builder.build()).build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    //                    .addHeader("Content-Type","application/json")
                    .post(builder.build())
                    .build();
        }

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String data = response.body().string();
                if (data != null) {
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(data);
                            Log.d("OkHttpUtils", data);
                        }
                    });
                } else {
                    Log.d("OkHttpUtils", "请求数据为空或数据错误");
                }


            }
        });


    }


    //拦截器  get请求拦截器
    private static final Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtls.isAvailable(App.activity)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (!NetWorkUtls.isAvailable(App.activity)) {
                int maxAge = 1 * 60; // read from cache for 1 minute
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();

            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    };


    /**
     * post请求拦截器
     */

    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString(); //获取请求URL

            Log.d("OkHttpUtils", "请求地址" + url);

            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            String params = buffer.readString(Charset.forName("UTF-8")); //获取请求参数

            Response response;
            if (NetWorkUtls.isAvailable(App.activity)) {
                int maxAge = 60 * 60 * 24;
                //如果网络正常，执行请求。
                Response originalResponse = chain.proceed(request);
                //获取MediaType，用于重新构建ResponseBody
                MediaType type = originalResponse.body().contentType();
                //获取body字节即响应，用于存入数据库和重新构建ResponseBody
                byte[] bs = originalResponse.body().bytes();
                response = originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        //重新构建body，原因在于body只能调用一次，之后就关闭了。
                        .body(ResponseBody.create(type, bs))
                        .build();
                //将响应插入数据库
                cacheDao.insertResponse(url, params, new String(bs, "GB2312"));
            } else {
                //没有网络的时候，由于Okhttp没有缓存post请求，所以不要调用chain.proceed(request)，会导致连接不上服务器而抛出异常（504）
                String b = cacheDao.queryResponse(url, params); //读出响应
                Log.d("OkHttp", "request:" + url);
                Log.d("OkHttp", "request method:" + request.method());
                Log.d("OkHttp", "response body:" + b);
                int maxStale = 60 * 60 * 24 * 28;
                //构建一个新的response响应结果
                response = new Response.Builder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .body(ResponseBody.create(MediaType.parse("application/json"), b.getBytes()))
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .build();
            }


            return response;
        }

    };

}