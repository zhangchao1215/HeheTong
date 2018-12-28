package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/11/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/13
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ShoppingArticleBean  {


    /**
     * status : 200
     * message : 获取成功
     * data : [{"recommend_id":"8118","recommend_name":"康烯圣养SY浴巾 SNH60089麻灰70×140cm","recommend_pic":"http://hehe.heyishenghuo.com/images/201711/thumb_img/8118_thumb_G_1511911779342.jpg","recommend_link":"http://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=8118","recommend_price":"206.00+52易券","old_price":"258.00","physique":["湿热体质","痰湿体质","阴虚体质","阳虚体质","平和体质"]},{"recommend_id":"8114","recommend_name":"康烯圣养SY烯枕(深度睡眠枕)(大) SNH60081","recommend_pic":"http://hehe.heyishenghuo.com/images/201711/thumb_img/8114_thumb_G_1511907601853.jpg","recommend_link":"http://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=8114","recommend_price":"774.00+194易券","old_price":"968.00","physique":["气郁体质","阴虚体质"]},{"recommend_id":"8106","recommend_name":"康烯圣养SY烯枕(Ｕ型护颈枕) SNH60053","recommend_pic":"http://hehe.heyishenghuo.com/images/201711/thumb_img/8106_thumb_G_1511906054564.jpg","recommend_link":"http://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=8106","recommend_price":"294.00+74易券","old_price":"368.00","physique":["痰湿体质","阴虚体质","阳虚体质"]},{"recommend_id":"8105","recommend_name":"康烯圣养SY烯枕(深度睡眠枕)(小) SNH60052灰","recommend_pic":"http://hehe.heyishenghuo.com/images/201711/thumb_img/8105_thumb_G_1511905932187.jpg","recommend_link":"http://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=8105","recommend_price":"614.00+154易券","old_price":"768.00","physique":["痰湿体质","阴虚体质"]},{"recommend_id":"8080","recommend_name":"金正 全自动婴儿辅食破壁料理机 JZPB-950","recommend_pic":"http://hehe.heyishenghuo.com/images/201711/thumb_img/8080_thumb_G_1511823569596.jpg","recommend_link":"http://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=8080","recommend_price":"559.00+140易券","old_price":"699.00","physique":["气郁体质","血瘀体质","湿热体质","痰湿体质","阴虚体质","阳虚体质","平和体质","气虚体质"]},{"recommend_id":"8079","recommend_name":"金正 全自动多功能家用破壁料理机 JZPB-926S","recommend_pic":"http://hehe.heyishenghuo.com/images/201711/thumb_img/8079_thumb_G_1511823234056.jpg","recommend_link":"http://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=8079","recommend_price":"479.00+120易券","old_price":"599.00","physique":["气郁体质","血瘀体质","湿热体质","痰湿体质","阴虚体质","阳虚体质","平和体质","气虚体质"]}]
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
         * recommend_id : 8118
         * recommend_name : 康烯圣养SY浴巾 SNH60089麻灰70×140cm
         * recommend_pic : http://hehe.heyishenghuo.com/images/201711/thumb_img/8118_thumb_G_1511911779342.jpg
         * recommend_link : http://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=8118
         * recommend_price : 206.00+52易券
         * old_price : 258.00
         * physique : ["湿热体质","痰湿体质","阴虚体质","阳虚体质","平和体质"]
         */

        private String recommend_id;
        private String recommend_name;
        private String recommend_pic;
        private String recommend_link;
        private String recommend_price;
        private String old_price;
        private List<String> physique;

        public String getRecommend_id() {
            return recommend_id;
        }

        public void setRecommend_id(String recommend_id) {
            this.recommend_id = recommend_id;
        }

        public String getRecommend_name() {
            return recommend_name;
        }

        public void setRecommend_name(String recommend_name) {
            this.recommend_name = recommend_name;
        }

        public String getRecommend_pic() {
            return recommend_pic;
        }

        public void setRecommend_pic(String recommend_pic) {
            this.recommend_pic = recommend_pic;
        }

        public String getRecommend_link() {
            return recommend_link;
        }

        public void setRecommend_link(String recommend_link) {
            this.recommend_link = recommend_link;
        }

        public String getRecommend_price() {
            return recommend_price;
        }

        public void setRecommend_price(String recommend_price) {
            this.recommend_price = recommend_price;
        }

        public String getOld_price() {
            return old_price;
        }

        public void setOld_price(String old_price) {
            this.old_price = old_price;
        }

        public List<String> getPhysique() {
            return physique;
        }

        public void setPhysique(List<String> physique) {
            this.physique = physique;
        }
    }
}
