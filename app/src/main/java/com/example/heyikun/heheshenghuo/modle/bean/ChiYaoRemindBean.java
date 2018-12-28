package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/10/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/16
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ChiYaoRemindBean {


    /**
     * status : 200
     * message : 获取成功
     * data : null
     */

    private String status;
    private String message;
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
