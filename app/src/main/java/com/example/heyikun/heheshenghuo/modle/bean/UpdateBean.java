package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/11/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/13
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：获取设备版本号
 */

public class UpdateBean {


    /**
     * status : 200
     * message : 版本升级信息获取成功
     * data : {"version":"21","uplink":"http://hehe.heyishenghuo.com/app_down.php","type":""}
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
        /**
         * version : 21
         * uplink : http://hehe.heyishenghuo.com/app_down.php
         * type :
         */

        private String version;
        private String uplink;
        private String type;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUplink() {
            return uplink;
        }

        public void setUplink(String uplink) {
            this.uplink = uplink;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
