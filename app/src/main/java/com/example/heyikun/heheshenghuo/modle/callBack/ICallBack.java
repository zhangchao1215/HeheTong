package com.example.heyikun.heheshenghuo.modle.callBack;


import com.example.heyikun.heheshenghuo.modle.response.ResponseHandler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/9/8 12:24
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public class ICallBack implements Callback<ResponseBody> {
    private ResponseHandler responseHandler;

    public ICallBack(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//        Log.e("ICallBack", "call.request().url():" + call.request().url());
            if(response.isSuccessful()){
                responseHandler.onSuccess(response);
            }else{
                responseHandler.onFailure(response.code(),"error"+response.message());
            }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        responseHandler.onFailure(0,t.getMessage());
    }
}
