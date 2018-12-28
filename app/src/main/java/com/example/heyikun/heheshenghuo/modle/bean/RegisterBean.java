package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/9/20.
 */

public class RegisterBean {


    /**
     * status : 200
     * message : 验证码正确
     */

    private String status;
    private String message;

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
}
