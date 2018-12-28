package com.example.heyikun.heheshenghuo.modle.response;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/9/8 12:58
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public abstract class GsonResponseHandler<T> implements ResponseHandler {
    private Type mType;

    public GsonResponseHandler() {
        Type myclass = getClass().getGenericSuperclass();    //反射获取带泛型的class
        if (myclass instanceof Class) {
            throw new RuntimeException("you must have a class");
        }
        ParameterizedType parameter = (ParameterizedType) myclass;      //获取所有泛型
        mType = $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);  //将泛型转为type
    }
    @Override
    public void onSuccess(Response<ResponseBody> response) {
        String bodyString = "";
        try {
            bodyString = response.body().string();
        } catch (IOException e) {
            onFailure(1, e.getMessage());
            return;
        }
        Gson gson = new Gson();
        onSuccess(response.code(), (T) gson.fromJson(bodyString, mType));
    }

    public abstract void onSuccess(int code, T response);

}
