package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/12/4.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/4
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 商圈实体类
 */

public class ServeShangquanBean {

    /**
     * status : 200
     * message : 获取成功
     * data : {"address":"北京市丰台区广场南三路","district":[{"district_id":"3474","district_name":"东门商圈"},{"district_id":"3473","district_name":"华强北商圈"},{"district_id":"3475","district_name":"南山商圈"},{"district_id":"3476","district_name":"华侨城商圈"},{"district_id":"3477","district_name":"人民南商圈"},{"district_id":"3478","district_name":"宝安商圈"},{"district_id":"3479","district_name":"龙岗商圈"},{"district_id":"3480","district_name":"深南中商圈"},{"district_id":"3481","district_name":"总部基地"}]}
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
         * address : 北京市丰台区广场南三路
         * district : [{"district_id":"3474","district_name":"东门商圈"},{"district_id":"3473","district_name":"华强北商圈"},{"district_id":"3475","district_name":"南山商圈"},{"district_id":"3476","district_name":"华侨城商圈"},{"district_id":"3477","district_name":"人民南商圈"},{"district_id":"3478","district_name":"宝安商圈"},{"district_id":"3479","district_name":"龙岗商圈"},{"district_id":"3480","district_name":"深南中商圈"},{"district_id":"3481","district_name":"总部基地"}]
         */

        private String address;
        private List<DistrictBean> district;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<DistrictBean> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictBean> district) {
            this.district = district;
        }

        public static class DistrictBean {
            /**
             * district_id : 3474
             * district_name : 东门商圈
             */

            private String district_id;
            private String district_name;

            public String getDistrict_id() {
                return district_id;
            }

            public void setDistrict_id(String district_id) {
                this.district_id = district_id;
            }

            public String getDistrict_name() {
                return district_name;
            }

            public void setDistrict_name(String district_name) {
                this.district_name = district_name;
            }
        }
    }
}
