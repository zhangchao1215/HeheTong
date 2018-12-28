package com.example.heyikun.heheshenghuo.modle.apiservice;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/9/8 12:25
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public interface IAPiSerVice {
    public static final String HOST_URL = "http://baike.baidu.com/api/openapi/";
    @GET("{url}")
    Call<ResponseBody> get(@Path("url") String url, @QueryMap Map<String, String> map, @HeaderMap Map<String, String> headers);

}
