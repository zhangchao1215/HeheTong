package com.example.heyikun.heheshenghuo.modle.callBack;

/**
 * Created by hyk on 2017/9/11.
 */

public interface MyCallBack {
    //成功的回调
    void onSuccess(String data);
    //失败的回调

    void onError(String error);


}
