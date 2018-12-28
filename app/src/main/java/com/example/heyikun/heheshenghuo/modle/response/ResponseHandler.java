package com.example.heyikun.heheshenghuo.modle.response;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/9/8 12:30
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public interface ResponseHandler {

    void onSuccess(Response<ResponseBody> response);

    void onFailure(int code, String errorMessage);
}
