package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/11/25.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/25
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 获取男女属性实体类
 */

public class SexBean {

    /**
     * status : 200
     * message : 获取用户性别成功
     * sex : 1
     */

    private String status;
    private String message;
    private String sex;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
