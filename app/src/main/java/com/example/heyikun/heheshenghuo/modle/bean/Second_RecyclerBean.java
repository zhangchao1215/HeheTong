package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/9/17.
 */

public class Second_RecyclerBean {


    /**
     * status : 200
     * message : 获取选项
     * data : [{"diag_id":"853","diag_name":"腰部冷重感","img":""},{"diag_id":"855","diag_name":"阴雨寒凉加重","img":""},{"diag_id":"861","diag_name":"腰部热重感","img":""},{"diag_id":"862","diag_name":"口中粘腻","img":""},{"diag_id":"866","diag_name":"疼痛绵绵不休","img":""},{"diag_id":"867","diag_name":"腰膝酸软","img":""},{"diag_id":"874","diag_name":"刺痛固定不移","img":""},{"diag_id":"875","diag_name":"日轻夜重","img":""}]
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
         * diag_id : 853
         * diag_name : 腰部冷重感
         * img :
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
