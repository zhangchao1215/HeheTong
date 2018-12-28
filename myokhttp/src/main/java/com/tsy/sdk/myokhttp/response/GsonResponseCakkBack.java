package com.tsy.sdk.myokhttp.response;

import java.io.IOException;

import okhttp3.Response;

/**
 * 项目名称: 新医疗(HD)
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/8/17 13:58
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public abstract class GsonResponseCakkBack implements IResponseHandler{
    @Override
    public void onSuccess(Response response) {
        try {
            response.body().string();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
