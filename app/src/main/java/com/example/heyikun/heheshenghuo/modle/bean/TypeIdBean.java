package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/9/16.
 */

public class TypeIdBean {


    /**
     * status : 200
     * message : 获取病症名称
     * data : [{"diag_id":"18","diag_name":"腹胀"},{"diag_id":"19","diag_name":"咳嗽"},{"diag_id":"20","diag_name":"气短"},{"diag_id":"21","diag_name":"胃痛"},{"diag_id":"22","diag_name":"胃胀"},{"diag_id":"23","diag_name":"心慌"},{"diag_id":"24","diag_name":"胸闷"},{"diag_id":"50","diag_name":"胸痛"},{"diag_id":"59","diag_name":"呕吐"}]
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
         * diag_id : 18
         * diag_name : 腹胀
         */

        private String diag_id;
        private String diag_name;

        public String getDiag_id() {
            return diag_id;
        }

        public void setDiag_id(String diag_id) {
            this.diag_id = diag_id;
        }

        public String getDiag_name() {
            return diag_name;
        }

        public void setDiag_name(String diag_name) {
            this.diag_name = diag_name;
        }
    }
}
