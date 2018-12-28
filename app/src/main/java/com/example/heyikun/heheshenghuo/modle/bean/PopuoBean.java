package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/9/14.
 */

public class PopuoBean {

    /**
     * status : 200
     * message : 获取选项
     * data : [{"diag_id":"191","diag_name":"名门火衰舌像","img":"data/diag/1504481144888102566.jpg"},{"diag_id":"180","diag_name":"心脾两虚舌像","img":"data/diag/1504480975446188982.jpg"},{"diag_id":"186","diag_name":"湿热下注舌像","img":"data/diag/1504480984832272093.jpg"},{"diag_id":"190","diag_name":"肝气郁结舌像","img":"data/diag/1504480994735282144.jpg"}]
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
         * diag_id : 191
         * diag_name : 名门火衰舌像
         * img : data/diag/1504481144888102566.jpg
         */

        private String diag_id;
        private String diag_name;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
