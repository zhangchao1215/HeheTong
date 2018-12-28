package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/11/1.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/1
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 商品收藏实体类
 */

public class ShopCollectBean {


    /**
     * status : 200
     * message : 获取成功
     * data : {"goods":[{"goods_rec_id":"36","goods_id":"7759","goods_name":"怡禾康美腿足疗机YH-3322","goods_pic":"http://test2.heyishenghuo.com/images/201710/thumb_img/7759_thumb_G_1509073985960.jpg","goods_link":"http://test2.heyishenghuo.com/mobile2/goods.php?u=5456&id=7759","goods_price":"1584+1056易币","old_price":"2640.00"},{"goods_rec_id":"37","goods_id":"7759","goods_name":"怡禾康美腿足疗机YH-3322","goods_pic":"http://test2.heyishenghuo.com/images/201710/thumb_img/7759_thumb_G_1509073985960.jpg","goods_link":"http://test2.heyishenghuo.com/mobile2/goods.php?u=5456&id=7759","goods_price":"1584+1056易币","old_price":"2640.00"},{"goods_rec_id":"37","goods_id":null,"goods_name":null,"goods_pic":"http://test2.heyishenghuo.com/","goods_link":"http://test2.heyishenghuo.com/mobile2/goods.php?u=5456&id=","goods_price":"0+易币","old_price":null},{"goods_rec_id":"38","goods_id":"7759","goods_name":"怡禾康美腿足疗机YH-3322","goods_pic":"http://test2.heyishenghuo.com/images/201710/thumb_img/7759_thumb_G_1509073985960.jpg","goods_link":"http://test2.heyishenghuo.com/mobile2/goods.php?u=5456&id=7759","goods_price":"1584+1056易币","old_price":"2640.00"},{"goods_rec_id":"38","goods_id":null,"goods_name":null,"goods_pic":"http://test2.heyishenghuo.com/","goods_link":"http://test2.heyishenghuo.com/mobile2/goods.php?u=5456&id=","goods_price":"0+易币","old_price":null},{"goods_rec_id":"38","goods_id":"7059","goods_name":"若羌灰枣","goods_pic":"http://test2.heyishenghuo.com/images/201709/thumb_img/7059_thumb_G_1505073497435.jpg","goods_link":"http://test2.heyishenghuo.com/mobile2/goods.php?u=5456&id=7059","goods_price":"35.3+23易币","old_price":"58.30"}]}
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
        private List<GoodsBean> goods;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_rec_id : 36
             * goods_id : 7759
             * goods_name : 怡禾康美腿足疗机YH-3322
             * goods_pic : http://test2.heyishenghuo.com/images/201710/thumb_img/7759_thumb_G_1509073985960.jpg
             * goods_link : http://test2.heyishenghuo.com/mobile2/goods.php?u=5456&id=7759
             * goods_price : 1584+1056易币
             * old_price : 2640.00
             */

            private String goods_rec_id;
            private String goods_id;
            private String goods_name;
            private String goods_pic;
            private String goods_link;
            private String goods_price;
            private String old_price;

            public String getGoods_rec_id() {
                return goods_rec_id;
            }

            public void setGoods_rec_id(String goods_rec_id) {
                this.goods_rec_id = goods_rec_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_pic() {
                return goods_pic;
            }

            public void setGoods_pic(String goods_pic) {
                this.goods_pic = goods_pic;
            }

            public String getGoods_link() {
                return goods_link;
            }

            public void setGoods_link(String goods_link) {
                this.goods_link = goods_link;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getOld_price() {
                return old_price;
            }

            public void setOld_price(String old_price) {
                this.old_price = old_price;
            }
        }
    }
}
