package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/10/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/14
 * <p>
 * 3：类描述： 收货地址的实体类
 * <p>
 * 4:类功能：
 */

public class TakeAddressBean {


    /**
     * status : 200
     * message : 获取用户收货地址
     * data : [{"address":"河北廊坊市文安县地址地址地址地址","province":"河北","city":"廊坊","district":"文安县","address_desc":"地址地址地址地址","address_id":"386","consignee":"张超","mobile":"18515597029","state":"1"}]
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
         * address : 河北廊坊市文安县地址地址地址地址
         * province : 河北
         * city : 廊坊
         * district : 文安县
         * address_desc : 地址地址地址地址
         * address_id : 386
         * consignee : 张超
         * mobile : 18515597029
         * state : 1
         */

        private String address;
        private String province;
        private String city;
        private String district;
        private String address_desc;
        private String address_id;
        private String consignee;
        private String mobile;
        private String state;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress_desc() {
            return address_desc;
        }

        public void setAddress_desc(String address_desc) {
            this.address_desc = address_desc;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
