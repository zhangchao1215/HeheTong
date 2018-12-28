package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/12/18.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/18
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 发布文章获取类型的实体类
 */

public class SendArticleBean {


    /**
     * status : 200
     * message : 获取成功
     * data : [{"cat_id":"8","cat_name":"美容瘦身","cat_link":"https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=8"},{"cat_id":"9","cat_name":"修身养性","cat_link":"https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=9"},{"cat_id":"10","cat_name":"母婴保健","cat_link":"https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=10"},{"cat_id":"4","cat_name":"生活窍门","cat_link":"https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=4"},{"cat_id":"5","cat_name":"养生厨房","cat_link":"https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=5"},{"cat_id":"6","cat_name":"养生运动","cat_link":"https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=6"},{"cat_id":"7","cat_name":"养生秘笈","cat_link":"https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=7"}]
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
         * cat_id : 8
         * cat_name : 美容瘦身
         * cat_link : https://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-column.php?id=8
         */

        private String cat_id;
        private String cat_name;
        private String cat_link;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getCat_link() {
            return cat_link;
        }

        public void setCat_link(String cat_link) {
            this.cat_link = cat_link;
        }
    }
}