package com.example.heyikun.heheshenghuo.modle.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hyk on 2017/10/21.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/21
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 设置密保问题的实体类
 */

public class MiBaoBean {


    /**
     * status : 200
     * message : 获取成功
     * data : {"1":["您母亲的姓名是？","您父亲的姓名是？","您配偶的姓名是？","您的出生地是？","您小学班主任的名字是？","您的小学校名是？","您父亲的生日是？","您母亲的生日是？","您配偶的生日是？","您的学号（或工号）是？"],"2":["您母亲的姓名是？","您父亲的姓名是？","您配偶的姓名是？","您的出生地是？","您小学班主任的名字是？","您的小学校名是？","您父亲的生日是？","您母亲的生日是？","您配偶的生日是？","您的学号（或工号）是？"],"3":["您母亲的姓名是？","您父亲的姓名是？","您配偶的姓名是？","您的出生地是？","您小学班主任的名字是？","您的小学校名是？","您父亲的生日是？","您母亲的生日是？","您配偶的生日是？","您的学号（或工号）是？"]}
     */

    private String status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("1")
        private List<String> _$1;
        @SerializedName("2")
        private List<String> _$2;
        @SerializedName("3")
        private List<String> _$3;

        public List<String> get_$1() {
            return _$1;
        }

        public void set_$1(List<String> _$1) {
            this._$1 = _$1;
        }

        public List<String> get_$2() {
            return _$2;
        }

        public void set_$2(List<String> _$2) {
            this._$2 = _$2;
        }

        public List<String> get_$3() {
            return _$3;
        }

        public void set_$3(List<String> _$3) {
            this._$3 = _$3;
        }
    }
}
