package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2018/4/20.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/4/20
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：获取经络文案 以及时辰养生的实体类
 */

public class TimeJingluoBean {


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


        private String jingluo;
        private String shichen;

        public String getJingluo() {
            return jingluo;
        }

        public void setJingluo(String jingluo) {
            this.jingluo = jingluo;
        }

        public String getShichen() {
            return shichen;
        }

        public void setShichen(String shichen) {
            this.shichen = shichen;
        }
    }
}
