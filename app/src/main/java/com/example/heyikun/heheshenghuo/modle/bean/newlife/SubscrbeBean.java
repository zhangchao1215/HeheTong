package com.example.heyikun.heheshenghuo.modle.bean.newlife;

import java.util.List;

/**
 * Created by hyk on 2018/1/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/16
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：ding订阅题目实体类
 */

public class SubscrbeBean {

    /**
     * status : 200
     * message : 获取成功
     * label : [{"state_id":"","label_id":"16","label_name":"女性","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"30","label_name":"挫折","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"14","label_name":"男性","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"15","label_name":"更年期","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"17","label_name":"卵巢","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"18","label_name":"职场","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"19","label_name":"人才","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"20","label_name":"谣言","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"21","label_name":"杀伤力","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"22","label_name":"性格","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"23","label_name":"压力","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"24","label_name":"能力","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"25","label_name":"潜能","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"26","label_name":"才能","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"27","label_name":"梦想","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"28","label_name":"未来","label_pic":"https://hehe.heyishenghuo.com/","label_state":0},{"state_id":"","label_id":"29","label_name":"精神","label_pic":"https://hehe.heyishenghuo.com/","label_state":0}]
     */

    private String status;
    private String message;
    private List<LabelBean> label;

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

    public List<LabelBean> getLabel() {
        return label;
    }

    public void setLabel(List<LabelBean> label) {
        this.label = label;
    }

    public static class LabelBean {
        /**
         * state_id :
         * label_id : 16
         * label_name : 女性
         * label_pic : https://hehe.heyishenghuo.com/
         * label_state : 0
         */

        private String state_id;
        private String label_id;
        private String label_name;
        private String label_pic;
        private int label_state;

        public String getState_id() {
            return state_id;
        }

        public void setState_id(String state_id) {
            this.state_id = state_id;
        }

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

        public String getLabel_pic() {
            return label_pic;
        }

        public void setLabel_pic(String label_pic) {
            this.label_pic = label_pic;
        }

        public int getLabel_state() {
            return label_state;
        }

        public void setLabel_state(int label_state) {
            this.label_state = label_state;
        }
    }
}
