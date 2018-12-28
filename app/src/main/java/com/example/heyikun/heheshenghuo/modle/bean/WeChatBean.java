package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/12/4.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/4
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：微信支付实体类
 */

public class WeChatBean {


    /**
     * status : 200
     * message : OK
     * prepayid : wx201712041546424b026f5d5b0714812930
     */

    private String status;
    private String message;
    private String prepayid;

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

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }
}
