package com.tsy.sdk.myokhttp.builder;

import android.util.Log;

import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.callback.MyCallback;
import com.tsy.sdk.myokhttp.response.IResponseHandler;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import static java.lang.String.valueOf;

/**
 * 项目名称: 新医疗(HD)
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/8/25 16:53
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public class UpLoadImagesBuilder {
    private Map<String, File> files;
    private Map<String, String> params;
    private String url;
    private MyOkHttp myOkHttp;

    public UpLoadImagesBuilder(MyOkHttp myOkHttp) {
        files = new HashMap<>();
        params = new HashMap<>();
        this.myOkHttp = myOkHttp;
    }

    public UpLoadImagesBuilder url(String url) {
        this.url = url;
        return this;
    }

    public UpLoadImagesBuilder addFile(String key, File file) {
        files.put(key, file);
        return this;
    }

    public UpLoadImagesBuilder addPamars(String key, String value) {
        params.put(key, value);
        return this;
    }

    public void enqueue(IResponseHandler iResponseHandler) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (files != null) {
            for (String s : files.keySet()) {
                File file = files.get(s);
                String[] split = file.getAbsolutePath().split("/.");
                //                Log.e("TAG", "文件大小" + formatFileSizeToString(file.length()));
                RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                String filename = file.getName();
                //                requestBody.addFormDataPart(s, file.getAbsolutePath(), body);
                requestBody.addFormDataPart(s, filename, body);
                Log.d("UpLoadImagesBuilder", "上传图片的个数" + s);
                Log.d("UpLoadImagesBuilder", "fileName" + filename);
            }

        }
        if (params != null) {
            // map 里面是请求中所需要的 key 和 value
            for (String s : params.keySet()) {
                requestBody.addFormDataPart(s, params.get(s));
            }

        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        myOkHttp.getOkHttpClient().newCall(request).enqueue(new MyCallback(iResponseHandler));
    }

    public static String formatFileSizeToString(long fileLen) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileLen < 1024) {
            fileSizeString = df.format((double) fileLen) + "B";
        } else if (fileLen < 1048576) {
            fileSizeString = df.format((double) fileLen / 1024) + "K";
        } else if (fileLen < 1073741824) {
            fileSizeString = df.format((double) fileLen / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileLen / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
