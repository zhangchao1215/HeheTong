package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/10/23.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/23
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：获取已经设置好的密保问题
 */

public class GetMiBaoBean {


    /**
     * status : 200
     * message : 密保问题
     * data : [{"questions":"您母亲的姓名是？"},{"questions":"您父亲的姓名是？"},{"questions":"您配偶的姓名是？"},{"questions":"您母亲的姓名是？"},{"questions":"您父亲的姓名是？"},{"questions":"您配偶的姓名是？"}]
     */

    private String status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * questions : 您母亲的姓名是？
         */

        private String questions;

        public String getQuestions() {
            return questions;
        }

        public void setQuestions(String questions) {
            this.questions = questions;
        }
    }
}
