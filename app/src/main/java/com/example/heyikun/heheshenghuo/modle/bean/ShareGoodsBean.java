package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/12/20.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/20
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 分享商品
 */

public class ShareGoodsBean {


    /**
     * status : 200
     * message : 获取成功
     * data : {"goods_name":"拉杆箱","goods_img":"https://hehe.heyishenghuo.com/images/201706/thumb_img/46_thumb_G_1496382263002.jpg"}
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
         * goods_name : 拉杆箱
         * goods_img : https://hehe.heyishenghuo.com/images/201706/thumb_img/46_thumb_G_1496382263002.jpg
         */

        private String goods_name;
        private String goods_img;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }
    }
}
