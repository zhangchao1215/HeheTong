package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/9/17.
 */

public class ZhenDuanTitleBean {


    /**
     * status : 200
     * message : 诊断部位
     * data : [{"typeid":"1","typename":"头颈","desc":"头颈头颈","pic_url":"data/diagtype/1507940651452922729.png"},{"typeid":"2","typename":"胸腹","desc":"胸腹胸腹","pic_url":"data/diagtype/1507940659671193086.png"},{"typeid":"3","typename":"腰背","desc":"腰背腰背","pic_url":"data/diagtype/1507940666998436101.png"},{"typeid":"4","typename":"生殖","desc":"生殖生殖","pic_url":"data/diagtype/1507940674179908541.png"},{"typeid":"5","typename":"皮肤","desc":"皮肤皮肤","pic_url":"data/diagtype/1507940686666269644.png"},{"typeid":"6","typename":"全身","desc":"全身全身","pic_url":"data/diagtype/1507940697274666374.png"},{"typeid":"7","typename":"妇科","desc":"妇科妇科","pic_url":"data/diagtype/1507940708185456934.png"},{"typeid":"9","typename":"test","desc":"test","pic_url":"data/diagtype/1507940719547964420.png"}]
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
         * typeid : 1
         * typename : 头颈
         * desc : 头颈头颈
         * pic_url : data/diagtype/1507940651452922729.png
         */

        private String typeid;
        private String typename;
        private String desc;
        private String pic_url;

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
