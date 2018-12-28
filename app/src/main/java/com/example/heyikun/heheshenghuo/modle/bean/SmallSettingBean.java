package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/10/19.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/19
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  小工具列表实体类
 */

public class SmallSettingBean  {


    /**
     * status : 200
     * message : 获取小工具列表
     * data : [{"label_id":"1","label_name":"纪念日","state":"1","label_type":"0"},{"label_id":"2","label_name":"吃药提醒","state":"1","label_type":"0"},{"label_id":"3","label_name":"女性生理期","state":"1","label_type":"0"},{"label_id":"4","label_name":"财富升级","state":"1","label_type":"0"},{"label_id":"5","label_name":"穿衣指数","state":"1","label_type":"1"},{"label_id":"6","label_name":"运动提醒","state":"1","label_type":"1"},{"label_id":"7","label_name":"病症提醒","state":"1","label_type":"1"}]
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
         * label_id : 1
         * label_name : 纪念日
         * state : 1
         * label_type : 0
         */

        private String label_id;
        private String label_name;
        private String state;
        private String label_type;

        public String getLabel_id() {
            return label_id;
        }

        public void setLabel_id(String label_id) {
            this.label_id = label_id;
        }

        public String getLabel_name() {
            return label_name;
        }

        public void setLabel_name(String label_name) {
            this.label_name = label_name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLabel_type() {
            return label_type;
        }

        public void setLabel_type(String label_type) {
            this.label_type = label_type;
        }
    }
}
