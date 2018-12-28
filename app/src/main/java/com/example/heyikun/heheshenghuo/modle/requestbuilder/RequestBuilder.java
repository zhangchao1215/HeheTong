package com.example.heyikun.heheshenghuo.modle.requestbuilder;


import com.example.heyikun.heheshenghuo.modle.response.ResponseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述:
 * 创建人: localadmin
 * 创建时间: 2017/9/8 12:41
 * 修改人:
 * 修改内容:
 * 修改时间:
 */
public abstract class RequestBuilder<T extends RequestBuilder> {
    protected String url;
    protected Map<String, String> parmas;
    protected Map<String, String> headler;

    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    public T params(String key, String value) {
        if (parmas == null) {
            parmas = new HashMap<>();
        }
        parmas.put(key, value);
        return (T) this;
    }

    public T addParam(Map<String, String> parmas) {
        this.parmas = parmas;
        return (T) this;
    }

    public T header(String key, String value) {
        if (headler == null) {
            headler = new HashMap<>();
        }
        headler.put(key, value);
        return (T) this;
    }

    public T addHeader(Map<String, String> headler) {
        this.headler = headler;
        return (T) this;
    }

    public abstract void enqueue(ResponseHandler responseHandler);

}
